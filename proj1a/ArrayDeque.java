public class ArrayDeque<T>{
/** Array based list.
 *  @author Lihui Mao
 */

//         0 1  2 3 4 5 6 7
// items: [6 9 -1 2 0 0 0 0 ...]
// size: 5

/* Invariants:
 addLast: The next item we want to add, will go into position size
 getLast: The item we want to return is in position size - 1
 size: The number of items in the list should be size.
*/
    private T[] items;
    private int nextFirst;
    private int nextLast;
    private int size;
    private static final int initialSize = 8;
    private static final int factor = 2;
    private static final double ratio = 0.25;

    /** Creates an empty list.
     *items = "new Item[initialSize]"; does not work,
     * just use "(Item[]) new Object[initialSize]" for now */
    public ArrayDeque() {
        items = (T[]) new Object[initialSize];
        nextFirst = 2;
        nextLast = 3;
        size = 0;
    }

    /** Creates a deep copy of other. */
    public ArrayDeque(ArrayDeque other) {
        items = (T[]) new Object[initialSize];
        size = 0;
        nextFirst = 4;
        nextLast = 5;
        for (int i = 0; i < other.size(); i++) {
            addLast((T) other.get(i));
        }
    }

    private int minusOne(int index){
        return (index - 1 + items.length) % items.length;
    }

    private int plusOne(int index){
        return (index + 1) % items.length;
    }

    /** build a new arraylist (multiplicity factor = 2) with size updated, then copy the items into the new one
     * the newarray starts at position 0 and ends at position size - 1*/
    private void resize(int capacity){
        T[] newItems = (T[]) new Object[capacity];

        int curr = plusOne(nextFirst);
        for (int i = 0; i < size; i ++){
            newItems[i] = items[curr];
            curr = plusOne(curr);
        }
        items = newItems;
        nextFirst = capacity - 1;
        nextLast = size;
    }

    /** Adds an item of type T to the front of the deque.
     * Q?: what if the index crosses the size*/
    public void addFirst(T item){
        if (size == items.length){
            resize (size * factor);
        }
        items[nextFirst] = item;
        nextFirst = minusOne(nextFirst);
        size = size + 1;
    }

    /** Inserts X into the back of the list. */
    public void addLast(T item) {
        if (size == items.length) {
            resize(size * factor);
        }
        items[nextLast] = item;
        nextLast = plusOne(nextLast);
        size = size + 1;
    }

    /**Returns true if deque is empty, false otherwise.*/
    public boolean isEmpty(){
        return size == 0;
    }

    /** Returns the number of items in the list. */
    public int size() {
        return size;
    }

    /**Prints the items in the deque from first to last, separated by a space.
     * Once all the items have been printed, print out a new line.*/
    public void printDeque(){
        for (int i = 0; i < size; i++){
            System.out.print(items[plusOne(i + nextFirst)] + " ");
        }
        System.out.println();
    }

    /** Deletes item from front of the list and
     * returns deleted item. */
    public T remvoeFirst() {
        if (isEmpty()){
            return null;
        }
        T x = items[plusOne(nextFirst)];
        nextFirst = plusOne(nextFirst);
        items[nextFirst] = null;
        size = size - 1;

        if (items.length > 16 && size < items.length * ratio){
            resize(items.length / 2);
        }

        return x;
    }

    /** Deletes item from back of the list and
     * returns deleted item. */
    public T removeLast() {
        if (isEmpty()){
            return null;
        }
        T x = items[minusOne(nextLast)];
        nextLast = minusOne(nextLast);
        items[nextLast] = null;
        size = size - 1;

        if (items.length > 16 && size < items.length * ratio){
            resize(items.length / 2);
        }

        return x;
    }

    /**Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth.
     * If no such item exists, returns null. Must not alter the deque!
     */
    public T get(int index){
        if (index > size) {
            return null;
        }
        index = (plusOne(nextFirst) + index) % items.length;
        return items[index];
    }
/**
    public static void main(String[] args) {
 		ArrayDeque<Integer> A = new ArrayDeque();
 		A.addFirst(5);
 		A.addLast(10);
 		A.addLast(15);
 		A.addFirst(1);
 		A.addLast(20);

 		A.addLast(25);
 		A.addLast(30);
 		A.addLast(35);
 		A.addLast(40);
 		A.printDeque();
 		A.remvoeFirst();
 		A.remvoeFirst();
 		A.removeLast();
 		A.removeLast();
 		A.removeLast();
 		A.removeLast();
 		A.removeLast();
 		A.printDeque();
 		System.out.println(A.size());
 	}
 */
}