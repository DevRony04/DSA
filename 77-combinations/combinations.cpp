class Solution {
public:
    vector<vector<int>> combine(int n, int k) {
        vector<vector<int>> res;
        vector<int> path;
        res.reserve(1 << k);

        dfs(1, n, k, path, res);
        return res;
    }

private:
    void dfs(int start, int n, int k, vector<int> &path, vector<vector<int>> &res) {
        if ((int)path.size() == k) {
            res.push_back(path);
            return;
        }

        int remaining = k - (int)path.size();

        for (int i = start; i <= n - remaining + 1; ++i) {
            path.push_back(i);
            dfs(i + 1, n, k, path, res);
            path.pop_back();
        }
    }
};
