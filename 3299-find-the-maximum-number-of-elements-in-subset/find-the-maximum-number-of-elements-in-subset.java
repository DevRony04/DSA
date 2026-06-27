class Solution {
    public int maximumLength(int[] nums) {
         Map<Integer, Integer> count = new HashMap<>();
         for(int num: nums){
             count.put(num, count.getOrDefault(num, 0) + 1);
         }
         int max = 1;
         if(count.containsKey(1)){
          int ones = count.get(1);
          if(ones % 2 == 0){
            ones--;
          }
          max = Math.max(max, ones);
         }
         for(int x : count.keySet()){
          if(x == 1) continue;

          int currentLen = 0;
          long curr = x;
          while(curr <= 1000000000L && count.getOrDefault((int)curr, 0) >= 2){
            currentLen +=2;
            curr *= curr;
          }
          if(curr <= 1000000000L && count.getOrDefault((int)curr, 0) > 0){
             currentLen += 1;
          }else{
            currentLen -= 1;
          }
          max = Math.max(max, currentLen);
         }
         return max;
    }
}