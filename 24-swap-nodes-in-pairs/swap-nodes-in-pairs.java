class Solution {
    public ListNode swapPairs(ListNode head) {
        // Dummy node
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode prev = dummy;

        while (prev.next != null && prev.next.next != null) {
            // nodes to swap
            ListNode first = prev.next;
            ListNode second = prev.next.next;

            // swap
            first.next = second.next;
            second.next = first;
            prev.next = second;

            // move prev
            prev = first;
        }

        return dummy.next;
    }
}
