import java.util.*;

class Solution {
    public List<String> letterCombinations(String digits) {
        if (digits == null || digits.length() == 0) return new ArrayList<>();

        Map<Character, String> phoneMap = new HashMap<>();
        phoneMap.put('2', "abc");
        phoneMap.put('3', "def");
        phoneMap.put('4', "ghi");
        phoneMap.put('5', "jkl");
        phoneMap.put('6', "mno");
        phoneMap.put('7', "pqrs");
        phoneMap.put('8', "tuv");
        phoneMap.put('9', "wxyz");

        List<String> res = new ArrayList<>();
        res.add("");

        for (char digit : digits.toCharArray()) {
            List<String> temp = new ArrayList<>();
            for (String combo : res) {
                for (char ch : phoneMap.get(digit).toCharArray()) {
                    temp.add(combo + ch);
                }
            }
            res = temp;
        }

        return res;
    }
}
