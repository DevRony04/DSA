class Solution {
     private static final int MOD = 1_000_000_007;
    public int xorAfterQueries(int[] nums, int[][] queries) {
        int n = nums.length;
        for (int[] query : queries) {
            int l = query[0];
            int r = query[1];
            int k = query[2];
            int v = query[3];

            int idx = l;

            while (idx <= r) {
                nums[idx] = (int) (((long) nums[idx] * v) % MOD);
                idx += k;
            }

        }
        int xor = 0;

        for (int i = 0; i < n; i++) {
            xor ^= nums[i];
        }

        return xor;
    }
}