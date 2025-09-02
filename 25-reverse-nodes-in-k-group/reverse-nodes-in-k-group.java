class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || k == 1) return head;

        // Dummy node
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prevGroupEnd = dummy;

        while (true) {
            // Find kth node
            ListNode kth = getKthNode(prevGroupEnd, k);
            if (kth == null) break; // not enough nodes left

            ListNode groupStart = prevGroupEnd.next;
            ListNode nextGroupStart = kth.next;

            // Reverse group
            ListNode prev = nextGroupStart;
            ListNode curr = groupStart;

            while (curr != nextGroupStart) {
                ListNode tmp = curr.next;
                curr.next = prev;
                prev = curr;
                curr = tmp;
            }

            // Connect reversed group back
            prevGroupEnd.next = kth;
            prevGroupEnd = groupStart;
        }

        return dummy.next;
    }

    // Helper: get kth node from current
    private ListNode getKthNode(ListNode curr, int k) {
        while (curr != null && k > 0) {
            curr = curr.next;
            k--;
        }
        return curr;
    }
}
