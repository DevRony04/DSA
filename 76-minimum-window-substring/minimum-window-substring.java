class Solution {
    public String minWindow(String s, String t) {
        if (s.length() < t.length()) return "";

        int[] freq = new int[128]; 
        for (char c : t.toCharArray()) {
            freq[c]++;
        }

        int required = t.length();
        int left = 0, minLen = Integer.MAX_VALUE, start = 0;

        for (int right = 0; right < s.length(); right++) {
            char rc = s.charAt(right);

            if (freq[rc] > 0) {
                required--;
            }
            freq[rc]--; 

            while (required == 0) {
                if (right - left + 1 < minLen) {
                    minLen = right - left + 1;
                    start = left;
                }

                char lc = s.charAt(left);

                freq[lc]++;
                if (freq[lc] > 0) {
                    required++;
                }
                left++;
            }
        }

        return minLen == Integer.MAX_VALUE ? "" : s.substring(start, start + minLen);
    }
}
