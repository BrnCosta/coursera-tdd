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
        List<String> result = CamelCase.converterCamelCase("TESTE");
        String primeiro = CamelCase.getIndex(result, 0);

        assertEquals(1, result.size());
        assertEquals("TESTE", primeiro);
    }

    @Test
    public void camelCaseComUpperCase() {
        List<String> result = CamelCase.converterCamelCase("camelTESTECase");
        String primeiro = CamelCase.getIndex(result, 0);
        String segundo = CamelCase.getIndex(result, 1);
        String terceiro = CamelCase.getIndex(result, 2);

        assertEquals(3, result.size());
        assertEquals("camel", primeiro);
        assertEquals("TESTE", segundo);
        assertEquals("case", terceiro);
    }
}
