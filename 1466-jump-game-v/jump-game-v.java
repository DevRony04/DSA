class Solution {
    public int maxJumps(int[] arr, int d) {
      int n = arr.length;
        int[] dp = new int[n];

        int ans = 1;

        for (int i = 0; i < n; i++) {
            ans = Math.max(ans, dfs(i, arr, d, dp));
        }

        return ans;
    }

    private int dfs(int i, int[] arr, int d, int[] dp) {

        if (dp[i] != 0) {
            return dp[i];
        }

        int best = 1;

        // Left
        for (int j = i - 1; j >= Math.max(0, i - d); j--) {

            if (arr[j] >= arr[i]) {
                break;
            }

            best = Math.max(best, 1 + dfs(j, arr, d, dp));
        }

        // Right
        for (int j = i + 1; j <= Math.min(arr.length - 1, i + d); j++) {

            if (arr[j] >= arr[i]) {
                break;
            }

            best = Math.max(best, 1 + dfs(j, arr, d, dp));
        }

        return dp[i] = best;
    }
}