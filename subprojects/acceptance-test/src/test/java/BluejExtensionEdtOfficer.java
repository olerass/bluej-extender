import org.fest.swing.exception.EdtViolationException;

import javax.swing.*;

import static org.fest.reflect.core.Reflection.staticMethod;

/**
 * Custom version of CheckThreadViolationRepaintManager that ignores thread violations
 * in BlueJ except those from the extension.
 */
public class BluejExtensionEdtOfficer extends CheckThreadViolationRepaintManager {
    public static BluejExtensionEdtOfficer install() {
        Object m = currentRepaintManager();
        if (m instanceof BluejExtensionEdtOfficer) return (BluejExtensionEdtOfficer)m;
        return installNew();
    }

    @Override
    public void addDirtyRegion(JComponent component, int x, int y, int w, int h) {
        if (!isFromBluej())
            super.addDirtyRegion(component, x, y, w, h);
    }

    @Override
    public synchronized void addInvalidComponent(JComponent component) {
        if (!isFromBluej())
            super.addInvalidComponent(component);
    }

    @Override
    public void violationFound(JComponent c, StackTraceElement[] stackTraceElements) {
        EdtViolationException e = new EdtViolationException("EDT violation detected");
        if (stackTraceElements != null) e.setStackTrace(stackTraceElements);
        throw e;
    }

    private static Object currentRepaintManager() {
        try {
            return staticMethod("appContextGet").withReturnType(Object.class)
                    .withParameterTypes(Object.class)
                    .in(SwingUtilities.class)
                    .invoke(RepaintManager.class);
        } catch (RuntimeException e) {
            return null;
        }
    }

    private static BluejExtensionEdtOfficer installNew() {
        BluejExtensionEdtOfficer m = new BluejExtensionEdtOfficer();
        setCurrentManager(m);
        return m;
    }

    private boolean isFromBluej() {
        String extensionPackage = System.getProperty("extensionPackage");
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        for (int i = 3; i < stackTrace.length; i++) { // Skip getStackTrace(), isFromBlueJ() and calling method
            if (stackTrace[i].getClassName().startsWith("bluej"))
                return true;
            if (stackTrace[i].getClassName().startsWith(extensionPackage))
                return false;
        }
        return false;
    }
}
