/** Array based list.
 *  @author Josh Hug
 */

//         0 1  2 3 4 5 6 7
// items: [6 9 -1 2 0 0 0 0 ...]
// size: 5

/* Invariants:
 addLast: The next item we want to add, will go into position size
 getLast: The item we want to return is in position size - 1
 size: The number of items in the list should be size.
*/

public class AList {
    private int[] items;
    private int size;

    /** Creates an empty list. */
    public AList() {
        items = new int[20];
        size = 0;
    }

    /** Resizes the underlying array to the target capacity. */
    private void resize(int capacity) {
        int[] a = new int[capacity];
        System.arraycopy(items, 0, a, 0, size);
        items = a;
    }

    /** Inserts X into the back of the list. */
    public void addLast(int x) {
        if (size == items.length) {
            resize(size + 100);
        }

        items[size] = x;
        size = size + 1;
    }

    /** Returns the item from the back of the list. */
    public int getLast() {
        return items[size - 1];
    }
    /** Gets the ith item in the list (0 is the front). */
    public int get(int i) {
        return items[i];
    }

    /** Returns the number of items in the list. */
    public int size() {
        return size;
    }

    /** Deletes item from back of the list and
      * returns deleted item. */
    public int removeLast() {
        int x = getLast();
        size = size - 1;
        return x;
    }

    /**Consider a method that inserts an int item into an int[] arr at the given position.
     * The method should return the resulting array. For example, if x = [5, 9, 14, 15], item = 6, and position = 2,
     * then the method should return [5, 9, 6, 14, 15].
     * If position is past the end of the array, insert item at the end of the array.
     */
    //because arrays have a fixed size, so to add an element, you need to create a new array.
    public static int[] insert(int[] arr, int item, int position) {
        int[] result = new int[arr.length + 1];
        position = Math.min(position, arr.length);

        for (int i = 0; i < position; i++){
            result[i] = arr[i];
        }
        result[position] = item;
        for (int i = position + 1; i < arr.length; i++){
            result[i] = arr[i];
        }
        return result;
    }

    /**Consider a method that destructively reverses the items in arr.
     * For example calling reverse on an array [1, 2, 3] should change the array to be [3, 2, 1]. Write the reverse method:
     */


    public static int[] reverse(int[] arr) {
        for (int i = 0; i < arr.length / 2; i++){
            int j = arr.length - 1 - i;
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
        return arr;
    }

    /** Consider a method that destructively reverses the items in arr.
    For example calling reverse on an array [1, 2, 3] should change the array to be [3, 2, 1]. Write the reverse method:
     */
    public static int[] replicate(int[] arr) {
        int total = 0;
        for (int item : arr){
            total += item;
        }
        int[] result = new int[total];
        int i = 0;
        for (int item : arr){
            for (int count = 0; count < item; count ++){
                result[i] = item;
                i++;
            }
        }
        return result;
    }




        public void printDeque(){
        for (int i = 0; i < size; i++){
            System.out.print(items[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args){
        AList L = new AList();
        L.addLast(1);
        L.addLast(2);
        L.addLast(3);
        L.addLast(4);
        L.addLast(5);
        L.printDeque();
        int[] k = new int[]{2,3,4};
        int[] b = insert(k, 10, 100);
        int[] c = reverse(k);
        int[] d = replicate(k);

        for (int i = 0; i < d.length; i++){
            System.out.print(d[i] + " ");
        }
        System.out.println();
        System.out.print(5/2);

    }
}