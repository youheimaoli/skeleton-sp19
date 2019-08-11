public class LinkedListDeque<T> {
	private class Node {
		public Node prev;
		public T item;
		public Node next;

		public Node(Node p, T i, Node n) {
			prev = p;
			item = i;
			next = n;
			//System.out.println(size);
		}
	}
	/* The first item (if it exists) is at sentinel.next. */
	private Node sentinel;
	private int size;

	/* Creates a deep copy of other.*/
	public LinkedListDeque(LinkedListDeque other){
		sentinel = new Node(null, null, null);
		sentinel.next = sentinel;
		sentinel.prev = sentinel;
		size = 0;

		for (int i = 0; i < other.size(); i++){
			addLast((T) other.get(i));
		}

	}

	/*construct an empty DLList, where 12 is an arbitrary integer item*/
	// even for an empty DLList, we make it a circle.
	public LinkedListDeque() {
		sentinel = new Node(null,null, null);
		sentinel.next = sentinel;
		sentinel.prev = sentinel;
		size = 0;
	}

	/**public LinkedListDeque(int x) {
		sentinel = new Node(null,12, null);
		IntNode temp = new IntNode(sentinel, x, sentinel);
		sentinel.next = temp;
		sentinel.prev = temp;
		size = 1;
	}*/

	/** Adds x to the front of the list. */
	public void addFirst(T x) {
		Node temp = new Node(sentinel, x, sentinel.next);
		sentinel.next.prev = temp;
		sentinel.next = temp;
		size = size + 1;
	}

	/** Adds x to the last of the list. */
	public void addLast(T x) {
		Node temp = new Node(sentinel.prev, x, sentinel);
		sentinel.prev.next = temp;
		sentinel.prev = temp;
		size = size + 1;
	}

	/** Returns true if deque is empty, false otherwise. */
	public boolean isEmpty(){
		return size == 0;
	}

	/** Returns the size of the list. take constant time by cache*/
	public int size() {
		return size;
	}

	/** Prints the items in the deque from first to last, separated by a space.
	* Once all the items have been printed, print out a new line.
	// wrong answer, use the dereference so it changes the deque
	public void printDeque(){
		IntNode temp = sentinel;
		for (int i = 0; i < size; i ++){
			System.out.print(temp.next.item + " ");
			temp.next = temp.next.next;
		}
		System.out.println();
	}
	 */
	public void printDeque(){
		Node temp = sentinel.next;
		for (int i = 0; i < size; i ++){
			System.out.print(temp.item + " ");
			temp = temp.next;
		}
		System.out.println();
	}

	/**Removes and returns the item at the front of the deque. If no such item exists, returns null.*/
	public T removeFirst(){
		if (isEmpty()){
			return null;
		} else {
			Node head = sentinel.next;
			sentinel.next = sentinel.next.next;
			sentinel.next.prev = sentinel;
			size = size - 1;
			return head.item;
		}
	}

	/**Removes and returns the item at the last of the deque. If no such item exists, returns null.*/
	public T removeLast(){
		if (isEmpty()){
			return null;
		} else {
			Node tail = sentinel.prev;
			sentinel.prev = sentinel.prev.prev;
			sentinel.prev.next = sentinel;
			size = size - 1;
			return tail.item;
		}
	}

	/**Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth.
	 *  If no such item exists, returns null.
	 *  Must not alter the deque and must use iteration.*/
	public T get(int index) {
		if (index > size){
			return null;
		}
		Node temp = sentinel.next;
		while (index > 0){
			temp = temp.next;
			index = index - 1;
		}
		return temp.item;
	}

	/** Same as get, but uses recursion. */
	private T getRecursiveHelper(int index, Node node){
		if (index > size){
			return null;
		}
		Node curr = node;
		if (index == 0){
			return curr.item;
		}
		curr = curr.next;
		return getRecursiveHelper(index - 1, curr);

	}

	public T getRecursive(int index){
		return getRecursiveHelper(index, sentinel.next);
	}

	/**
	public static void main(String[] args) {
 		/* Creates a list of one integer, namely 10
 		LinkedListDeque l = new LinkedListDeque();
 		//LinkedListDeque L = new LinkedListDeque(5);
 		l.addFirst(3);
 		l.addLast(10);
 		l.addLast(20);
		l.printDeque();
		System.out.println(l.getRecursive(0));

 		System.out.println(l.removeLast());

		System.out.println(l.size());
		l.printDeque();
		l.printDeque();
		System.out.println(l.get(0));

 	}
 	*/


}

