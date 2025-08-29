import java.util.*;

class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        backtrack(result, "", 0, 0, n);
        return result;
    }

    private void backtrack(List<String> result, String current, int open, int close, int max) {
        // If the string is complete
        if (current.length() == max * 2) {
            result.add(current);
            return;
        }

        // Option 1: Add '(' if still available
        if (open < max) {
            backtrack(result, current + "(", open + 1, close, max);
        }

        // Option 2: Add ')' if valid
        if (close < open) {
            backtrack(result, current + ")", open, close + 1, max);
        }
    }
}
