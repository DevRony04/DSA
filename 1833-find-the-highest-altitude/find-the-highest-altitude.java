class Solution {
    public int largestAltitude(int[] gain) {
        int maxAltitudes = Integer.MIN_VALUE;
        int sum = 0;
        for(int i = 0; i<gain.length; i++){
          sum += gain[i];
          if(sum > maxAltitudes){
            maxAltitudes = sum;
          } 
        }
        return maxAltitudes > 0 ? maxAltitudes : 0;
    }
}