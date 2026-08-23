class Solution {
    public boolean sumGame(String num) {
        int n = num.length();
        int half = n / 2;
        
        // diff = (sum of left) - (sum of right)
        // qDiff = (count of ? in left) - (count of ? in right)
        int diff = 0, qDiff = 0;
        
        for (int i = 0; i < n; i++) {
            if (i < half) {
                if (num.charAt(i) == '?') qDiff++;
                else diff += num.charAt(i) - '0';
            } else {
                if (num.charAt(i) == '?') qDiff--;
                else diff -= num.charAt(i) - '0';
            }
        }
        
        // Bob wins only if:
        // diff + qDiff * 9 / 2 == 0
        // i.e. 2 * diff + qDiff * 9 == 0
        // Alice wins if this condition is NOT met
        return !(diff * 2 + qDiff * 9 == 0);
    }
}