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
		if (mySize == 1) {
			myTail = myHead;
		}
	}

	public boolean equals (Object obj) {
		// TODO Your code here
		if (this.size() != ((List) obj).size()) {
			return false;
		}
		else {
			int i = 0;
			for (ListNode p = myHead; p != null; p = p.myNext) {
				if (p.myItem != ((List) obj).get(i)) {
					return false;
				}
				i++;
			}
			return true;
		}
	}

	public void add (Object x) {
		// TODO Your code here
		if (this.isEmpty()) {
			myHead = new ListNode(x);
			myTail = myHead;
			mySize++;
		}
		else {
			myTail.myNext = new ListNode(x);
			myTail = myTail.myNext;
			mySize++;
		}
		
	}

	public void appendInPlace (List l) {
		// TODO Your code here
		if (this.isEmpty()) {
			myHead = l.myHead;
			mySize = l.mySize;
			myTail = l.myTail;
		}
		else if (l.isEmpty()) {
			return;
		}
		else {
			myTail.myNext = l.myHead;
			myTail = l.myTail;
			mySize += l.mySize;
		}
	}
	
	public Iterator iterator() {
	    return new ElementIterator();
	}

	public class ElementIterator implements Iterator {

	    // State variable(s) to be provided.
		private int index;

	    public ElementIterator() {
	        // TODO code to be provided
	    	index = -1;
	    }

	    public boolean hasNext() {
	        return ((index + 1) < size());
	        // TODO code to be provided
	    }

	    public Object next() {
	        index++;
	        return get(index);
	        // TODO code to be provided
	    }

	    public void remove() {
	        // not used; do not implement
	    }
	}
	
	public boolean isOK() {
		int rtn = 0;
		for (ListNode p = myHead; p != null; p = p.myNext) {
			rtn++;
			if (p.myItem == null) {
				return false;
			}
		}
		
		if (mySize != rtn) {
			return false;
		}
		
		if (((myHead == null) && (myTail != null)) || ((myHead != null) && (myTail == null))) {
			return false;
		}
		
		if ((this.myHead.myItem != this.get(0)) || (this.myTail.myItem != this.get(mySize-1))) {
			return false;
		}
		
		return true;
		
		
	}
	
	public void remove(Object obj) {
		for (ListNode p = myHead; p.myNext != null; p = p.myNext) {
			if (p.myNext.myItem.equals(obj)) {
				p.myNext = p.myNext.myNext;
			}
		}
		if (myHead.myItem.equals(obj)) {
			myHead = myHead.myNext;
		}
	}
	
	public void doubleInPlace() {

	    for (ListNode p = myHead; p != null; p = p.myNext) {
	        // TODO Your code here
	    	ListNode temp = p.myNext;
	    	p.myNext = new ListNode(p.myItem,temp);
	    	p = p.myNext;
	    }
	    // TODO And maybe here as well
	}
	
	public void reverse() {
	    //myHead = reverseHelper(myHead,null);
		myHead = reverseHelper(myHead);
	}

//	private static ListNode reverseHelper(ListNode l, ListNode soFar) {
//	    if (l == null) {
//	        return soFar;
//	    } else {
//	    	ListNode temp = l.myNext;
//	    	l.myNext = soFar;
//	    	return reverseHelper (temp,l);
//	    }
//	}
	
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
