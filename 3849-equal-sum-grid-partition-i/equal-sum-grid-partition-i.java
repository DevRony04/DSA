class Solution {
    public boolean canPartitionGrid(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        long[] rowSum = new long[m];
        long[] colSum = new long[n];

        long total = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                total += grid[i][j];
                rowSum[i] += grid[i][j];
                colSum[j] += grid[i][j];
            }
        }

        if (total % 2 != 0) {
            return false;
        }

        // Horizontal split
        long upper = 0;
        for (int i = 0; i < m - 1; i++) {
            upper += rowSum[i];
            if (upper == total - upper) {
                return true;
            }
        }

        // Vertical split
        long left = 0;
        for (int j = 0; j < n - 1; j++) {
            left += colSum[j];
            if (left == total - left) {
                return true;
            }
        }

        return false;
    }
}