class Solution {
    public int robotSim(int[] commands, int[][] obstacles) {
         // Store obstacles in HashSet (encoded as long)
        Set<Long> set = new HashSet<>();
        for (int[] o : obstacles) {
            long key = (((long) o[0]) << 32) | (o[1] & 0xffffffffL);
            set.add(key);
        }

        // Directions: North, East, South, West
        int[] dx = {0, 1, 0, -1};
        int[] dy = {1, 0, -1, 0};

        int dir = 0; // start facing North
        int x = 0, y = 0;
        int maxDist = 0;

        for (int cmd : commands) {
            if (cmd == -1) {
                dir = (dir + 1) % 4; // turn right
            } else if (cmd == -2) {
                dir = (dir + 3) % 4; // turn left
            } else {
                // move step by step
                for (int i = 0; i < cmd; i++) {
                    int nx = x + dx[dir];
                    int ny = y + dy[dir];

                    long key = (((long) nx) << 32) | (ny & 0xffffffffL);
                    if (set.contains(key)) break;

                    x = nx;
                    y = ny;

                    maxDist = Math.max(maxDist, x * x + y * y);
                }
            }
        }

        return maxDist;
    }
}