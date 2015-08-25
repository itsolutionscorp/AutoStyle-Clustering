import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public class List implements Iterable {

	private ListNode myHead;
	private int mySize;
	private ListNode myTail;
	private ListNode tempI;

	public List() {
		myHead = null;
		mySize = 0;
		myTail = null;

	}

	public boolean isEmpty() {
		return myHead == null;
	}

	private static class ListNode {

		private Object myItem;
		private ListNode myNext;

		private ListNode(Object item, ListNode next) {
			myItem = item;
			myNext = next;
		}

		private ListNode(Object item) {
			myItem = item;
			myNext = null;
		}

	}

	public String toString() {
		String rtn = "( ";
		for (ListNode p = myHead; p != null; p = p.myNext) {
			rtn = rtn + p.myItem + " ";
		}
		return rtn + ")";
	}

	// Return the number of items in this list ("length" in Scheme).
	public int size() {
		// mySize = 0;
		// for (ListNode p = myHead; p != null; p = p.myNext) {
		// mySize++;
		// }
		return mySize;
	}

	// Return true if the list contains the object
	public boolean contains(Object obj) {
		for (ListNode p = myHead; p != null; p = p.myNext) {
			if (obj.equals(p.myItem)) {
				return true;
			}
		}
		return false;
	}

	// Returns the element at the given position in this list.
	public Object get(int pos) {
		if (pos < 0) {
			throw new IllegalArgumentException(
					"Argument to get must be at least 0.");
		}
		if (pos >= size()) {
			throw new IllegalArgumentException("Argument to get is too large.");
		}
		int k = 0;
		for (ListNode p = myHead; p != null; p = p.myNext) {
			if (k == pos) {
				return p.myItem;
			}
			k++;
		}
		return null;
	}

	public void addToFront(Object obj) {
		myHead = new ListNode(obj, myHead);
		mySize++;
	}

	public boolean equals(Object obj) {

		if (obj == null) {
			return false;
		}
		if (size() != ((List) obj).size()) {
			return false;
		}
		for (int k = 0; k < size(); k++) {
			if (get(k) != ((List) obj).get(k)) {
				return false;
			}
		}
		return true;

	}

	public void add(Object x) {
		mySize++;
		ListNode l = new ListNode(x);
		if (isEmpty()) {
			myHead = l;
			myTail = l;

		} else {
			ListNode temp = myTail;
			myTail = l;
			temp.myNext = l;
		}

	}

	public void appendInPlace(List l) {
		if (l == null || l.isEmpty()) {
			return;
		}
		mySize += l.mySize;
		if (isEmpty()) {
			myHead = l.myHead;
		} else {
			myTail.myNext = l.myHead;
		}
		myTail = l.myTail;
	}

	public void remove(Object x) {
		if (isEmpty()) {
			return;
		}
		Iterator iter = iterator();
		int acc = 0;
		ListNode pre = null;
		while (iter.hasNext()) {
			if (!(iter.next().equals(x))) {
				acc++;
				if (acc == 1) {
					myHead = tempI;
					pre = tempI;
				} else {
					pre.myNext = tempI;
					pre = tempI;
				}
			}
		}
		if (acc == 0) {
			myHead = null;
		} else if (pre.myNext != null) {
			pre.myNext = null;
		}
		myTail = pre;
		mySize = acc;
		// ListNode current = myHead;
		// ListNode previous = null;
		//
		// while (current != null) {
		// while (previous == null) {
		// if (current.myItem.equals(x)) {
		// current = current.myNext;
		// mySize--;
		//
		// } else {
		// previous = current;
		// myHead = previous;
		// current = current.myNext;
		//
		// }
		// }
		//
		// if (current.myItem.equals(x)) {
		// current = current.myNext;
		// mySize--;
		// } else {
		// previous.myNext = current;
		// previous = current;
		// current = current.myNext;
		// }
		//
		// }
		// if (previous.myItem.equals(x)) {
		//
		// }
		// myTail = previous;
		// myTail.myNext = null;

	}

	public void doubleInPlace() {
		if (isEmpty()) {
			return;
		}
		int acc = 0;
		for (ListNode p = myHead; p != null; p = p.myNext) {
			if (acc % 2 == 0) {
				ListNode duplicate = new ListNode(p.myItem, p.myNext);
				p.myNext = duplicate;
			}
			acc++;
		}

		myTail = myTail.myNext;
		mySize *= 2;
		// TODO And maybe here as well
	}

	public void reverse() {
		if (isEmpty()) {
			return;
		}
		ListNode previous = null;
		ListNode current = myHead;
		myHead = myTail;
		myTail = current;
		while (current != null) {

			ListNode tempC = current;
			current = current.myNext;
			tempC.myNext = previous;

			previous = tempC;

		}

	}

	public static void reverseHelper(ListNode l, ListNode soFar) {
		// ...
	}

	public boolean isOK() {
		int count = 0;
		ListNode fakeHead = null;
		ListNode fakeTail = null;
		boolean done = true;
		for (ListNode p = myHead; p != null; p = p.myNext) {
			if (done) {
				done = false;
				fakeHead = p;
			}
			if (p.myItem == null) {
				return false;
			}

			count++;
			if (p.myNext == null) {
				fakeTail = p;
			}

		}

		boolean nullHeadOK = (myTail == null && myHead == null);
		boolean tailIsOK = nullHeadOK || (fakeTail == myTail);
		boolean countCheck = (count == mySize);
		boolean headIsOK = (myHead == fakeHead) || nullHeadOK;
		boolean headersOK = headIsOK && tailIsOK;

		return countCheck && headersOK;
	}

	public Iterator iterator() {
		return new ElementIterator();
	}

	public class ElementIterator implements Iterator {

		// State variable(s) to be provided.
		int index;
		private ListNode iterHead;

		public ElementIterator() {
			// TODO code to be provided
			index = 0;
			iterHead = myHead;
		}

		public boolean hasNext() {
			return index < size();
		}

		public Object next() {
			index++;
			tempI = iterHead;
			iterHead = iterHead.myNext;
			return tempI.myItem;
		}

		public void remove() {
			// not used; do not implement
		}
	}

	public static void main(String[] args) {
		ArrayList<Integer> al = new ArrayList<Integer>();
		LinkedList l = new LinkedList();
		Timer t = new Timer();

		int numToPut = 10000;
		int numToDo = 50000;

		// for (int k = 0; k < numToDo; k++) {
		// al.add(numToPut);
		// }
		// t.start();
		// for (int k = 0; k < numToDo; k++) {
		// al.get(k);
		// }
		// System.out.println("array list takes " + t.stop() + "nano second");
		for (int k = 0; k < numToDo; k++) {
			l.add(numToPut);
		}

		t.start();
		for (int k = 0; k < numToDo; k++) {
			l.get(k);
		}
		System.out.println("doubly linked list takes " + t.stop()
				+ "nano second");

		List sList = new List();

		for (int k = 0; k < numToDo; k++) {
			sList.add(numToPut);
		}
		t.start();
		for (int k = 0; k < numToDo; k++) {
			sList.get(k);
		}
		System.out.println("single linked list takes " + t.stop()
				+ "nano second");

	}

}
