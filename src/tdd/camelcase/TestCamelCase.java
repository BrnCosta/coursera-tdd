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

    @Test
    public void wordOnlyUpperCase() {
        List<String> result = CamelCase.converterCamelCase("TEST");
        String first = CamelCase.getIndex(result, 0);

        assertEquals(1, result.size());
        assertEquals("TEST", first);
    }

    @Test
    public void camelCaseWithUpperCase() {
        List<String> result = CamelCase.converterCamelCase("camelTESTCase");
        String primeiro = CamelCase.getIndex(result, 0);
        String second = CamelCase.getIndex(result, 1);
        String third = CamelCase.getIndex(result, 2);

        assertEquals(3, result.size());
        assertEquals("camel", primeiro);
        assertEquals("TEST", second);
        assertEquals("case", third);
    }

    @Test
    public void startsWithNumber() {
        try {
            CamelCase.converterCamelCase("10CamelCase");
            fail("Method is running with number at the beginning.");
        } catch (Exception e) {
        }
    }
}
