class Solution {
    public boolean sumGame(String num) {
        int n = num.length();
        int diff = 0; // s1 -s2
        int qDiff = 0; // q1 - q2
        for (int i = 0; i < n / 2; i++) {
            if (num.charAt(i) == '?') {
                qDiff++;
            } else {
                diff += num.charAt(i) - '0';
            }
        }
        for (int i = n / 2; i < n; i++) {
            if (num.charAt(i) == '?') {
                qDiff--;
            } else {
                diff -= num.charAt(i) - '0';
            }
        }
        // qDiff == 0 means equal number of '?' on both sides.
        if (qDiff == 0) {
            return diff != 0;
        }
        // Each pair of '?' can compensate by at most 9.
        // If the current difference can exactly be compensated,
        // Bob can force equality.
        return diff * 2 != -9 * qDiff;
    }
}