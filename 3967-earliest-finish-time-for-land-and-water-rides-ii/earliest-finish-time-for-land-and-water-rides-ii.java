class Solution {
    public int earliestFinishTime(int[] landStartTime, int[] landDuration, int[] waterStartTime, int[] waterDuration) {
        int ans = Integer.MAX_VALUE;

        ans = Math.min(ans,
                solve(landStartTime, landDuration,
                        waterStartTime, waterDuration));

        ans = Math.min(ans,
                solve(waterStartTime, waterDuration,
                        landStartTime, landDuration));

        return ans;
    }

    private int solve(int[] aStart, int[] aDur,
                      int[] bStart, int[] bDur) {

        int m = bStart.length;

        int[][] arr = new int[m][2];

        for (int i = 0; i < m; i++) {
            arr[i][0] = bStart[i];
            arr[i][1] = bDur[i];
        }

        Arrays.sort(arr, (x, y) -> x[0] - y[0]);

        int[] starts = new int[m];

        // prefix minimum duration
        int[] pref = new int[m];

        // suffix minimum (start + duration)
        int[] suff = new int[m];

        for (int i = 0; i < m; i++) {
            starts[i] = arr[i][0];
        }

        pref[0] = arr[0][1];

        for (int i = 1; i < m; i++) {
            pref[i] = Math.min(pref[i - 1], arr[i][1]);
        }

        suff[m - 1] = arr[m - 1][0] + arr[m - 1][1];

        for (int i = m - 2; i >= 0; i--) {
            suff[i] = Math.min(
                    suff[i + 1],
                    arr[i][0] + arr[i][1]
            );
        }

        int ans = Integer.MAX_VALUE;

        for (int i = 0; i < aStart.length; i++) {

            int finish = aStart[i] + aDur[i];

            int idx = upperBound(starts, finish);

            // bStart <= finish
            if (idx >= 0) {
                ans = Math.min(ans,
                        finish + pref[idx]);
            }

            // bStart > finish
            if (idx + 1 < m) {
                ans = Math.min(ans,
                        suff[idx + 1]);
            }
        }

        return ans;
    }

    private int upperBound(int[] arr, int target) {

        int l = 0;
        int r = arr.length - 1;

        int ans = -1;

        while (l <= r) {

            int mid = (l + r) >>> 1;

            if (arr[mid] <= target) {
                ans = mid;
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }

        return ans;
    }
}