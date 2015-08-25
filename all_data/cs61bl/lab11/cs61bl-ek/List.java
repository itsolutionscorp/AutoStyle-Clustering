
	import java.util.Iterator;
import java.util.NoSuchElementException;

public class List {

	private ListNode myHead;
	private ListNode myTail;
	private int mySize = 0;

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

	@Override
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

	@Override
	public boolean equals(Object obj) {
		// TODO Your code here
		List l = (List) obj;
		if (l.size() != this.size()) {
			return false;
		}
		for (int i = 0; i < l.size(); i++) {
			if (this.get(i) != l.get(i)) {
				return false;
			}
		}
		return true;
	}

	public void add(Object x) {
		// TODO Your code here
		if (this.myHead != null) {
			ListNode pointer = this.myHead;
			while (pointer.myNext != null) {
				pointer = pointer.myNext;
			}
			pointer.myNext = new ListNode(x);
			myTail = pointer.myNext;
		} else {
			ListNode m = new ListNode(x);
			this.myHead = m;
			myTail = this.myHead;
		}
		mySize++;
	}

	public void appendInPlace(List l) {
		// TODO Your code here
		int listLength = l.size();
		if (myHead == null) {
			if (l.myHead != null) {
				myHead = l.myHead;
				myTail = l.myTail;
			}
		} else {
			if (l.myHead != null) {
				myTail.myNext = l.myHead;
				myTail = l.myTail;
			}
		}
		mySize += listLength;
	}

	public boolean isOK() {
		int length = 0;
		ListNode pointer = this.myHead;
		while (pointer != null) {
			length++;
			pointer = pointer.myNext;
			if (length == mySize) {
				return false;
			}
		}
		pointer = this.myHead;
		while (pointer != null) {
			if (pointer.myItem == null) {
				return false;
			}
			pointer = pointer.myNext;
		}
		pointer = this.myHead;
		while (pointer.myNext != null) {
			pointer = pointer.myNext;
		}
		if (pointer != this.myTail) {
			return false;
		}
		return true;
	}

	public void remove(Object obj) {
		if (this.myHead != null) {
			while (this.myHead.myItem == obj) {
				this.myHead = this.myHead.myNext;
				mySize--;
			}
			ListNode previous = this.myHead;
			ListNode current = this.myHead.myNext;
			ListNode next;
			while (current != null) {
				next = current.myNext;
				if (current.myItem == obj) {
					if (next == null) {
						this.myTail = previous;
					}
					previous.myNext = next;
					current.myNext = null;
					current = next;
					mySize--;
				}
			}
		}
	}

	public Iterator iterator() {
		return new ElementIterator();
	}

	public class ElementIterator implements Iterator {

		// State variable(s) to be provided.
		private ListNode l = myHead;

		public ElementIterator() {
			
			
		}

		@Override
		public boolean hasNext() {
			if (l == null) {
				return false;
			} else {
				return true;
			}
		}

		@Override
		public Object next() {
			// return myNext;
			if (hasNext() == false) {
				throw new NoSuchElementException();
			} else {
				Object z = l.myItem;
				l = l.myNext;
				return z;
			}
		}

		@Override
		public void remove() {
			// not used; do not implement
		}
	}


	public void doubleInPlace() {

	    for (ListNode p = myHead; p != null; p = p.myNext.myNext) {
	        // TODO Your code here
	    	p.myNext = new ListNode(p.myItem, p.myNext);
	    		//if (p.myNext.myNext == null) {
	    			//this.myTail=p.myNext;
	    	
	    	
	    }
	    // TODO And maybe here as well
	    // change my size
	}
	public void reverse() {
	    ListNode soFar = null;
	    reverseHelper(this.myHead, soFar);
	    ListNode x = this.myHead;
	    this.myHead = this.myTail;
	    this.myTail = x;
	}
	
	private static void reverseHelper(ListNode l, ListNode soFar) {
	    if (l == null) {
	        return;
	    } 
	    ListNode a = l.myNext;
	    l.myNext = soFar;
	    reverseHelper(a, l);
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


