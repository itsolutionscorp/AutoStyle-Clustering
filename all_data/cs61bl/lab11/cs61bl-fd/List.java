import java.util.*;

public class List implements Iterable {

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

	private static class ListNode {

		private Object myItem;
		private ListNode myNext;

		private ListNode (Object item, ListNode next) {
			myItem = item;
			myNext = next;
		}

		private ListNode (Object item) {
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
	public boolean contains (Object obj) {
		for (ListNode p = myHead; p != null; p = p.myNext) {
			if (obj.equals (p.myItem)) {
				return true;
			}
		}
		return false;
	}

	// Returns the element at the given position in this list.
	public Object get (int pos) {
		if (pos < 0) {
			throw new IllegalArgumentException (
					"Argument to get must be at least 0.");
		}
		if (pos >= size()) {
			throw new IllegalArgumentException ("Argument to get is too large.");
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
	
	public void addToFront (Object obj) {
		myHead = new ListNode (obj, myHead);
		mySize++;
	}

	public boolean equals (Object obj) {
		// TODO Your code here 
		if (this.size() != ((List) obj).size()) {
			return false;
		}
		for (int i = 0; i < this.size(); i++) {
			if (!this.get(i).equals(((List) obj).get(i))) {
				return false;
			}
		}
		return true;
	}

	public void add (Object x) {
		// TODO Your code here 
		if (isEmpty()) {
			myHead = new ListNode(x, null);
			myTail = myHead;
			mySize++;
			return;
		}
		myTail.myNext = new ListNode(x, null);
		myTail = myTail.myNext;
		mySize++;
	}

	public void appendInPlace (List l) {
		// TODO Your code here 
		if (l.isEmpty()) {
			return;
		}
		if (isEmpty()) {
			myHead = l.myHead;
			myTail = l.myTail;
			mySize = l.size();
			return;
		}
		myTail.myNext = l.myHead;
		myTail = l.myTail;
		mySize = mySize + l.size();
	}
	
	public void remove(Object o) {
		if (myHead == null) {
			return;
		}
		ListNode behind = myHead;
		ListNode p = myHead;
		if (p.myItem.equals(o)) {
			myHead = p.myNext;
		}
		p = p.myNext;
		while (p != null) {
			if (p.myItem.equals(o) && p == myHead) {
				myHead = p.myNext;
				behind.myNext = p.myNext;
				p = p.myNext;
			}
			else if (p.myItem.equals(o)) {
				behind.myNext = p.myNext;
				p = p.myNext;
			} else {
				behind = behind.myNext;
				p = p.myNext;
			}
		}
	}
	
	public void doubleInPlace() {
	    for (ListNode p = myHead; p != null; p = p.myNext) {
	        // TODO Your code here
	    	ListNode next = p.myNext;
	    	p.myNext = new ListNode(p.myItem);
	    	p.myNext.myNext = next;
	    	p = p.myNext;
	    }
	    // TODO And maybe here as well
	}
	
	public void reverse() {
		myTail = myHead;
	    myHead = reverseHelper(myHead);
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

	private static ListNode reverseHelper(ListNode l, ListNode soFar) {
	    if (l == null) {
	        return soFar;
	    } else {
	    	ListNode temp = l.myNext;
	    	l.myNext = soFar;
	    	return reverseHelper (temp, l);
	    }
	}
	
	public void isOK() {
		int countNodes = 0;
		if (myHead == null) {
			if (myTail != null) {
				throw new IllegalStateException("myHead and myTail are not representing the same list.");
			}
		}
		for (ListNode p = myHead; p != null; p = p.myNext) {
			countNodes++;
			if (p.myItem == null) {
				throw new IllegalStateException("myItem is null somewhere in this List.");
			}
			if (p.myNext == null) {
				if (p != myTail) {
					throw new IllegalStateException("myTail does not represent the end of this list.");
				}
			}
		}
		if (mySize != countNodes) {
			throw new IllegalStateException("mySize is not accurate.");
		}
	}
	
	public Iterator iterator() {
	    return new ElementIterator();
	}

	public class ElementIterator implements Iterator {

	    // State variable(s) to be provided.
		ListNode p;

	    public ElementIterator() {
	        // TODO code to be provided
	    	p = myHead;
	    }

	    public boolean hasNext() {
	    	if (p != null) {
	    		return true;
	    	}
	        return false;
	        // TODO code to be provided
	    }

	    public Object next() {
	    	Object rtn = p.myItem;
	    	p = p.myNext;
	        return rtn;
	        // TODO code to be provided
	    }

	    public void remove() {
	        // not used; do not implement
	    }
	}

}
