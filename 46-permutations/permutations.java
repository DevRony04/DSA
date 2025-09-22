import java.util.ArrayList;
import java.util.List;

class Solution {
    private List<List<Integer>> result = new ArrayList<>();
    private List<Integer> currentPermutation = new ArrayList<>();
    private boolean[] visited;
    private int[] nums;

    public List<List<Integer>> permute(int[] nums) {
        this.nums = nums;
        this.visited = new boolean[nums.length];
        backtrack();
        return result;
    }

    private void backtrack() {
        if (currentPermutation.size() == nums.length) {
            result.add(new ArrayList<>(currentPermutation));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (!visited[i]) {
                visited[i] = true;
                currentPermutation.add(nums[i]);
                backtrack();
                currentPermutation.remove(currentPermutation.size() - 1);
                visited[i] = false;
            }
        }
    }
}
