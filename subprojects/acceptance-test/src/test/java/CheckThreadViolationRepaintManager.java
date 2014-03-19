import javax.swing.*;
import java.lang.ref.WeakReference;

/**
 * <p>This class is used to detect Event Dispatch Thread rule violations<br>
 * See <a href="http://java.sun.com/docs/books/tutorial/uiswing/misc/threads.html">How to Use Threads</a>
 * for more info</p>
 * <p/>
 * <p>This is a modification of original idea of Scott Delap<br>
 * Initial version of ThreadCheckingRepaintManager can be found here<br>
 * <a href="http://www.clientjava.com/blog/2004/08/20/1093059428000.html">Easily Find Swing Threading Mistakes</a>
 * </p>
 *
 * @author Scott Delap
 * @author Alexander Potochkin
 * 
 * https://swinghelper.dev.java.net/
 */
public class CheckThreadViolationRepaintManager extends RepaintManager {
    // it is recommended to pass the complete check  
    private boolean completeCheck = true;
    private WeakReference<JComponent> lastComponent;

    public CheckThreadViolationRepaintManager(boolean completeCheck) {
        this.completeCheck = completeCheck;
    }

    public CheckThreadViolationRepaintManager() {
        this(true);
    }

    public boolean isCompleteCheck() {
        return completeCheck;
    }

    public void setCompleteCheck(boolean completeCheck) {
        this.completeCheck = completeCheck;
    }

    public synchronized void addInvalidComponent(JComponent component) {
        checkThreadViolations(component);
        super.addInvalidComponent(component);
    }

    public void addDirtyRegion(JComponent component, int x, int y, int w, int h) {
        checkThreadViolations(component);
        super.addDirtyRegion(component, x, y, w, h);
    }

    private void checkThreadViolations(JComponent c) {
        if (!SwingUtilities.isEventDispatchThread() && (completeCheck || c.isShowing())) {
            boolean repaint = false;
            boolean fromSwing = false;
            boolean imageUpdate = false;
            StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
            for (StackTraceElement st : stackTrace) {
                if (repaint && st.getClassName().startsWith("javax.swing.") &&
                        // for details see 
                        // https://swinghelper.dev.java.net/issues/show_bug.cgi?id=1
                         !st.getClassName().startsWith("javax.swing.SwingWorker")) {
                    fromSwing = true;
                }
                if (repaint && "imageUpdate".equals(st.getMethodName())) {
                    imageUpdate = true;
                }
                if ("repaint".equals(st.getMethodName())) {
                    repaint = true;
                    fromSwing = false;
                }
            }
            if (imageUpdate) {
                //assuming it is java.awt.image.ImageObserver.imageUpdate(...) 
                //image was asynchronously updated, that's ok 
                return;
            }
            if (repaint && !fromSwing) {
                //no problems here, since repaint() is thread safe
                return;
            }
            //ignore the last processed component
            if (lastComponent != null && c == lastComponent.get()) {
                return;
            }
            lastComponent = new WeakReference<JComponent>(c);
            violationFound(c, stackTrace);
        }
    }

    protected void violationFound(JComponent c, StackTraceElement[] stackTrace) {
        RuntimeException e = new RuntimeException("EDT violation detected");
        e.setStackTrace(stackTrace);
        throw e;
    }
}