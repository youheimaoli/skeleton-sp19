import java.util.Arrays;



public class UnionFind {

    // TODO - Add instance variables?
    private int[] parent;
    private int[] size;
    private int count;

    /* Creates a UnionFind data structure holding n vertices. Initially, all
       vertices are in disjoint sets. */
    public UnionFind(int n) {
        parent = new int[n];
        size = new int[n];
        count = n;
        for (int i = 0; i < n; i++) {
            parent[i] = -1;
            size[i] = 1;
        }
    }

    /* Throws an exception if v1 is not a valid index. */
    private void validate(int vertex) {
        int n = parent.length;
        if (vertex < 0 || vertex >= n){
            throw new IllegalArgumentException();
        }
    }

    public int count() {
        return count;
    }

    /* Returns the size of the set v1 belongs to. */
    public int sizeOf(int v1) {
        // TODO
        return size[find(v1)];
    }

    /* Returns the parent of v1. If v1 is the root of a tree, returns the
       negative size of the tree for which v1 is the root. */
    public int parent(int v1) {
        if (parent[v1] < 0){
            return -1;
        }   else {
            return parent[v1];
        }
    }

    /* Returns true if nodes v1 and v2 are connected. */
    public boolean connected(int v1, int v2) {
        return find(v1) == find(v2);
    }

    /* Connects two elements v1 and v2 together. v1 and v2 can be any valid 
       elements, and a union-by-size heuristic is used. If the sizes of the sets
       are equal, tie break by connecting v1's root to v2's root. Unioning a 
       vertex with itself or vertices that are already connected should not 
       change the sets but may alter the internal structure of the data. */
    public void union(int v1, int v2) {
        int i = find(v1);
        int j = find(v2);
        if (i == j){ return; }
        if (size[i] <= size[j]){
            parent[i] = j;
            size[j] = size[j] + size[i];
        } else {
            parent[j] = i;
            size[i] = size[i] + size[j];
        }
        count --;
    }

    /* Returns the root of the set V belongs to. Path-compression is employed
       allowing for fast search-time. */
    //naive way
    public int find(int vertex) {
        validate(vertex);
        int root = vertex;
        while (parent[root] >= 0){
            root = parent[root];
        }
        int tmp;
        while (vertex != root) {
            tmp = parent(vertex);
            parent[vertex] = root;
            vertex = tmp;
        }
        return root;
    }
    // use path-compression
    public static void main(String[] args) {
        int n = 10;
        UnionFind uf = new UnionFind(n);
        uf.union(3,4);
        uf.union(1,2);
        uf.union(1,0);
        uf.union(2,3);
        uf.union(1,9);
        uf.union(9,3);
        uf.find(3);
        System.out.println(uf.count());
        System.out.print( Arrays.toString(uf.parent));
    }

}
