class Solution {
    public List<Integer> survivedRobotsHealths(int[] positions, int[] healths, String directions) {
       int n = positions.length;

        List<Integer> indices = new ArrayList<>();
        for (int i = 0; i < n; i++)
            indices.add(i);

        // Sort by position
        Collections.sort(indices, (a, b) -> positions[a] - positions[b]);

        Stack<Integer> rightMovers = new Stack<>();

        for (int i : indices) {
            if (directions.charAt(i) == 'R') {
                rightMovers.push(i);
                continue;
            }

            // Collision handling
            while (!rightMovers.isEmpty() && healths[i] > 0) {
                int topIndex = rightMovers.peek();

                if (healths[topIndex] > healths[i]) {
                    healths[topIndex]--;
                    healths[i] = 0;
                } else if (healths[topIndex] < healths[i]) {
                    healths[rightMovers.pop()] = 0;
                    healths[i]--;
                } else {
                    healths[rightMovers.pop()] = 0;
                    healths[i] = 0;
                }
            }
        }

        // Collect survivors
        List<Integer> survivors = new ArrayList<>();
        for (int h : healths) {
            if (h > 0)
                survivors.add(h);
        }

        return survivors;
    }
}