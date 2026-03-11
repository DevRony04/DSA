class Solution {
    public int bitwiseComplement(int n) {
         if (n == 0)
            return 1;

        int result = 0;
        int counter = 0;
        while (n != 0) {
            int r = n % 2;
            result += (int)(Math.pow(2, counter) * (r == 0 ? 1 : 0));
            counter++;
            n = n >> 1;
        }
        return result;
    }
}