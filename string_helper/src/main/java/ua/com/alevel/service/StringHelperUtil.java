package ua.com.alevel.service;

public class StringHelperUtil {

    private StringHelperUtil() {
    }

    public static String reverse(String string, boolean full) {
        StringBuilder stringBuilder = new StringBuilder();

        if (full) {
            for (int i = string.length() - 1; i >= 0; i--) {
                stringBuilder.append(string.charAt(i));
            }
        } else {
            String[] words = string.split(" ");
            for (String word : words) {
                StringBuilder reverseWord = new StringBuilder();
                for (int i = word.length() - 1; i >= 0; i--) {
                    reverseWord.append(word.charAt(i));
                }
                stringBuilder.append(reverseWord).append(" ");
            }
            stringBuilder.setLength(stringBuilder.length() - 1);
        }
        return stringBuilder.toString();
    }

    public static String reverse(String string, String substring) {
        StringBuilder stringBuilder = new StringBuilder();
        if (string.contains(substring)) {
            stringBuilder.append(string.replaceAll(substring, reverse(substring, true)));
        } else {
            System.out.println("No substring found");
        }

        return stringBuilder.toString();
    }

    public static String reverse(String string, int firstIndex, int lastIndex) {
        String substring = string.substring(firstIndex, lastIndex + 1);
        String substring2 = reverse(substring, true);
        StringBuilder stringBuilder = new StringBuilder(string.replace(substring, substring2));
        return stringBuilder.toString();
    }

    public static String reverse(String string, char firstSymbol, char lastSymbol) {
        StringBuilder stringBuilder = new StringBuilder();
        int firstIndex = string.indexOf(firstSymbol);
        int lastIndex = string.indexOf(lastSymbol, firstIndex);
        if (lastIndex != -1) {
            stringBuilder.append(reverse(string, firstIndex, lastIndex));
        }
        return stringBuilder.toString();
    }

    public static String reverse(String string, String firstString, String lastString) {
        StringBuilder stringBuilder = new StringBuilder();
        int firstIndex = string.indexOf(firstString);
        int lastIndex = string.indexOf(lastString, firstIndex) + lastString.length() - 1;
        stringBuilder.append(reverse(string, firstIndex, lastIndex));
        return stringBuilder.toString();
    }
}