class Solution {
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);  // Step 1: sort
        int n = nums.length;
        int closestSum = nums[0] + nums[1] + nums[2]; // initialize with first 3

        for (int i = 0; i < n - 2; i++) {
            int left = i + 1, right = n - 1;

            while (left < right) {
                int currentSum = nums[i] + nums[left] + nums[right];

                // Update closest if needed
                if (Math.abs(currentSum - target) < Math.abs(closestSum - target)) {
                    closestSum = currentSum;
                }

                // Move pointers
                if (currentSum < target) {
                    left++;
                } else if (currentSum > target) {
                    right--;
                } else {
                    return currentSum; // exact match
                }
            }
        }
        return closestSum;
    }
}
