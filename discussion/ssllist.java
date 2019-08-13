/**
 * @author LM
 * @create 2019-08-12 17:52
 * HW 3: iteratively and recursively write the reverse function for linkedlist
 */
public class ssllist {
    private class IntNode {
        public int item;
        public IntNode next;

        public IntNode(int i, IntNode n) {
            item = i;
            next = n;
        }
    }

    private IntNode head;

    public ssllist(int x) {
        head = new IntNode(x, null);
    }

    public void addFirst(int x) {
        head = new IntNode(x, head);
    }

    public void reverseIterative(){
        if (head == null || head.next == null){
            return;
        }
        IntNode headnext = head.next;
        head.next = null;
        while (headnext != null){
            IntNode temp = headnext.next;
            headnext.next = head;
            head = headnext;
            headnext = temp;
        }
    }

    public void printlist() {
        IntNode temp = head;
        while (temp != null) {
            System.out.print(temp.item + " ");
            temp = temp.next;
        }
        System.out.println();
    }

    /**Implement SLList.insert which takes in an integer x and an integer position.
     * It inserts x at the given position. If position is after the end of the list,
     * insert the new node at the end.
     */

    public void insert(int item, int position) {
        if (head == null || position == 0){
            addFirst(item);
        } else {
            IntNode temphead = head;
            while (position > 1 && temphead.next != null){
                temphead = temphead.next;
                position = position - 1;
            }
            IntNode newNode = new IntNode(item, temphead.next);
            temphead.next = newNode;
        }

    }


    public void reverseRecursive() {
        head = reverseHelper(head);
    }
    private IntNode reverseHelper(IntNode first) {
        if (first == null || first.next == null) {
            return first;
        } else {
            IntNode firstnext = first.next;
            first.next = null;
            IntNode newhead = reverseHelper(firstnext);
            firstnext.next = first;
            return newhead;
        }
    }
        /**
         * } else {
         *             IntNode endofreverse = first.next;
         *             IntNode reversed = reverseHelper(first.next);
         *             endofreverse.next = first;
         *             first.next = null;
         *             return reversed;
         *         }
         */


    public static void main(String[] args) {
        ssllist a = new ssllist(5);
        a.addFirst(3);
        a.addFirst(1);
        a.addFirst(0);
        a.printlist();
        a.reverseRecursive();
        a.printlist();
        a.reverseIterative();
        a.printlist();
        a.insert(4,100);
        a.printlist();
    }
}