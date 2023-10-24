package tdd.camelcase;

import java.util.*;
import java.util.regex.*;

public class CamelCase {

    public static List<String> converterCamelCase(String original) {
        List<String> result = new ArrayList<String>(Arrays.asList(""));

        verifySpecialCharacter(original);

        for (int i = 0; i < original.length(); i++) {
            if (checkNewWord(original, i))
                result.add("");

            char letter = verifyLetterUpperCase(original, i);

            String word = result.get(result.size() - 1) + letter;
            result.set(result.size() - 1, word);
        }

        return result;
    }

    private static void verifySpecialCharacter(String original) {
        Pattern specialCharRegex = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
        Matcher originalMatch = specialCharRegex.matcher(original);

        if (originalMatch.find())
            throw new CamelCaseException("converterCamelCase cannot contain a special character");
    }

    private static char verifyLetterUpperCase(String original, int index) {
        char letter = original.charAt(index);

        if (!nextLetterUpper(original, index))
            return Character.toLowerCase(letter);

        return letter;
    }

    private static boolean checkNewWord(String original, int index) {
        if (Character.isDigit(original.charAt(index))) {
            if (index == 0)
                throw new CamelCaseException("converterCamelCase cannot start with a number");

            return !previousLetterDigit(original, index);
        }

        if (Character.isUpperCase(original.charAt(index))) {
            if (!previousLetterUpper(original, index))
                return index != 0;
            else
                return !nextLetterUpper(original, index) && index != 0;
        }
        return false;
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

        char previousLetter = camelString.charAt(index - 1);
        return Character.isUpperCase(previousLetter);
    }

    private static boolean previousLetterDigit(String camelString, int index) {
        char previousLetter = camelString.charAt(index - 1);
        return Character.isDigit(previousLetter);
    }

    public static String getIndex(List<String> stringList, int index) {
        return stringList.get(index);
    }
}
