// Definition for singly-linked list.
class ListNode {
    int val;
    ListNode next;
    
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    
    // Add deserialize method for testing framework compatibility
    public static ListNode deserialize(String data) {
        if (data == null || data.equals("[]")) {
            return null;
        }
        
        // Remove brackets and split by comma
        data = data.substring(1, data.length() - 1);
        String[] values = data.split(",");
        
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;
        
        for (String val : values) {
            val = val.trim();
            if (!val.equals("null")) {
                current.next = new ListNode(Integer.parseInt(val));
                current = current.next;
            }
        }
        
        return dummy.next;
    }
    
    // Optional: Add serialize method for debugging
    public static String serialize(ListNode head) {
        if (head == null) return "[]";
        
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        
        while (head != null) {
            sb.append(head.val);
            head = head.next;
            if (head != null) {
                sb.append(",");
            }
        }
        
        sb.append("]");
        return sb.toString();
    }
}

public class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0); 
        ListNode current = dummy;
        int carry = 0;
        
        while (l1 != null || l2 != null || carry != 0) {
            int sum = carry;
            
            if (l1 != null) {
                sum += l1.val;
                l1 = l1.next;
            }
            
            if (l2 != null) {
                sum += l2.val;
                l2 = l2.next;
            }
            
            carry = sum / 10;
            current.next = new ListNode(sum % 10);
            current = current.next;
        }
        
        return dummy.next;
    }
}