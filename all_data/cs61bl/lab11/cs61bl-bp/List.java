import java.util.Iterator;

public class List implements Iterable {

	private ListNode myHead;
	private int mySize;
	private ListNode myTail;

	public List() {
		myHead = null;
		myTail = null;
		mySize = 0;
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
		if (myTail == null) {
			myTail = myHead;
		}
	}

	public boolean equals(Object obj) {
		try {
			ListNode current = ((List) obj).myHead;
			if (this.size() != ((List) obj).size()) {
				return false;
			}
			for (ListNode p = myHead; p != null; p = p.myNext) {
				if (!p.myItem.equals(current.myItem)) {
					return false;
				}
				current = current.myNext;
			}
		} catch (ClassCastException e) {
			return false;
		}
		return true;
	}

	public void add(Object x) {
		if (myHead == null) {
			myHead = new ListNode(x, null);
			myTail = myHead;
		} else {
			myTail.myNext = new ListNode(x, null);
			myTail = myTail.myNext;
		}
		mySize++;
	}

	public void appendInPlace(List l) {
		if (myHead == null) {
			myHead = l.myHead;
			myTail = l.myTail;
		} else {
			if (l.size() > 0) {
				myTail.myNext = l.myHead;
				myTail = l.myTail;
			}
		}
		mySize += l.mySize;
	}

	public Iterator iterator() {
		return new ElementIterator();
	}

	public class ElementIterator implements Iterator {

		// State variable(s) to be provided.
		ListNode pointer;

		public ElementIterator() {
			pointer = myHead;
		}

		public boolean hasNext() {
			return pointer != null;
		}

		public Object next() {
			ListNode t = pointer;
			pointer = t.myNext;
			return t.myItem;
		}

		public void remove() {
			// not used; do not implement
		}
	}

	public boolean isOK() {
		int count = 0;

		if ((myHead == null && myTail != null)
				|| (myHead != null && myTail == null)) {
			System.out.println("null head/tail");
			return false;
		}

		for (ListNode p = myHead; p != null; p = p.myNext) {
			count++;
			if (p.myItem == null) {
				System.out.println("null item");
				return false;
			}

			if (count == mySize) {
				if (myTail != p) {
					System.out.println("tail mismatch");
					return false;
				}
			}
		}
		if (count != mySize) {
			System.out.println(count + " size " + mySize);
			System.out.println("size error");
			return false;
		}

		return true;
	}

	public void remove(Object x) {
		ListNode prev = null;
		for (ListNode p = myHead; p != null; p = p.myNext) {
			if (p.myItem.equals(x)) {
				if (prev == null) {
					myHead = p.myNext;
				} else {
					prev.myNext = p.myNext;
				}
			} else {
				prev = p;
			}
		}
	}

	public void doubleInPlace() {
		for (ListNode p = myHead; p != null; p = p.myNext) {
			ListNode temp = p.myNext;
			p.myNext = new ListNode(p.myItem, temp);
			p = p.myNext;
		}
	}
	
	public void reverse() {
		myHead = reverseHelper(myHead);
	}
	
	public static ListNode reverseHelper(ListNode l, ListNode soFar) {
		if (l == null)
			return soFar;
		else {
			ListNode temp = l.myNext;
			l.myNext = soFar;
			return reverseHelper(temp , l);
		}
	}
	
	private static ListNode reverseHelper(ListNode head) {
	    ListNode p, soFar;
	    // p plays the role of l in the previous version.
	    for (p = head, soFar = null; p != null;) {
	        ListNode temp = p.myNext;
	        p.myNext = soFar;
	        soFar = p;
	        p = temp;
	    }
	    return soFar;
	}
}
