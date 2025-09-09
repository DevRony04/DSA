public class Solution {
    public int divide(int dividend, int divisor) {
        // Edge case: overflow
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }

        // Convert to long and take absolute values
        long a = Math.abs((long) dividend);
        long b = Math.abs((long) divisor);

        int result = 0;

        // Check from highest bit (31) down to 0
        for (int i = 31; i >= 0; i--) {
            if ((a >> i) >= b) {   // Can subtract b << i
                a -= b << i;
                result += 1 << i;
            }
        }

        // Apply sign
        return ((dividend ^ divisor) < 0) ? -result : result;
    }
}
