package tdd.camelcase;

import java.util.*;

public class CamelCase {
    public static List<String> converterCamelCase(String camelCaseString) {
        List<String> result = new ArrayList<String>(Arrays.asList(""));
        int wordIndex = 0;

        for (int i = 0; i < camelCaseString.length(); i++) {
            char letter = camelCaseString.charAt(i);

            if (Character.isUpperCase(letter)) {
                if (nextLetterUpper(camelCaseString, i)) {
                    if (!previousLetterUpper(camelCaseString, i) && i != 0) {
                        wordIndex++;
                        result.add("");
                    }
                } else {
                    wordIndex++;
                    result.add("");
                    letter = Character.toLowerCase(letter);
                }
            }

            String word = result.get(wordIndex) + letter;
            result.set(wordIndex, word);
        }

        return result;
    }

    private static boolean nextLetterUpper(String camelString, int index) {
        if (index + 1 == camelString.length())
            return true;

        char nextLetter = camelString.charAt(index + 1);
        return Character.isUpperCase(nextLetter);
    }

    private static boolean previousLetterUpper(String camelString, int index) {
        if (index - 1 < 0)
            return true;

        char nextLetter = camelString.charAt(index - 1);
        return Character.isUpperCase(nextLetter);
    }

    public static String getIndex(List<String> stringList, int index) {
        return stringList.get(index);
    }
}
