import java.util.Iterator;

public class List implements Iterable {

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

	public boolean isOk() {
		int count = 0;
		int c = 0;
		if (myTail == null && myHead != null) {
			System.out.println("one null");
			return false;
		}
		if (myTail != null && myHead == null) {
			System.out.println("one null");
			return false;
		}
		if (myTail == null && myHead == null) {
			System.out.println("not error both null");
			return true;
		}
		for (ListNode p = myHead; p != null; p = p.myNext) {
			count++;
			if (p.myItem == null) {
				System.out.println("an item is null");
				return false;
			}
			if (p.equals(myHead)){
				c++;
			}
			if (p.equals(myTail)){
				c++;
			}
			if (p == myHead && count != 1) {
				System.out.println("Head not shut up parth?");
				return false;
			}
			if (p == myTail && count != mySize) {
				System.out.println("tail not last?");
				return false;
			}
		}
		if (c != 2) {
			System.out.println("tail and head not in the same list" + c);
			return false;
		}
		if (count != mySize) {
			System.out.println("size isnt count??");
			return false;
		}
		return true;
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
	}

	public boolean equals(Object obj) {
		if (this.size() != ((List) obj).size())
			return false;
		for (int i = 0; i < this.size(); i++) {
			if (this.get(i) != ((List) obj).get(i)) {
				return false;
			}
		}
		return true;
	}

	public void add(Object x) {
		if (myHead == null) {
			myHead = new ListNode(x);
			mySize++;
			myTail = myHead;
			return;
		}
		myTail.myNext = new ListNode(x);
		mySize++;
		myTail = myTail.myNext;

	}

	public void appendInPlace(List l) {
		if (myHead == null) {
			myHead = l.myHead;
			mySize += l.size();
			myTail = l.myTail;
			return;
		}
		myTail.myNext = l.myHead;
		mySize += l.size();
		myTail = myTail.myNext;
	}

	public void remove(Object obj) {
		ListNode prev = null;
		for (ListNode p = myHead; p != null;) {
			if (p.myItem.equals(obj)) {
				if (p.equals(myTail)) {
					myTail = prev;
				}
				if (p.equals(myHead)) {
					myHead = p.myNext;
					p = p.myNext;
					mySize--;
				} else {
					mySize--;
					prev.myNext = p.myNext;
					p = p.myNext;
				}
			} else {
				prev = p;
				p = p.myNext;
			}
		}
	}
	
	public void doubleInPlace() {
	    for (ListNode p = myHead; p != null; p = p.myNext) {
	    	p.myNext = new ListNode(p.myItem, p.myNext);
	    	p = p.myNext;
	    	if (p.myItem == myTail.myItem) {
				myTail = p;
			}
	    }
	    mySize *= 2;
	}
	
	public void reverse() {
	    this.myHead = reverseHelper(this.myHead/*, null*/);
	}
	
	private static ListNode reverseHelper(ListNode l, ListNode soFar) {
	    if (l == null) {
	        return soFar;
	    } else {
	    	ListNode temp = l.myNext;
			l.myNext = soFar;
		    return reverseHelper (temp , l);
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

	public Iterator iterator() {
		return new ElementIterator();
	}

	public class ElementIterator implements Iterator {
		ListNode li;

		// State variable(s) to be provided.

		public ElementIterator() {
			// TODO code to be provided
			li = myHead;
		}

		public boolean hasNext() {
			while (li != null && li.myNext != null) {
				return true;
			}
			return false;
			// TODO code to be provided
		}

		public Object next() {
			Object rtn = li.myItem;
			li = li.myNext;
			return rtn;
			// TODO code to be provided
		}

		public void remove() {
			// not used; do not implement
		}
	}

}
