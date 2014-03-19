import commonUtil;
import edtOfficer;
import com.google.common.io.Files;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.fest.swing.core.BasicRobot;
import org.fest.swing.core.Robot;
import org.fest.swing.security.NoExitSecurityManagerInstaller;
import org.netbeans.jemmy.JemmyProperties;
import org.netbeans.jemmy.drivers.InputDriverInstaller;
import org.netbeans.jemmy.operators.JFrameOperator;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * General setup for Cucumber and Jemmy.
 */
public class Init {
    private Robot cleaningRobot;
    private Thread t;
    private static JFrameOperator mainFrame;
    private NoExitSecurityManagerInstaller sec;
    private static File tempDir1;
    private static File tempDir2;

    public static void setBluejFrame(String newWindowTitle) {
        mainFrame = new JFrameOperator(newWindowTitle);
    }

    public static JFrameOperator bluejFrame() {
        return mainFrame;
    }

    public static String getTempDir1() {
        return tempDir1.getAbsolutePath();
    }
    public static String getTempDir2() {
        return tempDir2.getAbsolutePath();
    }

    private void createTempDirs() {
        tempDir1 = Files.createTempDir();
        tempDir2 = Files.createTempDir();
    }

    private void deleteTempDirs() {
        Util.deleteDirectory(tempDir1);
        Util.deleteDirectory(tempDir2);
    }

    private void configureJemmy() {
        if (System.getProperty("slowSwing") != null) {
            InputDriverInstaller i = new InputDriverInstaller(false, true);
            i.install();
            JemmyProperties.setCurrentTimeout("Waiter.TimeDelta", 1000);
        }

        // Most of these are lowered considerably compared to default values.
        // ~60 seconds is just too long to wait before test fail.
        JemmyProperties.setCurrentTimeout("ComponentOperator.WaitStateTimeout", 5000);
        JemmyProperties.setCurrentTimeout("ComponentOperator.WaitComponentTimeout", 5000);
    }

    /**
     * Starts BlueJ in a new thread.
     */
    @Before
    public void before() throws InterruptedException, InvocationTargetException {
        createTempDirs();
        configureJemmy();
        cleaningRobot = BasicRobot.robotWithNewAwtHierarchy();
        sec = NoExitSecurityManagerInstaller.installNoExitSecurityManager();
        BluejExtensionEdtOfficer.install();

        t = new Thread(new Runnable() {
            public void run() {
                File file = new File(System.getProperty("bluej-jar"));
                URLClassLoader cl = null;
                try {
                    cl = new URLClassLoader(new URL[]{file.toURI().toURL()}, null);
                } catch (MalformedURLException e) {
                }

                Class<?> clazz = null;
                try {
                    clazz = cl.loadClass("bluej.Boot");
                } catch (ClassNotFoundException e) {
                }

                Method main = null;
                try {
                    main = clazz.getMethod("main", String[].class);
                } catch (NoSuchMethodException e) {
                }

                try {
                    main.invoke(null, new Object[]{new String[]{}});
                } catch (Exception e) {
                }
            }
        });
        t.start();

        mainFrame = new JFrameOperator("BlueJ");
    }

    /**
     * Shuts down BlueJ and kills the starting thread.
     */
    @After
    public void after() throws InterruptedException {
        t.stop();
        t.join();
        cleaningRobot.cleanUp();
        sec.uninstall();
        System.gc(); // Allow JVM to cleanup disposed windows
        deleteTempDirs();
    }
}
