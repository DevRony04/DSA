class Solution {
    public int longestValidParentheses(String s) {
        int left = 0, right = 0, maxLen = 0;

        // Left to Right Scan
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') left++;
            else right++;
            
            if (left == right) {
                maxLen = Math.max(maxLen, 2 * right);
            } else if (right > left) {
                left = right = 0;
            }
        }

        left = right = 0;
        // Right to Left Scan
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == '(') left++;
            else right++;

            if (left == right) {
                maxLen = Math.max(maxLen, 2 * left);
            } else if (left > right) {
                left = right = 0;
            }
        }

        return maxLen;
    }
}
