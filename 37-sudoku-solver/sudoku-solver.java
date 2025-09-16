class Solution {
    private int[] rows = new int[9];
    private int[] cols = new int[9];
    private int[] boxes = new int[9];
    private List<int[]> empty = new ArrayList<>();

    public void solveSudoku(char[][] board) {
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                if (board[r][c] == '.') {
                    empty.add(new int[]{r, c});
                } else {
                    int d = board[r][c] - '1';
                    setMask(r, c, d);
                }
            }
        }
        backtrack(board, 0);
    }

    private boolean backtrack(char[][] board, int idx) {
        if (idx == empty.size()) return true; 

        int[] cell = empty.get(idx);
        int r = cell[0], c = cell[1];
        int box = (r / 3) * 3 + (c / 3);

        int mask = rows[r] | cols[c] | boxes[box];

        for (int d = 0; d < 9; d++) {
            if ((mask & (1 << d)) == 0) { 
                board[r][c] = (char) (d + '1');
                setMask(r, c, d);

                if (backtrack(board, idx + 1)) return true;
               
                unsetMask(r, c, d);
                board[r][c] = '.';
            }
        }
        return false;
    }

    private void setMask(int r, int c, int d) {
        rows[r] |= 1 << d;
        cols[c] |= 1 << d;
        boxes[(r / 3) * 3 + c / 3] |= 1 << d;
    }

    private void unsetMask(int r, int c, int d) {
        rows[r] ^= 1 << d;
        cols[c] ^= 1 << d;
        boxes[(r / 3) * 3 + c / 3] ^= 1 << d;
    }
}
