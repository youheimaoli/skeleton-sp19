import java.util.Iterator;
import java.util.Set;


public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {
    private Node root;

    private class Node{
        private K key;          // sorted by key
        private V val;         // associated data
        private Node left, right;  // left and right subtrees
        private int size;          // number of nodes in subtree

        public Node(K key, V val, int size){
            this.key = key;
            this.val = val;
            this.size = size;
        }

    }

    public BSTMap() {
    }
    // Initializes an empty symbol table.


    /** Removes all of the mappings from this map. */
    @Override
    public void clear(){
        root = null;
    }

    /* Returns true if this map contains a mapping for the specified key. */

    @Override
    public boolean containsKey(K key){
        //if (key == null) {
        //    throw new IllegalArgumentException("argument to contains() is null");
        //}
        return (get(key) != null);

    }

    /* Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    @Override
    public V get(K key){
        return get(root, key);
    }

    private V get(Node x, K key) {
        if (x == null){
            //throw new IllegalArgumentException("calls get() with a null key");
            return null;
        }
        int cmp = key.compareTo(x.key);
        if (cmp < 0){
            return get(x.left, key);
        } else if (cmp > 0) {
            return get(x.right, key);
        } else {
            return x.val;
        }
    }

    /* Returns the number of key-value mappings in this map. */
    @Override
    public int size() {
        return size(root);
    }

    private int size(Node x) {
        if (x == null){
            return 0;
        }
        return x.size;
    }

    /* Associates the specified value with the specified key in this map. */
    @Override
    public void put(K key, V value) {
        root = put(key, value, root);
    }

    private Node put (K key, V value, Node x) {
        if (x == null) {
            return new Node(key, value, 1);
        }
        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            x.left = put(key, value, x.left);
        } else if (cmp > 0) {
            x.right = put(key, value, x.right);
        } else {
            x.val = value;
        }
        x.size = size(x.left) + size(x.right) + 1;
        return x;
    }

    /* Returns a Set view of the keys contained in this map. */
    @Override
    public Set<K> keySet() {
        throw new UnsupportedOperationException();
    }

    /* Removes the mapping for the specified key from this map if present.
     * Not required for Lab 8. If you don't implement this, throw an
     * UnsupportedOperationException. */
    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    /* Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for Lab 8. If you don't implement this,
     * throw an UnsupportedOperationException.*/
    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<K> iterator() {
        throw new UnsupportedOperationException();
    }

    private void printInOrder(Node x) {
        if (x == null) {
            throw new IllegalArgumentException("the tree is null");
        }
        if (x.left == null && x.right == null) {
            printNode(x);
        } else if (x.right == null) {
            printInOrder(x.left);
            printNode(x);
        } else if (x.left == null) {
            printNode(x);
            printInOrder(x.right);
        } else {
            printInOrder(x.left);
            printNode(x);
            printInOrder(x.right);
        }

    }

    private void printNode(Node x) {
        System.out.print(x.key + " " + x.val);
        System.out.println();
    }

    public static void main(String arg[]){
        BSTMap<String, Integer> tree = new BSTMap<>();
        tree.clear();

        System.out.println(tree.size());
        tree.put("abc", 10);
        tree.put("aaa", 0);
        tree.put("abb", 16);
        tree.put("xyz", 12);
        System.out.println(tree.get("absc"));
        tree.printInOrder(tree.root);

    }



}