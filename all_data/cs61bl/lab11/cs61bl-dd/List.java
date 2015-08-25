import java.util.*;

public class List {

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
		mySize = rtn;
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
		if (this.isEmpty()) {
			myHead = new ListNode(obj, myHead);
			myTail = myHead;
		} else {
			myHead = new ListNode(obj, myHead);
		}
		mySize += 1;
	}

	public boolean equals(Object obj) {
		if (this.size() != ((List) obj).size()) {
			return false;
		}
		ListNode temp = ((List) obj).myHead;
		for (ListNode p = this.myHead; p != null; p = p.myNext) {
			if (p.myItem != temp.myItem) {
				return false;
			}
			temp = temp.myNext;
		}
		return true;
	}

	public void add(Object x) {
		if (this.isEmpty()) {
			myTail = new ListNode(x, null);
			myHead = myTail;
		} else {
			myTail.myNext = new ListNode(x, null);
			myTail = myTail.myNext;
		}
		mySize += 1;
	}

	public void appendInPlace(List l) {
		if (this.isEmpty()) {
			this.myHead = l.myHead;
			this.myTail = l.myTail;
		} else if (l.isEmpty()) {
			return;
		} else {
			this.myTail.myNext = l.myHead;
			this.myTail = l.myTail;
		}
		mySize += l.size();
	}

	public Iterator iterator() {
		size();
		return new ElementIterator();
	}

	public class ElementIterator implements Iterator {
		private int myIndex;
		private ListNode temp = myHead;

		public ElementIterator() {
			this.myIndex = 0;
		}

		public boolean hasNext() {
			return myIndex < mySize;
		}

		public Object next() {
			Object rtn = temp.myItem;
			temp = temp.myNext;
			myIndex += 1;
			return rtn;
		}

		public void remove() {
			// not used
		}
	}

	public boolean isOk() {
		if (mySize != size()) {
			System.out.println("Size inconsistency");
			return false;
		}
		for (ListNode p = this.myHead; p != null; p = p.myNext) {
			if (p.myItem == null) {
				System.out.println("Item inconsistency");
				return false;
			}
			if (p.myNext == null && p != this.myTail) {
				System.out.println("Head or Tail inconsistency");
				return false;
			}
		}
		return true;
	}

	public void remove(Object obj) {
		while (this.myHead.myItem.equals(obj)) {
			this.myHead = this.myHead.myNext;
		}
		for (ListNode p = this.myHead; p != null; p = p.myNext) {
			if (p.myNext.myItem.equals(obj)) {
				p.myNext = p.myNext.myNext;
			}
			if (p.myNext == null) {
				this.myTail = p;
			}
		}
		size();
	}

	public void doubleInPlace() {
		for (ListNode p = myHead; p != null; p = p.myNext) {
			p.myNext = new ListNode(p.myItem, p.myNext);
			p = p.myNext;
		}
	}

	public void reverse() {
		this.myHead = this.reverseHelper(this.myHead);
	}

	private static ListNode reverseHelper(ListNode l, ListNode soFar) {
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
		for (p = head, soFar = null; p != null;) {
			ListNode temp = p.myNext;
			p.myNext = soFar;
			soFar = p;
			p = temp;
		}
		return soFar;
	}
}
