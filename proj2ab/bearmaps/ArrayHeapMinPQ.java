package bearmaps;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.NoSuchElementException;

/**
 * @author LM
 * @create 2019-08-28 15:14
 */
public class ArrayHeapMinPQ<T> implements ExtrinsicMinPQ<T>{
    /* Adds an item with the given priority value. Throws an
     * IllegalArgumentExceptionb if item is already present.
            * You may assume that item is never null. */

    private ArrayList<Node> heap;   //itemPQ
    private HashMap<T,Integer> items;  //itemMapIndex;

    public class Node {
        T item;
        double priority;

        public Node (T item, double priority) {
            this.item = item;
            this.priority = priority;
        }
    }

    public ArrayHeapMinPQ() {
        heap = new ArrayList<>();
        items = new HashMap<>();
    }

    private static int parent(int i) {
        return (i - 1) / 2;
    }

    private static int leftchild(int i) {
        return 2 * i + 1;
    }

    private static int rightchild(int i) {
        return 2 * i + 2;
    }

    private void swap(int i, int j) {
        Node temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
        items.put(heap.get(i).item, i);
        items.put(heap.get(j).item, j);
    }

    private boolean less(int i, int j) {
        return heap.get(i).priority < heap.get(j).priority;
    }

    /***************************************************************************
     * Helper functions to restore the heap invariant.
     ***************************************************************************/

    private void swim(int k) {
        while (k > 0) {
            int p = parent(k);
            if (!less(k, p)) break;
            swap (k, p);
            k = p;


        }
    }

    private void sink(int k) {
        while (leftchild(k) < size()) {
            int j = leftchild(k);
            if (rightchild(j) < heap.size() && less(rightchild(k), j)) j++;
            if (less(k, j)) break;
            swap(k, j);
            k = j;
        }
    }

    @Override
    public void add(T item, double priority) {
        if (contains(item)) {
            throw new IllegalArgumentException();
        }
        heap.add(new Node(item, priority));
        items.put(item, size() - 1); //?? what's this hashmap for?
        swim(size() - 1);
    }

    /* Returns true if the PQ contains the given item. */
    @Override
    public boolean contains(T item) {
        return items.containsKey(item);
    }

    /* Returns the minimum item. Throws NoSuchElementException if the PQ is empty. */
    @Override
    public T getSmallest(){
        if (size() == 0) {
            throw new NoSuchElementException();
        }
        return heap.get(0).item;
    }

    /* Removes and returns the minimum item. Throws NoSuchElementException if the PQ is empty. */
    @Override
    public T removeSmallest() {
        if (size() == 0) {
            throw new NoSuchElementException();
        }
        T min = getSmallest();
        swap(0, size() - 1);
        sink(0);
        heap.remove(size() - 1);
        items.remove(min);
        return min;
    }



    /* Returns the number of items in the PQ. */
    @Override
    public int size() {
        return heap.size();
    }
    /* Changes the priority of the given item. Throws NoSuchElementException if the item
     * doesn't exist. */
    @Override
    public void changePriority(T item, double priority){
        if (!contains(item)) {
            throw new NoSuchElementException();
        }
        int i = items.get(item);
        double oldpriority = heap.get(i).priority;
        heap.get(i).priority = priority;
        if (oldpriority < priority) {
            sink(i);
        }
        else {
            swim(i);
        }
    }

    public static void main(String[] args) {
        //Integer[] example = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16};
        PrintHeapDemo printHeapDemo = new PrintHeapDemo();
        ExtrinsicMinPQ<Integer> example = new ArrayHeapMinPQ<>();
        example.add(20, 99);
        example.add(15, 88);
        example.add(10, 77);

    }

}
