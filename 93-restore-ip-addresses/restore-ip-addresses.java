class Solution {
    List<String> result = new ArrayList<>();

    public List<String> restoreIpAddresses(String s) {
        backtrack(s, 0, 0, new StringBuilder());
        return result;
    }

    private void backtrack(String s, int index, int segments, StringBuilder path) {

        // If 4 segments formed and all chars used
        if (segments == 4 && index == s.length()) {
            result.add(path.substring(0, path.length() - 1));
            return;
        }

        // Prune invalid paths
        if (segments == 4 || index == s.length()) return;

        int num = 0;
        int len = path.length();

        for (int i = index; i < Math.min(index + 3, s.length()); i++) {

            num = num * 10 + (s.charAt(i) - '0');

            if (num > 255) break;           // invalid range
            if (i > index && s.charAt(index) == '0') break; // leading zero

            path.append(num).append('.');
            backtrack(s, i + 1, segments + 1, path);
            path.setLength(len); // backtrack
        }
    }
}
