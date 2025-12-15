class Solution {
    public int numDecodings(String s) {
        int n = s.length();
        if (n == 0) return 0;

        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = s.charAt(0) == '0' ? 0 : 1;

        for (int i = 2; i <= n; i++) {

            int oneDigit = s.charAt(i - 1) - '0';
            if (oneDigit >= 1) {
                dp[i] += dp[i - 1];
            }

            int twoDigits = (s.charAt(i - 2) - '0') * 10 + oneDigit;
            if (twoDigits >= 10 && twoDigits <= 26) {
                dp[i] += dp[i - 2];
            }
        }
        return dp[n];
    }
}
