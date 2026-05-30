class Solution {
     static class SegmentTree {
        int n;
        int[] tree;

        SegmentTree(int n) {
            this.n = n;
            tree = new int[4 * n];
        }

        void update(int node, int start, int end, int idx, int val) {
            if (start == end) {
                tree[node] = val;
                return;
            }

            int mid = (start + end) >> 1;

            if (idx <= mid)
                update(node << 1, start, mid, idx, val);
            else
                update(node << 1 | 1, mid + 1, end, idx, val);

            tree[node] = Math.max(tree[node << 1], tree[node << 1 | 1]);
        }

        int query(int node, int start, int end, int l, int r) {
            if (r < start || end < l)
                return 0;

            if (l <= start && end <= r)
                return tree[node];

            int mid = (start + end) >> 1;

            return Math.max(
                query(node << 1, start, mid, l, r),
                query(node << 1 | 1, mid + 1, end, l, r)
            );
        }
    }
    public List<Boolean> getResults(int[][] queries) {
        int MAX = 50005;

        TreeSet<Integer> obstacles = new TreeSet<>();
        obstacles.add(0);

        SegmentTree seg = new SegmentTree(MAX);

        List<Boolean> ans = new ArrayList<>();

        for (int[] q : queries) {

            if (q[0] == 1) {

                int x = q[1];

                Integer left = obstacles.floor(x);
                Integer right = obstacles.ceiling(x);

                if (right == null)
                    right = MAX - 1;

                seg.update(1, 0, MAX - 1, x, x - left);

                if (right != MAX - 1) {
                    seg.update(1, 0, MAX - 1, right, right - x);
                }

                obstacles.add(x);

            } else {

                int x = q[1];
                int sz = q[2];

                Integer last = obstacles.floor(x);

                int best = seg.query(1, 0, MAX - 1, 0, x);

                best = Math.max(best, x - last);

                ans.add(best >= sz);
            }
        }

        return ans;
    }
}