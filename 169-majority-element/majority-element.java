class Solution {
    public int majorityElement(int[] nums) {
        int count = 0;
        int candidate = 0;
        for(int x : nums){
         if(count == 0) candidate = x;
         count += (x == candidate) ? 1 : -1;
        }
        return candidate;
    }
}