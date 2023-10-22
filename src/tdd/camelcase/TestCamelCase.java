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
}