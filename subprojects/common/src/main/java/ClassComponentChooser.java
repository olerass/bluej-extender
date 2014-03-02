import org.netbeans.jemmy.ComponentChooser;

import java.awt.*;

public class ClassComponentChooser implements ComponentChooser {
    private String targetClass;

    public ClassComponentChooser(String targetClass) {
        this.targetClass = targetClass;
    }

    @Override
    public boolean checkComponent(Component component) {
        return targetClass.equals(component.getClass().getName());
    }

    @Override
    public String getDescription() {
        return "Component with class: " + targetClass;
    }
}
