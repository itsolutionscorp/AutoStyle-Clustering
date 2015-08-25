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
		return myHead == null;
	}

	// private static ListNode reverseHelper(ListNode head) {
	// ListNode p, soFar;
	// // p plays the role of l in the previous version.
	// for (p = head, soFar = null; p != null;) {
	// ListNode temp = p;
	// ...
	// }
	// return soFar;
	// }

	public void reverse() {
		myHead = reverseHelper(myHead, null);
	}

	public static ListNode reverseHelper(ListNode l, ListNode soFar) {
		if (l == null) {
			return soFar;
		} else {
			ListNode temp = l.myNext;
			l.myNext = soFar;
			return reverseHelper(temp, l);
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

	public void doubleInPlace() {
		for (ListNode p = myHead; p != null; p = p.myNext) {
			ListNode temp = new ListNode(p.myItem, p.myNext);
			p.myNext = temp;
			p = p.myNext;
		}
	}

	public void remove(Object item) {
		if (isEmpty()) {
			return;
		} else if (myHead.myItem.equals(item) && size() == 1) {
			myHead = null;
			mySize--; // first and only
		} else if (myHead.myItem.equals(item) && size() > 1) {
			myHead = myHead.myNext;
			mySize--;
			remove(item); // first
		} else {
			for (ListNode p = myHead; p.myNext != null; p = p.myNext) {
				if (p.myNext.myNext == null && p.myNext.myItem.equals(item)) {
					myTail = p;
					mySize--; // last
				} else if (p.myNext.myNext != null && p.myNext.myItem.equals(item)) {
					p.myNext = p.myNext.myNext;
					mySize--; // middle
				}
			}
		}
		if (size() == 1) {
			myTail = myHead;
		} else if (isEmpty()) {
			myTail = null;
		}
	}

	public boolean isOk() {
		int rtn = 0;
		for (ListNode p = myHead; p != null; p = p.myNext) {
			rtn++;
		}
		if (rtn != mySize) {
			return false;
		} // check mySize
		if (size() == 0 && myHead == null && myTail == null) {
			return true;
		} else if (size() == 1 && myHead.equals(myTail)) {
			return true;
		} else {
			ListNode temp = myHead;
			for (int i = 0; i < size() - 1; i++) {
				temp = temp.myNext;
				if (temp.myItem == null) {
					return false; // check null myItem
				}
			}
			if (!temp.equals(myTail)) {
				return false; // check myTail
			}
			return true;
		}

	}

	///////////////////////////////////////////////////////////////////
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
	//////////////////////////////////////////////////////////////////////

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
			throw new IllegalArgumentException("Argument to get must be at least 0.");
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
		if (((List) obj).size() != this.size()) {
			return false;
		}
		for (int i = 0; i < size(); i++) {
			if (!this.get(i).equals(((List) obj).get(i))) {
				return false;
			}
		}
		return true;
	}

	public void add(Object x) {
		ListNode temp = new ListNode(x);
		if (isEmpty()) {
			myHead = temp;
			myTail = temp;
		} else {
			myTail.myNext = temp;
			myTail = temp;
		}
		mySize++;
	}

	public void appendInPlace(List l) {
		if (isEmpty()) {
			myHead = l.myHead;
		} else if (l.isEmpty()) {
			return;
		} else {
			myTail.myNext = l.myHead;
		}
		mySize += l.mySize;
		myTail = l.myTail;
	}

	/////////////////////////////////////////////////////////////////////////
	public Iterator iterator() {
		return new ElementIterator();
	}

	public class ElementIterator implements Iterator {

		// State variable(s) to be provided.

		private int index;

		public ElementIterator() {
			index = 0;
		}

		public boolean hasNext() {
			if ((isEmpty()) || (index == size())) {
				return false;
			} else {
				return true;
			}
		}

		public Object next() {
			ListNode temp = myHead;
			for (int i = 0; i < index; i++) {
				temp = temp.myNext;
			}
			index++;
			return temp.myItem;
		}

	}

}
