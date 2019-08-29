import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class MyHashMap<K , V> implements Map61B<K,V> {

    /** A new, empty mapping using a hash table that initially has
     * INITIALBINS bins, and maintains a load factor <= LOADFACTOR. */

    private class Entry {
        K key;
        V value;
        Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private ArrayList<ArrayList<Entry>> buckets;
    private HashSet<K> keySet;
    private int numOfEntries = 0;
    private int numOfBuckets;
    private double maxAvgBucketSize;

    /** An empty map with default initial bins and load factor 0.75. */
    public MyHashMap () { this (16, 0.75); }

    /** An empty map with INITIALBINS initial bins and load factor 0.75. */
    public MyHashMap (int initialBins) { this (initialBins, 0.75); }

    public MyHashMap(int initialSize, double loadFactor) {
        keySet = new HashSet<>();
        buckets = new ArrayList<>();
        numOfBuckets = initialSize;
        maxAvgBucketSize = loadFactor;
        for (int i = 0; i < numOfBuckets; i++) {
            buckets.add(new ArrayList<Entry>());
        }
    }



    /** Removes all of the mappings from this map. */
    @Override
    public void clear() {
        keySet = new HashSet<>();
        buckets = new ArrayList<>();
        numOfBuckets = 0;
        numOfEntries = 0;
    }

    /** Returns true if this map contains a mapping for the specified key. */
    @Override
    public boolean containsKey(K key){
        return(keySet.contains(key));
    }

    /**
     * Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    @Override
    public V get(K key) {
        if (!containsKey(key)) {
            return null;
        }
        int index = hash(key, numOfBuckets);
        for (Entry entry : buckets.get(index)) {
            if (entry.key.equals(key)) {
                return entry.value;
            }
        }
        return null;
    }

    private int hash(K key, int capacity) {
        return Math.floorMod(key.hashCode(), capacity);
    }

    /** Returns the number of key-value mappings in this map. */
    @Override
    public int size() {
        return numOfEntries;
    }

    /**
     * Associates the specified value with the specified key in this map.
     * If the map previously contained a mapping for the key,
     * the old value is replaced.
     */
    @Override
    public void put(K key, V value) {
        if (containsKey(key)) {
            update(key, value);
            return;
        }
        if (size() >= maxAvgBucketSize * numOfBuckets) {
            resize(numOfBuckets * 2);
        }
        int index = hash(key, numOfBuckets);
        buckets.get(index).add(new Entry(key, value));
        keySet.add(key);
        numOfEntries += 1;
    }

    private void resize(int capacity) {
        ArrayList<ArrayList<Entry>> newBuckets = new ArrayList<>();
        for (int i = 0; i < capacity; i++) {
            newBuckets.add(new ArrayList<>());
        }
        for (K key : this) {
            int index = hash(key, capacity);
            newBuckets.get(index).add(new Entry(key, get(key)));
        }
        this.numOfBuckets = capacity;
        this.buckets = newBuckets;
    }

    private void update(K key, V value) {
        int index = hash(key, numOfBuckets);
        for (Entry entry : buckets.get(index)) {
            if (entry.key.equals(key)) {
                entry.value = value;
            }
        }
    }

    /** Returns a Set view of the keys contained in this map. */
    @Override
    public Set<K> keySet() {
        return keySet;
    }

    /**
     * Removes the mapping for the specified key from this map if present.
     * Not required for Lab 8. If you don't implement this, throw an
     * UnsupportedOperationException.
     */
    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    /**
     * Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for Lab 8. If you don't implement this,
     * throw an UnsupportedOperationException.
     */
    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<K> iterator() {
        return keySet.iterator();
    }
}