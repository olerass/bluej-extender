import org.junit.Test;

import javax.swing.*;
import java.awt.*;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;


public class ClassComponentChooserTest {
    @Test
    public void checkComponent_ReturnsTrue_WhenComponentHasCorrectClass() {
        ClassComponentChooser chooser = new ClassComponentChooser("javax.swing.JLabel");
        Component c = new JLabel();

        assertThat(chooser.checkComponent(c), is(true));
    }

    @Test
    public void checkComponent_ReturnsFalse_WhenComponentHasIncorrectClass() {
        ClassComponentChooser chooser = new ClassComponentChooser("some.other.Class");
        Component c = new JLabel();

        assertThat(chooser.checkComponent(c), is(false));
    }

    @Test
    public void getDescription_ReturnsDescriptiveString_WhenCalled() {
        ClassComponentChooser chooser = new ClassComponentChooser("some.Class");

        assertThat(chooser.getDescription(), is("Component with class: some.Class"));
    }
}
