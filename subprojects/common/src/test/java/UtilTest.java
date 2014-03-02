import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class UtilTest {
    @Test
    public void toStrArray_ReturnsAnArray_WithSameContentsAsTheGivenList() {
        List<String> lst = new ArrayList<String>();
        lst.add("item1");
        lst.add("item2");

        String[] res = Util.toStrArray(lst);

        assertThat(res, is(notNullValue()));
        assertEquals(2, res.length);
        assertEquals("item1", res[0]);
        assertEquals("item2", res[1]);
    }
}
