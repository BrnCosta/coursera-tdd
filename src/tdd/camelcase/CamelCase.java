package tdd.camelcase;

import java.util.*;

public class CamelCase {
    public static List<String> converterCamelCase(String original) {
        List<String> result = new ArrayList<String>(Arrays.asList(""));
        int wordIndex = 0;

        for (char letter : original.toCharArray()) {
            if (Character.isUpperCase(letter)) {
                wordIndex++;
                result.add("");
                letter = Character.toLowerCase(letter);
            }

            String word = result.get(wordIndex) + letter;
            result.set(wordIndex, word);
        }

        return result;
    }

    public static String getIndex(List<String> stringList, int index) {
        return stringList.get(index);
    }
}
