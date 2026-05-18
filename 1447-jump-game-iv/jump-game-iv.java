class Solution {
    public int minJumps(int[] arr) {
        int n = arr.length;
        if (n == 1) return 0;

        Map<Integer, List<Integer>> map = new HashMap<>();

        for (int i = 0; i < n; i++) {
            map.computeIfAbsent(arr[i], k -> new ArrayList<>()).add(i);
        }

        boolean[] vis = new boolean[n];
        Queue<Integer> q = new ArrayDeque<>();

        q.offer(0);
        vis[0] = true;

        int steps = 0;

        while (!q.isEmpty()) {
            int size = q.size();

            while (size-- > 0) {
                int idx = q.poll();

                if (idx == n - 1) return steps;

                // i - 1
                if (idx > 0 && !vis[idx - 1]) {
                    vis[idx - 1] = true;
                    q.offer(idx - 1);
                }

                // i + 1
                if (idx < n - 1 && !vis[idx + 1]) {
                    vis[idx + 1] = true;
                    q.offer(idx + 1);
                }

                // same value jumps
                List<Integer> next = map.get(arr[idx]);

                if (next != null) {
                    for (int ni : next) {
                        if (!vis[ni]) {
                            vis[ni] = true;
                            q.offer(ni);
                        }
                    }

                    // critical optimization
                    next.clear();
                }
            }

            steps++;
        }

        return -1;
    }
}