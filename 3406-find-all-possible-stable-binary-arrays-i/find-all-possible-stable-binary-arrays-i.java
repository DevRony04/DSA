class Solution {
      private static final int MOD = 1_000_000_007;
    public int numberOfStableArrays(int zero, int one, int limit) {
         // dp[onesLeft][zerosLeft][lastPlaced]
        int[][][] dp = new int[one + 1][zero + 1][2];
        // Base case: no elements left
        dp[0][0][0] = 1;
        dp[0][0][1] = 1;
        for (int onesLeft = 0; onesLeft <= one; onesLeft++) {
            for (int zerosLeft = 0; zerosLeft <= zero; zerosLeft++) {
                if (onesLeft == 0 && zerosLeft == 0) {
                    continue;
                }
                long ways = 0;
                // Case: lastPlaced = 1 → place zeros
                int maxZerosWeCanPlace = Math.min(zerosLeft, limit);
                for (int len = 1; len <= maxZerosWeCanPlace; len++) {
                    ways += dp[onesLeft][zerosLeft - len][0];
                    ways %= MOD;
                }
                dp[onesLeft][zerosLeft][1] = (int) ways;
                ways = 0;
                // Case: lastPlaced = 0 → place ones
                int maxOnesWeCanPlace = Math.min(onesLeft, limit);
                for (int len = 1; len <= maxOnesWeCanPlace; len++) {
                    ways += dp[onesLeft - len][zerosLeft][1];
                    ways %= MOD;
                }
                dp[onesLeft][zerosLeft][0] = (int) ways;
            }
        }
        int startWithOne  = dp[one][zero][0];
        int startWithZero = dp[one][zero][1];
        return (startWithOne + startWithZero) % MOD;
    }
}