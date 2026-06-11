class Solution {

    private static final int MOD = 1_000_000_007;

    public int assignEdgeWeights(int[][] edges) {

        Map<Integer, List<Integer>> graph = new HashMap<>();

        for (int[] e : edges) {
            int u = e[0];
            int v = e[1];

            graph.computeIfAbsent(u, k -> new ArrayList<>()).add(v);
            graph.computeIfAbsent(v, k -> new ArrayList<>()).add(u);
        }

        Queue<Integer> q = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();

        q.offer(1);
        visited.add(1);

        int depth = -1;

        while (!q.isEmpty()) {
            depth++;

            int size = q.size();

            while (size-- > 0) {
                int curr = q.poll();

                for (int next : graph.getOrDefault(curr, Collections.emptyList())) {
                    if (visited.add(next)) {
                        q.offer(next);
                    }
                }
            }
        }

        return power(2, depth - 1);
    }

    private int power(long base, int exp) {
        long res = 1;

        while (exp > 0) {
            if ((exp & 1) == 1) {
                res = (res * base) % MOD;
            }

            base = (base * base) % MOD;
            exp >>= 1;
        }

        return (int) res;
    }
}