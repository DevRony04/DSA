class Solution {
    public boolean isMatch(String s, String p) {
        int sLen = s.length(), pLen = p.length();
        int sIdx = 0, pIdx = 0;
        int starIdx = -1, sTmpIdx = -1;

        while (sIdx < sLen) {          
            if (pIdx < pLen && (p.charAt(pIdx) == '?' || p.charAt(pIdx) == s.charAt(sIdx))) {
                ++sIdx;
                ++pIdx;
            }          
            else if (pIdx < pLen && p.charAt(pIdx) == '*') {
                starIdx = pIdx;
                sTmpIdx = sIdx;
                ++pIdx;
            }        
            else if (starIdx != -1) {
                pIdx = starIdx + 1;
                ++sTmpIdx;
                sIdx = sTmpIdx;
            }         
            else {
                return false;
            }
        }
        while (pIdx < pLen && p.charAt(pIdx) == '*') {
            ++pIdx;
        }

        return pIdx == pLen;
    }
}
