class Solution {
    public List<Integer> findAllPeople(int n, int[][] meetings, int firstPerson) {

        Arrays.sort(meetings, (a, b) -> a[2] - b[2]);

        boolean[] knows = new boolean[n];
        knows[0] = true;
        knows[firstPerson] = true;

        int i = 0;
        while (i < meetings.length) {

            int time = meetings[i][2];
            Map<Integer, List<Integer>> graph = new HashMap<>();
            Set<Integer> people = new HashSet<>();

            // Build graph for same-time meetings
            while (i < meetings.length && meetings[i][2] == time) {
                int x = meetings[i][0];
                int y = meetings[i][1];

                graph.computeIfAbsent(x, k -> new ArrayList<>()).add(y);
                graph.computeIfAbsent(y, k -> new ArrayList<>()).add(x);

                people.add(x);
                people.add(y);
                i++;
            }

            // BFS within this time block
            Queue<Integer> queue = new ArrayDeque<>();
            Set<Integer> visited = new HashSet<>();

            for (int p : people) {
                if (knows[p]) {
                    queue.offer(p);
                    visited.add(p);
                }
            }

            while (!queue.isEmpty()) {
                int curr = queue.poll();
                knows[curr] = true;

                for (int nei : graph.getOrDefault(curr, Collections.emptyList())) {
                    if (visited.add(nei)) {
                        queue.offer(nei);
                    }
                }
            }
        }

        List<Integer> result = new ArrayList<>();
        for (int p = 0; p < n; p++) {
            if (knows[p]) result.add(p);
        }

        return result;
    }
}
