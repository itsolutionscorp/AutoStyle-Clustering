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
	}

	public boolean equals (Object obj) {
		// Different type of object or different sizes
		if(!(obj instanceof List) || ((List) obj).size() != this.size())
			return false;
		
		// Empty cases
		if(this.isEmpty() || ((List) obj).isEmpty())
			return this.isEmpty() == ((List) obj).isEmpty();
		
		for(ListNode p = this.myHead, q = ((List) obj).myHead; p != null && q != null; p = p.myNext, q = q.myNext) {
			if(!(p.myItem.equals(q.myItem))) {
				return false;
			}
		}
		return true;
	}

	public void add (Object x) {
		mySize++;
		if(this.isEmpty()) {
			myHead = new ListNode(x);
			myTail = myHead;
			return;
		}
		myTail.myNext = new ListNode(x);
		myTail = myTail.myNext;
	}
	
	public void remove (Object x) {
		if(this.isEmpty()) {
			return;
		}
		ListNode p = myHead;
		
		while(p.myItem.equals(x)) {
			p = p.myNext;
			mySize--;
			if(p == null)
				break;
		}
		myHead = p;
		myTail = p;
		
		for( ; p != null; p = p.myNext) {
			if((p.myNext != null && p.myNext.myItem.equals(x))) {
				p.myNext = p.myNext.myNext;
				mySize--;
			} else {
				myTail = p;
			}
		}
	}

	public void appendInPlace (List l) {
		if(l.isEmpty())
			return;
		if(this.isEmpty()) {
			myHead = l.myHead;
		} else {
			myTail.myNext = l.myHead;
		}
		myTail = l.myTail;
		mySize += l.mySize;
	}
	
	public boolean isOK() {
		
		if(this.isEmpty()) {
			if(myHead != null || myTail != null || mySize != 0)
				return false;
			return true;
		}
		if (myHead == null || myTail == null)
			return false;
		ListNode p;
		int size = 1;
		for(p = this.myHead; p.myNext != null; p = p.myNext) {
			if(p.myItem == null)
				return false;
			size++;
		}
		if(p != myTail || size != mySize)
			return false;
		return true;
	}
	
	public void doubleInPlace() {

	    if(myHead == null)
	    	return;
		for (ListNode p = myHead; p != null; p = p.myNext) {
	        p.myNext = new ListNode(p.myItem, p.myNext);
	        mySize ++;
	        p = p.myNext;
	    }
	    myTail = myTail.myNext;
	}
	
	public void reverse() {
	    if(myHead == null)
	    	return;
	    ListNode tmp2 = null;
	    reverseHelper(myHead);
	    
	    ListNode tmp = myHead;
	    myHead = myTail;
	    myTail = tmp;
	}

	public static void reverseHelper(ListNode l, ListNode soFar) {
	    if(l == null)
	    	return;
		ListNode tmp = soFar;
	    soFar = l;
	    l = l.myNext;
	    soFar.myNext = tmp;
    	reverseHelper(l, soFar);
	 }
	
	private static ListNode reverseHelper(ListNode head) {
	    ListNode p, soFar;
	    // p plays the role of l in the previous version.
	    for (p = head, soFar = null; p != null;) {
	    	ListNode tmp = soFar;
		    soFar = p;
		    p = p.myNext;
		    soFar.myNext = tmp;
	    }
	    return soFar;
	}
	
	public Iterator iterator() {
	    return new ElementIterator();
	}

	public class ElementIterator implements Iterator {

	    private ListNode currentNode;
		// State variable(s) to be provided.

	    public ElementIterator() {
	        currentNode = myHead;
	    }

	    public boolean hasNext() {
	        return currentNode != null;
	    }

	    public Object next() {
	    	Object tmp = currentNode.myItem;
	    	currentNode = currentNode.myNext;
	    	return tmp;
	    }

	    public void remove() {
	        // not used; do not implement
	    }
	}
	
}
