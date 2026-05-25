class Solution {
    public boolean canReach(String s, int minJump, int maxJump) {
          int n = s.length();

        boolean[] dp = new boolean[n];
        dp[0] = true;

        int reachable = 0;

        for(int i = 1; i < n; i++) {

            // Add new index into window
            if(i - minJump >= 0 && dp[i - minJump]) {
                reachable++;
            }

            // Remove old index from window
            if(i - maxJump - 1 >= 0 && dp[i - maxJump - 1]) {
                reachable--;
            }

            // Current index reachable
            if(s.charAt(i) == '0' && reachable > 0) {
                dp[i] = true;
            }
        }

        return dp[n - 1];
    }
}