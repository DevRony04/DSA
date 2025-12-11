// Memoization + Pruning
class Solution {
    public boolean isScramble(String s1, String s2) {
        if (s1.equals(s2)) return true;
        if (s1.length() != s2.length()) return false;

        return dfs(s1, s2, new HashMap<>());
    }

    private boolean dfs(String s1, String s2, HashMap<String, Boolean> memo) {
        String key = s1 + "_" + s2;
        if (memo.containsKey(key)) return memo.get(key);

        if (!sameChars(s1, s2)) {
            memo.put(key, false);
            return false;
        }

        int n = s1.length();
        if (n == 1) return true;

        for (int i = 1; i < n; i++) {

            if (dfs(s1.substring(0, i), s2.substring(0, i), memo) &&
                dfs(s1.substring(i), s2.substring(i), memo)) {
                memo.put(key, true);
                return true;
            }

            if (dfs(s1.substring(0, i), s2.substring(n - i), memo) &&
                dfs(s1.substring(i), s2.substring(0, n - i), memo)) {
                memo.put(key, true);
                return true;
            }
        }

        memo.put(key, false);
        return false;
    }

    private boolean sameChars(String a, String b) {
        int[] count = new int[26];
        for (char c : a.toCharArray()) count[c - 'a']++;
        for (char c : b.toCharArray()) count[c - 'a']--;
        for (int x : count) if (x != 0) return false;
        return true;
    }
}
