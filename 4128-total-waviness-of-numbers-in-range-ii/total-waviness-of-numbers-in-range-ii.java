class Solution {

    static class Pair {
        long count;
        long waviness;

        Pair(long count, long waviness) {
            this.count = count;
            this.waviness = waviness;
        }
    }

    private char[] digits;
    private Pair[][][][][] dp;

    public long totalWaviness(long num1, long num2) {
        return solve(num2) - solve(num1 - 1);
    }

    private long solve(long n) {
        if (n <= 0) return 0;

        digits = String.valueOf(n).toCharArray();

        dp = new Pair[digits.length][2][2][11][11];

        return dfs(0, 1, 0, 10, 10).waviness;
    }

    private Pair dfs(int pos,
                     int tight,
                     int started,
                     int prev1,
                     int prev2) {

        if (pos == digits.length) {
            return new Pair(started == 1 ? 1 : 0, 0);
        }

        if (dp[pos][tight][started][prev1][prev2] != null) {
            return dp[pos][tight][started][prev1][prev2];
        }

        int limit = tight == 1 ? digits[pos] - '0' : 9;

        long totalCount = 0;
        long totalWaviness = 0;

        for (int digit = 0; digit <= limit; digit++) {

            int nextTight = (tight == 1 && digit == limit) ? 1 : 0;

            if (started == 0 && digit == 0) {

                Pair next = dfs(pos + 1, nextTight, 0, 10, 10);

                totalCount += next.count;
                totalWaviness += next.waviness;

            } else {

                if (prev1 == 10) {

                    Pair next =
                            dfs(pos + 1, nextTight, 1, digit, 10);

                    totalCount += next.count;
                    totalWaviness += next.waviness;

                } else if (prev2 == 10) {

                    Pair next =
                            dfs(pos + 1, nextTight, 1, digit, prev1);

                    totalCount += next.count;
                    totalWaviness += next.waviness;

                } else {

                    int add = 0;

                    if ((prev1 > prev2 && prev1 > digit)
                            || (prev1 < prev2 && prev1 < digit)) {
                        add = 1;
                    }

                    Pair next =
                            dfs(pos + 1, nextTight, 1, digit, prev1);

                    totalCount += next.count;
                    totalWaviness += next.waviness
                            + (long) add * next.count;
                }
            }
        }

        return dp[pos][tight][started][prev1][prev2] =
                new Pair(totalCount, totalWaviness);
    }
}