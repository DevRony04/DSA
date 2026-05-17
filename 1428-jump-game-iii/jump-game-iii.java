class Solution {
    public boolean canReach(int[] arr, int start) {
          return dfs(arr, start);
    }

    private boolean dfs(int[] arr, int i) {
        if (i < 0 || i >= arr.length || arr[i] < 0) {
            return false;
        }

        if (arr[i] == 0) {
            return true;
        }

        int jump = arr[i];
        arr[i] = -arr[i]; // mark visited

        return dfs(arr, i + jump) || dfs(arr, i - jump);
    }
}