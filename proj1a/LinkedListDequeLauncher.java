public class LinkedListDequeLauncher{
	public static void main(String[] args) {
 		/* Creates a list of one integer, namely 10 */
		/** test for Integer
 		LinkedListDeque<Integer> l = new LinkedListDeque();
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
		*/

		/* test for type String*/
		LinkedListDeque<String> L = new LinkedListDeque();
		L.addFirst("first");
		L.addLast("second");
		L.addLast("third");
		System.out.println(L.isEmpty());
		L.printDeque();
		System.out.println(L.getRecursive(0));

		System.out.println(L.removeLast());

		System.out.println(L.size());
		LinkedListDeque<String> l = new LinkedListDeque(L);
		l.printDeque();
	}
}