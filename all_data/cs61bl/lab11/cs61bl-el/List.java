import java.util.*;

public class List implements Iterable {
	private ListNode myHead;
	private ListNode myTail;
	private int mySize;

	public Iterator iterator() {
		return new ElementIterator();
	}

	public class ElementIterator implements Iterator {

		// State variable(s) to be provided.
		private int myCount;

		public ElementIterator() {
			myCount = 0;
		}

		public boolean hasNext() {
			return myCount < size();
		}

		public Object next() {
			myCount += 1;
			return get(myCount - 1);
		}

		public void remove() {
			// not used; do not implement
		}

	}

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
		myHead = new ListNode(obj, myHead);
		mySize++;
	}

	public boolean equals(Object obj) {
		// TODO Your code here

		ListNode curObj = ((List) obj).myHead;
		ListNode temp = myHead;
		if (curObj == null && temp == null) {
			return true;
		} else if (curObj == null || temp == null) {
			return false;
		} else if (this.size() != ((List) obj).size()) {
			return false;
		}

		for (ListNode p = myHead; p != null; p = p.myNext) {
			if (!curObj.myItem.equals(p.myItem)) {
				return false;
			}
			curObj = curObj.myNext;
		}
		return true;

	}

	public void add(Object x) {
		// TODO Your code here
		ListNode pointer = this.myHead;
		if (pointer == null) {
			myHead = new ListNode(x, null);
			myTail = myHead;
			return;
		}

		// for (ListNode p = myHead; p != null; p = p.myNext) {
		// if (p.myNext == null) {
		// p.myNext = new ListNode(x, null);
		// myTail = p.myNext;
		// return;
		// }
		//
		// }

		myTail.myNext = new ListNode(x, null);
		myTail = myTail.myNext;
		mySize++;
	}

	public void appendInPlace(List l) {
		// TODO Your code here

		ListNode pointer = this.myHead;
		if (pointer == null) {
			this.myHead = l.myHead;
			this.myTail = l.myTail;
			return;
		}
		//
		// for (ListNode p = myHead; p != null; p = p.myNext) {
		// if (p.myNext == null) {
		// p.myNext = l.myHead;
		// return;
		// }

		// }

		myTail.myNext = l.myHead;
		myTail = myTail.myNext;
		mySize += l.size();

	}

	public boolean isOk() {
		if (mySize != size()) {
			return false;
		} else if (contains(null)) {
			return false;
		} else if (!(myHead == null && myTail == null)
				|| !(myTail.myNext == null)) {
			return false;
		} else if (myHead.myItem != get(0)) {
			return false;
		}
		return true;
	}

	public void remove(Object o) {
		if (myHead == null) {
			return;
		} else if (myHead.myNext == null && myHead.myItem.equals(o)) {
			myHead = null;
			return;
		}

		ListNode prev = myHead;
		for (ListNode curr = myHead.myNext; curr != null;) {
			if (curr.myItem.equals(o)) {
				if (curr.myNext != null) {
					prev.myNext = curr.myNext;
					curr = prev.myNext;
				} else {
					curr = null;
					prev.myNext = null;
				}
			} else {
				curr = curr.myNext;
				prev = prev.myNext;
			}
		}

		if (myHead.myItem.equals(o)) {
			myHead = myHead.myNext;
			return;
		}

	}

	public void doubleInPlace() {

		for (ListNode p = myHead; p != null; p = p.myNext) {
			p.myNext = new ListNode(p.myItem, p.myNext);
			p = p.myNext;
		}
		myTail = myTail.myNext;
	}

	public void reverse() {
		myHead = reverseHelper(myHead);
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
		// p plays the role of l in the previous version.
		for (p = head, soFar = null; p != null;) {
			// System.out.println("p: " + p.toString());
			// System.out.println("soFar: " + soFar.toString());
			ListNode temp = p.myNext;
			p.myNext = soFar;
			soFar = p;
			p = temp;
		}
		return soFar;
	}

}
