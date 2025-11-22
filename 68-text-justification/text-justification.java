import java.util.*;

class Solution {
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> result = new ArrayList<>();
        int n = words.length;
        int index = 0;

        while (index < n) {
            int lineStart = index;
            int lineLen = words[index].length();
            index++;

              while (index < n && lineLen + 1 + words[index].length() <= maxWidth) {
                lineLen += 1 + words[index].length(); 
                index++;
            }

            int lineEnd = index; 
            int wordCount = lineEnd - lineStart;
            StringBuilder sb = new StringBuilder();

            if (lineEnd == n || wordCount == 1) {
                sb.append(words[lineStart]);
                for (int i = lineStart + 1; i < lineEnd; i++) {
                    sb.append(' ');
                    sb.append(words[i]);
                }
                int remaining = maxWidth - sb.length();
                sb.append(spaces(remaining));
            } else {
                int totalWordLength = 0;
                for (int i = lineStart; i < lineEnd; i++) {
                    totalWordLength += words[i].length();
                }

                int totalSpaces = maxWidth - totalWordLength;
                int gaps = wordCount - 1;
                int spaceEach = totalSpaces / gaps;
                int extra = totalSpaces % gaps; 

                for (int i = lineStart; i < lineEnd - 1; i++) {
                    sb.append(words[i]);
                    int spacesToApply = spaceEach + (i - lineStart < extra ? 1 : 0);
                    sb.append(spaces(spacesToApply));
                }
                sb.append(words[lineEnd - 1]); 
            }

            result.add(sb.toString());
        }

        return result;
    }

    private String spaces(int n) {
        if (n <= 0) return "";
        char[] arr = new char[n];
        Arrays.fill(arr, ' ');
        return new String(arr);
    }
}
