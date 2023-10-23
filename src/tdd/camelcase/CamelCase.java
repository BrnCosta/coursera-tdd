package tdd.camelcase;

import java.util.*;
import java.util.regex.*;

public class CamelCase {

    public static List<String> converterCamelCase(String original) {
        List<String> result = new ArrayList<String>(Arrays.asList(""));
        int wordIndex = 0;

        Pattern my_pattern = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
        Matcher my_match = my_pattern.matcher(original);
        boolean hasSpecial = my_match.find();

        if (hasSpecial)
            throw new CamelCaseException("converterCamelCase cannot contain a special character");

        for (int i = 0; i < original.length(); i++) {
            char letter = original.charAt(i);
            boolean isNewWord = false;

            if (Character.isDigit(letter)) {
                if (i == 0)
                    throw new CamelCaseException("converterCamelCase cannot start with a number");

                isNewWord = !previousLetterDigit(original, i);
            }

            if (Character.isUpperCase(letter)) {
                isNewWord = true;
                if (nextLetterUpper(original, i))
                    isNewWord = !previousLetterUpper(original, i) && i != 0;
                else
                    letter = Character.toLowerCase(letter);
            }

            if (isNewWord) {
                wordIndex++;
                result.add("");
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

    private static boolean previousLetterDigit(String camelString, int index) {
        char nextLetter = camelString.charAt(index - 1);
        return Character.isDigit(nextLetter);
    }

    public static String getIndex(List<String> stringList, int index) {
        return stringList.get(index);
    }
}
