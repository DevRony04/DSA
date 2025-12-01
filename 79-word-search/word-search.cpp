class Solution {
public:
    bool exist(vector<vector<char>>& board, string word) {
        int m = board.size(), n = board[0].size();

        vector<int> freq(128, 0);
        for (auto &row : board)
            for (char c : row)
                freq[c]++;

        for (char c : word)
            if (--freq[c] < 0)
                return false;

        if (freq[word[0]] > freq[word.back()])
            reverse(word.begin(), word.end());

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == word[0] && dfs(board, word, 0, i, j))
                    return true;
            }
        }
        return false;
    }

    bool dfs(vector<vector<char>>& board, string& word, int idx, int x, int y) {
        if (idx == word.length()) return true;

        if (x < 0 || y < 0 || x >= board.size() || y >= board[0].size()
            || board[x][y] != word[idx])
            return false;

        char temp = board[x][y];
        board[x][y] = '#';  

        static int dirs[4][2] = {{1,0},{-1,0},{0,1},{0,-1}};
        for (auto &d : dirs)
            if (dfs(board, word, idx + 1, x + d[0], y + d[1])) {
                board[x][y] = temp;
                return true;
            }

        board[x][y] = temp;
        return false;
    }
};
