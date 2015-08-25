import java.util.*;
import java.util.function.Consumer;

public class List implements Iterable {

	private ListNode myHead, myTail;
	private int mySize;

	public List() {
		myHead = null;
		myTail = null;
		mySize = 0;
	}

	public boolean isEmpty() {
		return myHead == null;
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

	@Override
	public boolean equals(Object obj) {
		List n = (List) obj;
		int max = Math.max(size(), n.size());
		for (int q = 0; q < max; q++) {
			if (size() <= q || n.size() <= q)
				return false;
			else {
				if (!get(q).equals(n.get(q)))
					return false;
			}
		}
		return true;
	}

	public void add(Object x) {
		if (mySize == 0) {
			myHead = new ListNode(x);
			myTail = myHead;
			mySize++;
		} else {
			// System.out.println(myTail);
			myTail.myNext = new ListNode(x);
			myTail = myTail.myNext;
			mySize++;
		}
	}

	public void appendInPlace(List l) {
		if (l.size() > 0) {
			if (mySize == 0) {
				myHead = l.myHead;
				myTail = l.myTail;
				mySize = l.mySize;
			} else {
				myTail.myNext = l.myHead;
				myTail = l.myTail;
				mySize += l.mySize;
			}
		}
	}

	public void remove(Object obj) {
		if (contains(obj)) {
			ListNode prev = myHead;
			for (ListNode p = myHead; p != null; p = p.myNext) {
				if (obj.equals(p.myItem)) {
					// If we need to remove the first item in the list...
					if (p == myHead)
						myHead = p.myNext;
					else {
						prev.myNext = p.myNext;
					}
				}
				prev = p;
			}

		}
	}

	public boolean isOK() {
		boolean ok;
		if (mySize == 0)
			ok = (myHead == null && myTail == null);
		else {
			ok = (myTail.myNext == null);
		}
		int rtn = 0;
		for (ListNode p = myHead; p != null; p = p.myNext) {
			if (p.myItem == null)
				ok = false;
			rtn++;
		}
		if (rtn != mySize) {
			ok = false;
		}
		return ok;
	}

	public void doubleInPlace() {

		for (ListNode p = myHead; p != null; p = p.myNext) {
			p.myNext = new ListNode(p.myItem, p.myNext);
			p = p.myNext;
		}
	}

	public void reverse() {
		myHead = List.reverseHelper(myHead, null);
	}

	public static ListNode reverseHelper(ListNode l, ListNode soFar) {
		if (l == null) {
			return soFar;
		} else {
			ListNode temp = l.myNext;
			l.myNext = soFar;
			soFar = l;
			return reverseHelper(temp,soFar);
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
			System.out.println(soFar.myItem);
		}
		return soFar;
	}

	public Iterator iterator() {
		return new ElementIterator();
	}

	public void forEach(Consumer action) {
		// TODO Auto-generated method stub

	}

	public Spliterator spliterator() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * ListNode class for implementation of lists and colonoscopies
	 * 
	 * @author cs61bl-ah, cs61bl-ag
	 * 
	 */
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

	/**
	 * 
	 * @author cs61bl-ah & cs61bl-ag
	 * 
	 */
	public class ElementIterator implements Iterator {

		int index;

		public ElementIterator() {
			index = 0;
		}

		public boolean hasNext() {
			try {
				get(index);
			} catch (IllegalArgumentException ex) {
				return false;
			}
			return true;
		}

		public Object next() {
			Object o = get(index);
			index++;
			return o;
		}

		public void remove() {
			// not used; do not implement
		}
	}

}