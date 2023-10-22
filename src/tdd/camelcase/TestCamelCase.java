package tdd.camelcase;

import static org.junit.Assert.*;
import org.junit.*;

import java.util.List;

public class TestCamelCase {

    @Test
    public void emptyString() {
        List<String> result = CamelCase.converterCamelCase("");
        String first = CamelCase.getIndex(result, 0);

        assertEquals(1, result.size());
        assertEquals("", first);
    }

    @Test
    public void onlyOneWord() {
        String expected = "test";

        List<String> result = CamelCase.converterCamelCase(expected);
        String first = CamelCase.getIndex(result, 0);

        assertEquals(1, result.size());
        assertEquals(expected, first);
    }

    @Test
    public void twoWords() {
        List<String> result = CamelCase.converterCamelCase("camelCase");
        String first = CamelCase.getIndex(result, 0);
        String second = CamelCase.getIndex(result, 1);

        assertEquals(2, result.size());
        assertEquals("camel", first);
        assertEquals("case", second);
    }
}
