import java.util.Iterator;

public class List implements Iterable {

	private ListNode myHead;
	private ListNode myTail;
	private int mySize;

	public List() {
		myHead = null;
		mySize = 0;
		myTail = null;
	}

	public boolean isEmpty() {
		return myHead == null;
	}

	public void isOK() throws IllegalStateException {
		if ((myHead == null) != (myTail == null))
			throw new IllegalStateException("Invalid head/tail.");
		if (myHead == null)
			return;
		ListNode temp = myHead;
		int counter = 0;
		while (temp.myNext != null) {
			if (temp.myItem == null)
				throw new IllegalStateException("An item is null");
			counter++;
			temp = temp.myNext;
		}
		if (counter != mySize - 1)
			throw new IllegalStateException("mySize is not correct");
		if (temp != myTail)
			throw new IllegalStateException("myTail is not correct");
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
		if (myTail == null)
			myTail = myHead;
		mySize++;
	}

	public boolean equals(Object obj) {
		ListNode objList = ((List) obj).myHead;
		ListNode myList = myHead;
		if (!(obj instanceof List)) {
			return false;
		} else {
			while (true) {
				if (myList == null) {
					return (objList == null);
				} else if (objList == null) {
					return false;
				} else if (!myList.myItem.equals(objList.myItem)) {
					return false;
				}
				objList = objList.myNext;
				myList = myList.myNext;
			}
		}
	}

	public void add(Object x) {
		if (myTail == null) {
			myHead = new ListNode(x, null);
			myTail = myHead;
			mySize++;
		} else {
			myTail.myNext = new ListNode(x, null);
			myTail = myTail.myNext;
			mySize++;
		}
		//
		//
		// ListNode temp = myHead;
		// if (myHead == null) {
		// myHead = new ListNode(x, null);
		// myTail = myHead;
		// } else {
		// for (ListNode p = myHead; p != null; p = p.myNext) {
		// temp = p;
		// }
		// temp.myNext = new ListNode(x, null);
		// myTail = temp.myNext;
		// }
	}

	public void remove(Object x) {
		// System.out.println(myHead == null);
		// System.out.println(myTail == null);
		ListNode pointer = myHead;
		if (myHead == null) {
			return;
		}
		if (myHead == myTail) {
			if (myHead.myItem.equals(x)) {
				myHead = myHead.myNext;
				myTail = myHead;
				mySize--;
				return;
			}
		}
		while (pointer != myTail) {
			// System.out.println("My Head " + myHead + " My Tail " + myTail
			// + " My Item " + pointer.myItem + "");
			if (myHead.myItem.equals(x)) {
				myHead = myHead.myNext;
				pointer = myHead;
				mySize--;
			} else if (pointer.myNext.myItem.equals(x)) {
				pointer.myNext = pointer.myNext.myNext;
				mySize--;
				if (pointer.myNext == null)
					myTail = pointer;
			} else {
				pointer = pointer.myNext;
			}
		}
	}

	public void appendInPlace(List l) {
		if (l.myHead == null) {
			return;
		}
		if (myTail != null) {
			myTail.myNext = l.myHead;
			myTail = l.myTail;
			mySize += l.mySize;
		} else {
			myHead = l.myHead;
			myTail = l.myTail;
			mySize = l.mySize;
		}
		//
		// ListNode temp = myHead;
		// if (l.myHead == null) {
		// return;
		// } else {
		// if (myHead == null) {
		// myHead = l.myHead;
		// } else {
		// for (ListNode p = myHead; p != null; p = p.myNext) {
		// temp = p;
		// }
		// temp.myNext = l.myHead;
		// }
		// }

	}

	public void reverse() { // Should this be destructive????
		myHead = reverseHelper(myHead, null);
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

	public void iterativeReverse() {
		myHead = reverseHelper(myHead);
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

		int myCount;

		public ElementIterator() {
			myCount = 0;
		}

		public boolean hasNext() {
			ListNode temp = myHead;
			for (int index = myCount; index != 0; index--) {
				temp = temp.myNext;
			}
			return (temp != null && temp.myNext != null);
		}

		public Object next() {
			ListNode temp = myHead;
			for (int index = myCount; index != 0; index--) {
				temp = temp.myNext;
			}
			myCount++;
			return temp.myItem;
		}

		public void remove() {
			// not used; do not implement
		}
	}
}
