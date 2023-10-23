package tdd.camelcase;

import java.util.*;

public class CamelCase {

    public static List<String> converterCamelCase(String original) {
        List<String> result = new ArrayList<String>(Arrays.asList(""));
        int wordIndex = 0;

        for (int i = 0; i < original.length(); i++) {
            char letter = original.charAt(i);
            boolean isNewWord = true;

            if (Character.isDigit(letter))
                throw new CamelCaseException("converterCamelCase cannot start with a number");

            if (Character.isUpperCase(letter)) {
                if (nextLetterUpper(original, i))
                    isNewWord = !previousLetterUpper(original, i) && i != 0;
                else
                    letter = Character.toLowerCase(letter);

                if (isNewWord) {
                    wordIndex++;
                    result.add("");
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
