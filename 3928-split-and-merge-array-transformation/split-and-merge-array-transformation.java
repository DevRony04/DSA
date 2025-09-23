class Solution {
    public int minSplitMerge(int[] nums1, int[] nums2) {
         String start = Arrays.toString(nums1);
        String target = Arrays.toString(nums2);

        if (start.equals(target)) return 0;

        Queue<String> queue = new LinkedList<>();
        Map<String, Integer> dist = new HashMap<>();
        
        queue.add(start);
        dist.put(start, 0);
        int n = nums1.length;

        while (!queue.isEmpty()) {
            String curr = queue.poll();
            int steps = dist.get(curr);

            int[] arr = parse(curr);

            for (int L = 0; L < n; L++) {
                for (int R = L; R < n; R++) {
                    int[] block = Arrays.copyOfRange(arr, L, R + 1);
                    int[] remain = new int[n - (R - L + 1)];
                    
                    int idx = 0;
                    for (int i = 0; i < n; i++) {
                        if (i < L || i > R) remain[idx++] = arr[i];
                    }

                    for (int pos = 0; pos <= remain.length; pos++) {
                        int[] newArr = new int[n];
                        int p = 0;

                       
                        for (int i = 0; i < pos; i++) newArr[p++] = remain[i];
                       
                        for (int x : block) newArr[p++] = x;
                        
                        for (int i = pos; i < remain.length; i++) newArr[p++] = remain[i];

                        String key = Arrays.toString(newArr);
                        if (key.equals(target)) return steps + 1;

                        if (!dist.containsKey(key)) {
                            dist.put(key, steps + 1);
                            queue.add(key);
                        }
                    }
                }
            }
        }
        return -1;
    }

    private int[] parse(String s) {
        String[] parts = s.replaceAll("[\\[\\] ]", "").split(",");
        int[] res = new int[parts.length];
        for (int i = 0; i < parts.length; i++) res[i] = Integer.parseInt(parts[i]);
        return res;
    }
}