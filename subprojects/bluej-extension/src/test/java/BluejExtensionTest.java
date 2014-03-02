import bluej.extensions.BlueJ;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.net.MalformedURLException;
import java.net.URL;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest(BlueJ.class)
public class BluejExtensionTest {
    private BluejExtension ext;

    @Before
    public void setup() {
        ext = new BluejExtension();
    }

    @Test
    public void getVersion_ReturnsTheExtensionVersion_WhenCalled() {
        assertThat(ext.getVersion(), is("1.0.0"));
    }

    @Test
    public void getName_ReturnsTheExtensionName_WhenCalled() {
        assertThat(ext.getName(), is("exampleName"));
    }

    @Test
    public void getURL_ReturnsTheExtensionURL_WhenCalled() throws MalformedURLException {
        assertThat(ext.getURL(), is(new URL("http://github.com/olerass/bluej-extender")));
    }

    @Test
    public void getDescription_ReturnsTheDescriptionStringFromLabels_WhenCalled() {
        final String LABEL_NAME = "extension.description";
        BlueJ proxyStub = mock(BlueJ.class);
        when( proxyStub.getLabel(LABEL_NAME) ).thenReturn("Some description");

        ext.startup(proxyStub);
        String res = ext.getDescription();

        assertThat(res, is("Some description"));
        verify(proxyStub).getLabel(LABEL_NAME);
    }
}