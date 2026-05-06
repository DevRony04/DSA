class Solution {
    public char[][] rotateTheBox(char[][] boxGrid) {
         int m = boxGrid.length, n = boxGrid[0].length;

        char ans[][] = new char[n][m];

        for(char[] r : ans) {
            Arrays.fill(r, '.');
        }


        for(int i=0; i<m; i++) {

            int last = n-1;

            for(int j=n-1; j>=0; j--) {
                if(boxGrid[i][j]=='*') {
                    ans[j][m-1-i] = '*';
                    last = j-1;
                } else if(boxGrid[i][j]=='#') {
                    ans[last][m-1-i] = '#';
                    last--;
                }
            }

        }

        return ans;
    }
}