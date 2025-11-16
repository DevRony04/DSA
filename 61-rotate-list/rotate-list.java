class Solution {
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null || k == 0) return head;

        int len = 1;
        ListNode tail = head;
        while (tail.next != null) {
            tail = tail.next;
            len++;
        }

        tail.next = head;

        k = k % len;
        int stepsToNewHead = len - k;

        ListNode newTail = tail;
        while (stepsToNewHead-- > 0) {
            newTail = newTail.next;
        }

        // 5. Break the circle
        ListNode newHead = newTail.next;
        newTail.next = null;

        return newHead;
    }
}
