class Solution {
     private long totalSum = 0;
    public boolean canPartitionGrid(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        // Step 1: Calculate total sum of grid
        for (int[] row : grid) {
            for (int val : row) {
                totalSum += val;
            }
        }
        // Step 2: Try horizontal partitions
        if (isValidCut(grid)) return true;
        // Step 3: Reverse rows and try again
        reverseRows(grid);
        if (isValidCut(grid)) return true;
        // Restore original grid
        reverseRows(grid);
        // Step 4: Transpose grid to reuse same logic for vertical cuts
        int[][] transposed = transpose(grid);
        if (isValidCut(transposed)) return true;
        // Step 5: Reverse transposed grid and check again
        reverseRows(transposed);
        return isValidCut(transposed);
    }
    // Checks if a valid partition exists by cutting horizontally
    private boolean isValidCut(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        long upperSum = 0;
        Set<Long> seenValues = new HashSet<>();
        // Try cutting after each row
        for (int r = 0; r < rows - 1; r++) {
            // Add current row values to upper section
            for (int c = 0; c < cols; c++) {
                upperSum += grid[r][c];
                seenValues.add((long) grid[r][c]);
            }
            long lowerSum = totalSum - upperSum;
            long difference = upperSum - lowerSum;
            // Case 1: Perfect split
            if (difference == 0) return true;
            // Case 2: Remove one boundary element
            if (difference == grid[0][0] ||
                difference == grid[0][cols - 1] ||
                difference == grid[r][0]) {
                return true;
            }
            // Case 3: Remove any previously seen element
            if (r > 0 && cols > 1 && seenValues.contains(difference)) {
                return true;
            }
        }
        return false;
    }
    // Reverse rows of the grid (top ↔ bottom)
    private void reverseRows(int[][] grid) {
        int top = 0, bottom = grid.length - 1;
        while (top < bottom) {
            int[] temp = grid[top];
            grid[top] = grid[bottom];
            grid[bottom] = temp;
            top++;
            bottom--;
        }
    }
    // Transpose matrix
    private int[][] transpose(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        int[][] result = new int[cols][rows];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result[j][i] = grid[i][j];
            }
        }
        return result;
    }
}