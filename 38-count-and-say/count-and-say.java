class Solution {
    public String countAndSay(int n) {
        String res = "1";
        for (int i = 1; i < n; i++) {
            StringBuilder sb = new StringBuilder();
            int count = 1;
            char prev = res.charAt(0);
            
            for (int j = 1; j < res.length(); j++) {
                char curr = res.charAt(j);
                if (curr == prev) {
                    count++;
                } else {
                    sb.append(count).append(prev);
                    prev = curr;
                    count = 1;
                }
            }
            sb.append(count).append(prev);
            res = sb.toString();
        }
        return res;
    }
}
