import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new ArrayList<>();
        if (matrix == null || matrix.length == 0) return result;

        int m = matrix.length, n = matrix[0].length;
        int rMin = 0, rMax = m - 1;
        int cMin = 0, cMax = n - 1;

        while (rMin <= rMax && cMin <= cMax) {
            for (int c = cMin; c <= cMax; c++) {
                result.add(matrix[rMin][c]);
            }
            rMin++;

            for (int r = rMin; r <= rMax; r++) {
                result.add(matrix[r][cMax]);
            }
            cMax--;

            if (rMin <= rMax) {
                for (int c = cMax; c >= cMin; c--) {
                    result.add(matrix[rMax][c]);
                }
                rMax--;
            }

            if (cMin <= cMax) {
                for (int r = rMax; r >= rMin; r--) {
                    result.add(matrix[r][cMin]);
                }
                cMin++;
            }
        }

        return result;
    }
}
