import java.util.List;

class Solution {

    public int minimumTotal(List<List<Integer>> triangle) {

        int n = triangle.size();
        int[] dp = new int[n + 1];

        for (int level = n - 1; level >= 0; level--) {
            List<Integer> row = triangle.get(level);
            for (int i = 0; i <= level; i++) {
                dp[i] = row.get(i) + Math.min(dp[i], dp[i + 1]);
            }
        }

        return dp[0];
    }
}
