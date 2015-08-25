import java.util.*;

public class List {

	private ListNode myHead;
	private int mySize;
	private ListNode myTail;

	public List() {
		myHead = null;
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
		for (ListNode p = myHead; p != null; p = p.myNext) {
			if (p.myNext == null) {
				myTail = p;
			}
		}
		mySize++;
	}

	public boolean equals(Object obj) {
		// TODO Your code here
		ListNode a = ((ListNode) ((List) obj).myHead);
		if (size() != ((List) obj).size()) {
			return false;
		} else if (this.isEmpty() && ((List) obj).isEmpty()) {
			return true;
		} else if (this.isEmpty()) {
			return false;
		} else if (((List) obj).isEmpty()) {
			return false;
		}
		for (ListNode p = myHead; p != null; p = p.myNext) {
			if (p.myItem != a.myItem) {
				return false;
			} else {
				a = a.myNext;
			}
		}
		return true;
	}

	public void add(Object x) {
		// TODO Your code here
		if (isEmpty()) {
			this.myHead = new ListNode(x);
			mySize++;
		} else {
			for (ListNode p = myHead; p != null; p = p.myNext) {
				if (p.myNext == null) {
					p.myNext = new ListNode(x);
					myTail = p.myNext;
					mySize++;
					break;
				}
			}
		}
	}

	public void appendInPlace(List l) {
		// TODO Your code here
		if (isEmpty() && l.isEmpty()) {
			this.myHead = l.myHead;
		} else if (isEmpty()) {
			this.myHead = l.myHead;
			mySize++;
		} else {
			for (ListNode p = myHead; p != null; p = p.myNext) {
				if (p.myNext == null) {
					p.myNext = l.myHead;
					for (ListNode q = p.myNext; q != null; q = q.myNext) {
						mySize++;
						myTail = q;
					}
					break;
				}
			}
		}
	}

	public boolean isOk() {
		int checkSize = 0;
		for (ListNode p = myHead; p != null; p = p.myNext) {
			checkSize++;
			if (p.myItem == null) {
				return false;
			}
		}
		if (mySize != checkSize) {
			return false;
		}
		if (myHead == null && myTail != null) {
			return false;
		}
		if (size() == 1 && myHead != myTail) {
			return false;
		} else {
			return true;
		}
	}

	public void remove(Object o) {

		if (isEmpty()) {
			throw new IllegalArgumentException();
		}
		for (ListNode p = myHead; p != null; p = p.myNext) {
			if (p.myItem == o && p.myNext == null) {
				p.myItem = null;
				mySize--;
			} else if (p.myItem == o) {
				p = p.myNext;
				mySize--;
			} else if (p.myNext == null) {
				break;
			} else if (p.myNext.myItem == o) {
				while (p.myNext != null && p.myNext.myItem == o) {
					p.myNext = p.myNext.myNext;
					mySize--;
				}
			}

		}
	}

	public void doubleInPlace() {

		for (ListNode p = myHead; p != null; p = p.myNext.myNext) {
			if (p.myItem == null) {
				break;
			} else {
				p.myNext = new ListNode(p.myItem, p.myNext);
				mySize++;
			}
		}
	}
	
	/*
	public ListNode reverse() {
		myHead = reverseHelper(myHead, null);
		return myHead;
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
	*/
	
	public ListNode reverse() {
		myHead = reverseHelper(myHead);
		return myHead;
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
	
	
	

	public Iterator iterator() {
		return new ElementIterator();
	}

	public class ElementIterator implements Iterator {
		ListNode first;

		// State variable(s) to be provided.

		public ElementIterator() {
			if (isEmpty()) {
				first = null;
			} else {
				first = myHead;
			}
		}

		public boolean hasNext() {
			return first != null;
		}

		public Object next() {
			Object temp = first.myItem;
			first = first.myNext;
			return temp;

		}

		public void remove() {
			// not used; do not implement
		}
	}
}
