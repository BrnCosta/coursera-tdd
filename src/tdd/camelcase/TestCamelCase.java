package tdd.camelcase;

import static org.junit.Assert.*;
import org.junit.*;

import java.util.List;

public class TestCamelCase {

    @Test
    public void emptyString() {
        List<String> result = CamelCase.converterCamelCase("");

        assertEquals(1, result.size());
        assertEquals("", CamelCase.getIndex(result, 0));
    }

    @Test
    public void onlyOneWord() {
        String expected = "test";
        List<String> result = CamelCase.converterCamelCase(expected);

        assertEquals(1, result.size());
        assertEquals(expected, CamelCase.getIndex(result, 0));
    }

    @Test
    public void oneWordWithUpper() {
        String expected = "test";
        List<String> result = CamelCase.converterCamelCase("Test");

        assertEquals(1, result.size());
        assertEquals(expected, CamelCase.getIndex(result, 0));
    }

    @Test
    public void twoWords() {
        List<String> result = CamelCase.converterCamelCase("camelCase");

        assertEquals(2, result.size());
        assertEquals("camel", CamelCase.getIndex(result, 0));
        assertEquals("case", CamelCase.getIndex(result, 1));
    }

    @Test
    public void wordOnlyUpperCase() {
        List<String> result = CamelCase.converterCamelCase("TEST");

        assertEquals(1, result.size());
        assertEquals("TEST", CamelCase.getIndex(result, 0));
    }

    @Test
    public void camelCaseWithUpperCase() {
        List<String> result = CamelCase.converterCamelCase("camelTESTCase");

        assertEquals(3, result.size());
        assertEquals("camel", CamelCase.getIndex(result, 0));
        assertEquals("TEST", CamelCase.getIndex(result, 1));
        assertEquals("case", CamelCase.getIndex(result, 2));
    }

    @Test(expected = CamelCaseException.class)
    public void startsWithNumber() {
        CamelCase.converterCamelCase("10CamelCase");
    }

    @Test(expected = CamelCaseException.class)
    public void specialCharacter() {
        CamelCase.converterCamelCase("Camel#Case");
    }

    @Test
    public void camelCaseWithNumber() {
        List<String> result = CamelCase.converterCamelCase("camel10Case");

        assertEquals(3, result.size());
        assertEquals("camel", CamelCase.getIndex(result, 0));
        assertEquals("10", CamelCase.getIndex(result, 1));
        assertEquals("case", CamelCase.getIndex(result, 2));
    }

    @Test
    public void camelCaseWithNumberAndUpper() {
        List<String> result = CamelCase.converterCamelCase("camel10TESTCase");

        assertEquals(4, result.size());
        assertEquals("camel", CamelCase.getIndex(result, 0));
        assertEquals("10", CamelCase.getIndex(result, 1));
        assertEquals("TEST", CamelCase.getIndex(result, 2));
        assertEquals("case", CamelCase.getIndex(result, 3));
    }
}
