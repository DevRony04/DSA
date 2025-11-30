class Solution {
public:
    vector<vector<int>> subsets(vector<int>& nums) {
        vector<vector<int>> res;
        vector<int> path;
        dfs(0, nums, path, res);
        return res;
    }

private:
    void dfs(int idx, vector<int>& nums, vector<int>& path, vector<vector<int>>& res) {
        res.push_back(path); 
        
        for (int i = idx; i < nums.size(); i++) {
            path.push_back(nums[i]);
            dfs(i + 1, nums, path, res);
            path.pop_back(); 
        }
    }
};
