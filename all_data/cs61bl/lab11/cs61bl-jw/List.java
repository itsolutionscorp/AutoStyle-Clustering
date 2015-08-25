import java.util.Iterator;

public class List implements Iterable {

	private ListNode myHead;
	private ListNode myTail;
	private int mySize;

	public List() {
		myHead = null;
		myTail = null;
	}

	public boolean isEmpty() {
		return myHead == null & myTail == null;

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
		int rtn = 0;
		for (ListNode p = myHead; p != null; p = p.myNext) {
			rtn++;
		}
		return rtn;
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
	}

	public boolean equals(Object obj) {
		ListNode T = myHead;
		ListNode M = ((List) obj).myHead;
		if (this.size() != ((List) obj).size()) {
			return false;
		}

		while (T != null && M != null) {
			if (!T.myItem.equals(M.myItem)) {
				return false;
			}
			T = T.myNext;
			M = M.myNext;
		}
		return true;
	}

	public void add(Object x) {
		if (mySize == 0) {
			myHead = new ListNode(x);
			myTail = myHead;
		} else {
			ListNode T = new ListNode(x);
			myTail.myNext = T;
			myTail = T;
		}
		mySize++;
	}

	public void appendInPlace(List l) {
		// TODO Your code here
		if (this.isEmpty()) {
			myHead = l.myHead;
			myTail = l.myTail;
			mySize = l.mySize;
		} else if (l.isEmpty()) {
			return;
		} else {
			myTail.myNext = l.myHead;
			this.myTail = l.myHead;
			this.mySize += l.mySize;
		}
	}

	public Iterator iterator() {
		return new ElementIterator();
	}

	public class ElementIterator implements Iterator {

		// State variable(s) to be provided.
		ListNode p;

		public ElementIterator() {
			p = myHead;
		}

		public boolean hasNext() {
			return p != null;
		}

		public Object next() {
			if (p == null) {
				return null;
			}
			Object T = p.myItem;
			p = p.myNext;
			return T;

		}

		public void remove() {
			// not used; do not implement
		}

	}

	public void remove(Object c) {
		if (this.isEmpty()) {
			System.out.println("empty");
			return;
		}
		if (myHead.myItem	.equals(c)) {
			myHead = myHead.myNext;
			mySize--;
		}
		ListNode p = myHead;
		ListNode T = p;
		while (p != null) {
			p = p.myNext;
			if (p == null) {
				return;
			}
			
			if (p.myItem.equals(c)) {
				T.myNext = T.myNext.myNext;
				mySize--;
			}
			T = T.myNext;
		}

	}

	//

	public boolean isOK() {
		ListNode p = myHead;
		int size = 0;
		while (p != null) {
			size++;
			if (p.myItem == null) {
				return false;
			}
			p = p.myNext;
		}
		
		if (size != mySize) {
			return false;
		}

		if (myHead == null & myTail != null | myHead != null & myTail == null) {
			return false;
		}

		return true;
	}

	public ListNode reverse() {
		ListNode T, L;
		T = new ListNode(null);
		L = myHead;
		return reverseHelper(L, T);
	}

	private static ListNode reverseHelper(ListNode l, ListNode soFar) {
		if (l == null) {
			return soFar;
		} else {
			ListNode temp = l.myNext;
			l.myNext = soFar;
			return reverseHelper(temp, soFar);
		}

	}

	public ListNode reverse2() {
		return reverseHelper(myHead);
	}

	private static ListNode reverseHelper(ListNode head) {
		ListNode p, soFar;
		// p plays the role of l in the previous version.
		for (p = head, soFar = null; p != null;) {
			ListNode temp = p;
			temp = p.myNext;
			p.myNext = soFar;
			soFar = p;

		}
		return soFar;
	}

	
}
