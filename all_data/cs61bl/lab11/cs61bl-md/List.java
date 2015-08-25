import java.util.Iterator;

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
		if(mySize == 0) myTail = myHead; 
		mySize++;
	}
	
	public boolean equals (Object obj) {
		// ObjectODO Your code here 
		if (!(obj instanceof List)) return false;
		List l = (List) obj;
		if (l.size() != this.size()) return false;
		for (int i = 0 ; i < size() ; i++) {
			if (!get(i).equals(l.get(i))) return false;
		}
		return true;
		
	}

	public void add (Object x) {
		// TODO Your code here 
		ListNode ln = new ListNode (x);
		if(mySize == 0) {
			myHead = ln;
			myTail = ln;
		}
		else {
			myTail.myNext = ln;
			myTail = ln;
		}
		mySize++;
	}

	public void appendInPlace (List l) {
		// TODO Your code here
		if (l.size() == 0) {
			return;
		}
		if (mySize == 0) {
			myHead = l.myHead;
		}
		else {
			myTail.myNext = l.myHead;
		}
		myTail = l.myTail;
		mySize = mySize + l.size();
		}
	
	public Iterator iterator() {
	    return new ElementIterator();
	}

	public class ElementIterator implements Iterator {

	    // State variable(s) to be provided.
		int index;
		

	    public ElementIterator() {
	        // TODO code to be provided
	    	index = 0;
	    }

	    public boolean hasNext() {
	    	if (index < size() - 1) return true;
	    	return false;
	        // TODO code to be provided
	    }

	    public Object next() {
	        index++;
	    	return get(index - 1);
	        // TODO code to be provided
	    }

	    public void remove() {
	        // not used; do not implement
	    }
	}
	
	public boolean isOK() {
		int size = 0;
		ListNode p = myHead;
		for (; p != null ; p = p.myNext) {
			size++;
			if (p.myItem == null) return false;
		}
		if (size != mySize) return false;
		if (myHead == null && myTail == null) return true;
		if (myTail == p ) return true;
		return false;
		
	}
	
	public void remove(Object obj) {
		for (int i = 0 ; i < mySize ; i++ ) {
			if(get(i).equals(obj)) {
				ListNode p = myHead;
				for (int j = 0 ; j < i ; j++) {
					p = p.myNext;
				}
				if(i == mySize -  1) {
					p.myNext = null;
					myTail = p;
				}
				else {
					p.myNext = p.myNext.myNext;
				}
				mySize--;
			}
		}
	}
	
	public void doubleInPlace() {

	    for (ListNode p = myHead; p != null; p = p.myNext) {
	        // TODO Your code here
	    	ListNode duplicate = new ListNode(p.myItem, p.myNext);
	    	p.myNext = duplicate;
	    	p = p.myNext;
	    }
	    // TODO And maybe here as well
	}
	
//	public void reverse() {
//		if(mySize <= 1) return;
//		ListNode p = myHead.myNext;
//		ListNode prev = myHead;
//		ListNode temp = myHead.myNext;
//		myHead.myNext = null;
//		myTail = myHead;
//		for (; p.myNext != null; p = temp) {
//			temp = p.myNext;
//			p.myNext = prev;
//			prev = p;
//		}
//		p.myNext = prev;
//		myHead = p;
//	}
	
	public void reverse() {
		myHead = reverseHelper(myHead);
	}
	
	private static ListNode reverseHelper(ListNode l, ListNode soFar) {
	    if (l == null) {
	        return soFar;
	    } else {
	        ListNode temp = l;
	        l = l.myNext;
	        temp.myNext = soFar;
	        soFar = temp;
	        return reverseHelper(l, soFar);
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
	
	

}
