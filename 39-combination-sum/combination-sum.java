import java.util.*;

class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(candidates);
        backtrack(res, new ArrayList<>(), candidates, target, 0);
        return res;
    }

    private void backtrack(List<List<Integer>> res, List<Integer> curr, int[] candidates, int target, int start) {
        if (target == 0) {
            res.add(new ArrayList<>(curr));
            return;
        }

        for (int i = start; i < candidates.length; i++) {
            if (candidates[i] > target) break; 
            curr.add(candidates[i]);
            backtrack(res, curr, candidates, target - candidates[i], i);
            curr.remove(curr.size() - 1);
        }
    }
}
