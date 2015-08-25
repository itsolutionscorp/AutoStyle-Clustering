import java.util.Iterator;

public class List {

	private ListNode myHead;
	private int mySize;
	private ListNode myTail;

	public List() {
		myHead = null;
		mySize = 0;
		myTail = null;
	}

	public boolean isEmpty() {
		return myHead == null;
	}

	public Iterator iterator() {
		return new ElementIterator();
	}

	public boolean isOk() {
		int size = 0;
		int c = 0;
		for (ListNode p = myHead; p != null; p = p.myNext) {
			size++;
			if (p == myHead) {
				c++;
			}

			if (p.myItem == null) {
				return false;
			}
			if (p == myTail) {
				c++;
			}
			if (p.myNext == null && p != myTail) {
				return false;
			}
			// if (p == myTail && size != mySize)
			// return false;
		}
		if (c != 2)
			return false;
		if (size != mySize) {
			return false;
		}
		return true;

	}

	public class ElementIterator implements Iterator {
		ListNode myNode;

		// State variable(s) to be provided.

		public ElementIterator() {
			myNode = myHead;

		}

		public boolean hasNext() {
			if (myNode == null || myNode.myNext == null) {
				return false;
			} else {
				return true;
			}
		}

		public Object next() {
			Object returned = myNode.myItem;
			myNode = myNode.myNext;
			return returned;
		}

		public void remove() {
			// not used; do not implement
		}
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

	public void remove(Object obj) {
		// not used; do not implement
		ListNode p = myHead;
		ListNode prev = null;

		while (p.myNext != null) {
			if (p.myItem.equals(obj)) {
				mySize--;
//				myTail = p;
//				System.out.println("tail" + myTail.myItem);
				if (p.myNext == null && p.myNext.myItem != obj) {
					myTail = p;
				}
				if (prev == null) {
					myHead = p.myNext;
					p = p.myNext;

				} else {
					prev.myNext = p.myNext;
					p = p.myNext;

				}

			} else {
				prev = p;
				p = p.myNext;

			}

		}
	}
	
	public void reverse() {
//		this.myHead = reverseHelper(myHead, null);
		this.myHead = reverseHelper(myHead);
	}

//	private static ListNode reverseHelper(ListNode l, ListNode soFar) {
//		if (l == null) {
//			return soFar;
//		} else {
//			ListNode temp = l.myNext;
//			l.myNext = soFar;
//			return reverseHelper(temp, l);
//		}
//	}
	
	
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


	public void doubleInPlace() {
		for (ListNode p = myHead; p != null; p = p.myNext) {
			p.myNext = new ListNode(p.myItem, p.myNext);
			p = p.myNext;
			myTail = p;
//			System.out.println(myTail.myItem);

			// TODO Your code here
		}
		// TODO And maybe here as well
		mySize = mySize * 2;
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
	}

	public boolean equals(Object obj) {
		// TODO Your code here
		if (((List) obj).size() != this.size())
			return false;
		for (int c = 0; c < this.size(); c++) {
			if (((List) obj).get(c) != this.get(c)) {
				return false;
			}
		}
		return true;
	}

	public void add(Object x) {
		if (this.myHead == null) {
			this.myHead = new ListNode(x);
			mySize++;
			return;
		}

		for (ListNode p = myHead; p != null; p = p.myNext) {
			if (p.myNext == null) {
				p.myNext = new ListNode(x);
				mySize++;
//				myTail = p;
				break;
			}
		}
	}

	public void appendInPlace(List l) {
		// TODO Your code here
		if (this.myHead == null) {
			this.myHead = l.myHead;
			mySize += l.size();
			return;
		}

		for (ListNode p = myHead; p != null; p = p.myNext) {
			if (p.myNext == null) {
				p.myNext = l.myHead;
				mySize += l.size();
//				myTail = p;
				break;
			}
		}
	}
}
