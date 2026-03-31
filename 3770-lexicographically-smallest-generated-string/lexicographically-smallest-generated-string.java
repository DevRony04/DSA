class Solution {
    public String generateString(String str1, String str2) {
        int n = str1.length();
        int m = str2.length();
        int len = n + m - 1;
        char[] word = new char[len];
        boolean[] locked = new boolean[len]; // positions fixed by 'T'
        // Step 1: Initialize with placeholder
        Arrays.fill(word, '#');
        // Step 2: Apply all 'T' constraints
        for (int i = 0; i < n; i++) {
            if (str1.charAt(i) == 'T') {
                for (int j = 0; j < m; j++) {
                    int idx = i + j;

                    if (word[idx] != '#' && word[idx] != str2.charAt(j)) {
                        return ""; // conflict
                    }

                    word[idx] = str2.charAt(j);
                    locked[idx] = true; // cannot change later
                }
            }
        }
        // Step 3: Fill remaining positions with 'a'
        for (int i = 0; i < len; i++) {
            if (word[i] == '#') {
                word[i] = 'a';
            }
        }
        // Step 4: Handle 'F' constraints
        for (int i = 0; i < n; i++) {
            if (str1.charAt(i) == 'F') {
                // If substring equals str2 → must break it
                if (matches(word, str2, i)) {
                    boolean broken = false;
                    // Try to modify from right to left (minimize lex impact)
                    for (int j = m - 1; j >= 0; j--) {
                        int idx = i + j;
                        if (!locked[idx]) {
                            // Change to smallest different character
                            word[idx] = (str2.charAt(j) == 'a') ? 'b' : 'a';
                            broken = true;
                            break;
                        }
                    }
                    if (!broken) return ""; // cannot fix
                }
            }
        }
        return new String(word);
    }
    // Helper: check if substring equals str2
    private boolean matches(char[] word, String str2, int start) {
        for (int j = 0; j < str2.length(); j++) {
            if (word[start + j] != str2.charAt(j)) {
                return false;
            }
        }
        return true;
    }
}