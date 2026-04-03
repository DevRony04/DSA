class Solution {
    public int maxWalls(int[] robots, int[] distance, int[] walls) {
        int n = robots.length;
        // left[i]  -> walls destroyed if robot i shoots LEFT
        // right[i] -> walls destroyed if robot i shoots RIGHT
        // overlap[i] -> walls between robot[i-1] and robot[i]
        int[] left = new int[n];
        int[] right = new int[n];
        int[] overlap = new int[n];
        // Map robot position -> distance
        Map<Integer, Integer> robotDist = new HashMap<>();
        for (int i = 0; i < n; i++) {
            robotDist.put(robots[i], distance[i]);
        }
        Arrays.sort(robots);
        Arrays.sort(walls);
        for (int i = 0; i < n; i++) {
            int pos = robots[i];
            int d = robotDist.get(pos);
            // First wall strictly greater than current robot
            int rightStartIdx = upperBound(walls, pos);
            // ---------------- LEFT RANGE ----------------
            int leftStartIdx;
            if (i > 0) {
                // Cannot cross previous robot
                int leftLimit = Math.max(pos - d, robots[i - 1] + 1);
                leftStartIdx = lowerBound(walls, leftLimit);
            } else {
                leftStartIdx = lowerBound(walls, pos - d);
            }
            // walls in [leftLimit, pos]
            left[i] = rightStartIdx - leftStartIdx;
            // ---------------- RIGHT RANGE ----------------
            int rightEndIdx;
            if (i < n - 1) {
                // Cannot cross next robot
                int rightLimit = Math.min(pos + d, robots[i + 1] - 1);
                rightEndIdx = upperBound(walls, rightLimit);
            } else {
                rightEndIdx = upperBound(walls, pos + d);
            }
            int rightStart = lowerBound(walls, pos);
            // walls in [pos, rightLimit]
            right[i] = rightEndIdx - rightStart;
            // ---------------- OVERLAP ----------------
            // walls between previous robot and current robot
            if (i > 0) {
                int prevStart = lowerBound(walls, robots[i - 1]);
                overlap[i] = rightStartIdx - prevStart;
            }
        }
        // DP states:
        // takeLeft  -> max walls till i if current robot shoots LEFT
        // takeRight -> max walls till i if current robot shoots RIGHT
        int takeLeft = left[0];
        int takeRight = right[0];
        for (int i = 1; i < n; i++) {
            // Case 1: current robot shoots LEFT
            int newLeft = Math.max(
                // previous also LEFT
                takeLeft + left[i],
                // previous was RIGHT → handle overlap correction
                takeRight
                    - right[i - 1] // remove previous right count
                    + Math.min(left[i] + right[i - 1], overlap[i]) // add valid merged coverage
            );
            // Case 2: current robot shoots RIGHT
            int newRight = Math.max(
                takeLeft + right[i],  // previous LEFT
                takeRight + right[i]  // previous RIGHT
            );
            takeLeft = newLeft;
            takeRight = newRight;
        }
        return Math.max(takeLeft, takeRight);
    }
    // First index >= target
    private int lowerBound(int[] arr, int target) {
        int l = 0, r = arr.length;
        while (l < r) {
            int mid = (l + r) / 2;
            if (arr[mid] < target) l = mid + 1;
            else r = mid;
        }
        return l;
    }
    // First index > target
    private int upperBound(int[] arr, int target) {
        int l = 0, r = arr.length;
        while (l < r) {
            int mid = (l + r) / 2;
            if (arr[mid] <= target) l = mid + 1;
            else r = mid;
        }
        return l;
    }
}