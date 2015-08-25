import java.util.*;

public class List {

	private ListNode myHead;
	private ListNode myTail;
	private int mySize;

	public List() {
		myHead = null;
		myTail = null;
		mySize = 0;
	}

	public boolean isEmpty() {
		return myHead == null;
	}

	public void isOK() {
		int a = 0;
		for (ListNode p = myHead; p != null; p = p.myNext) {
			a++;
		}
		if (!(a == mySize)) {
			throw new IllegalStateException("mySize is no internaly consistant");
		}

		for (ListNode p = myHead; p != null; p = p.myNext) {
			if (p.myItem == null) {
				throw new IllegalStateException(
						"a myItem object cannot be a null");
			}
		}
		if (myHead == null && myTail == null) {
			// do noting
		} else if (myTail.myItem == get(mySize - 1)) {
			// do nothing
		} else {
			throw new IllegalStateException(
					"myTail is not pointing to the proper place");
		}
	}
	public void remove(Object obj) {
		int s = mySize;
		ListNode p = myHead;
		for (int i = 0; i < s; i++) {
			if (p.myItem.equals(obj)) {
				myHead = p.myNext;
				p = myHead;
				myTail = p;
				mySize--;
			} else if (p.myNext != null) {
				if (p.myNext.myItem.equals(obj)) {
					p.myNext = p.myNext.myNext;
					mySize--;
				} else {
					p = p.myNext;
					myTail = p;
				}
			}
		}

	}
	public void doubleInPlace() {

		for (ListNode p = myHead; p != null; p = p.myNext) {
			p.myNext = new ListNode(p.myItem, p.myNext);
			p = p.myNext;
			if (p.myNext == null) {
				myTail=p;
			}
		}
		mySize= 2*mySize;
	}
	public void reverse() {
		if (!this.isEmpty()) {
			// myHead= reverseHelper(myHead,null);
			myTail=myHead;
			myHead = reverseHelper(myHead);
		}
	}
	private static ListNode reverseHelper(ListNode l, ListNode soFar) {
		if (l == null) {
			// do nothing
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
			ListNode temp = p;
			p = p.myNext;
			temp.myNext = soFar;
			soFar = temp;

		}
		return soFar;
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
		mySize++;
		if (myHead == null) {
			myHead = new ListNode(obj, myHead);
			myTail = myHead;
		} else {
			myHead = new ListNode(obj, myHead);
		}
	}

	public boolean equals(Object obj) {
		if ((this.isEmpty() == true && ((List) obj).isEmpty() != true)
				|| (this.isEmpty() != true && ((List) obj).isEmpty() == true)) {
			return false;
		}
		return this.toString().equals(obj.toString());
	}

	public void add(Object x) {
		ListNode b = null;
		mySize++;
		if (myHead == null) {
			myHead = new ListNode(x);
			myTail = myHead;
		} else {
			for (ListNode p = myHead; p != null; p = p.myNext) {
				b = p;
			}
			b.myNext = new ListNode(x);
			myTail = b.myNext;
		}
	}

	public void appendInPlace(List l) {
		ListNode b = null;
		if (myHead == null && l.myHead == null) {
			// do noting
		} else if (myHead == null) {
			myHead = l.myHead;
			myTail = l.myTail;
			mySize = l.mySize;
		} else if (l.myHead == null) {
			// do nothing
		} else {
			for (ListNode p = myHead; p != null; p = p.myNext) {
				b = p;
			}
			b.myNext = l.myHead;
			myTail = l.myTail;
			mySize += l.mySize;
		}
	}

	@SuppressWarnings("rawtypes")
	public Iterator iterator() {
		return new ElementIterator();
	}

	@SuppressWarnings("rawtypes")
	public class ElementIterator implements Iterator {

		private int count;

		public ElementIterator() {
			count = 0;
		}

		public boolean hasNext() {
			if (size() > count) {
				return true;
			} else {
				return false;
			}
		}

		public Object next() {
			Object b = get(count);
			count++;
			return b;
		}

		public void remove() {
			// not used; do not implement
		}
	}
}
