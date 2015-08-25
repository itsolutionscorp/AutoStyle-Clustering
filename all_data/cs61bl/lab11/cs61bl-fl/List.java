import java.util.Iterator;

public class List implements Iterable {

	private ListNode myHead;
	private ListNode myTail;
	private int mySize;

	public List() {
		myHead = null;
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
		
		public boolean equals (Object other) {
			if (other == null) return false;
			if (!(other instanceof ListNode)) return false;
			ListNode thisNext = this;
			ListNode objectNext = ((ListNode)other);
			while (thisNext != null && objectNext != null) {
				if (!thisNext.myItem.equals(objectNext.myItem)) return false;
				thisNext = thisNext.myNext;
				objectNext = objectNext.myNext;
			}
			if (thisNext != null || objectNext != null) return false;
			return true;
		}
		
		public ListNode add(Object o, ListNode where) {
			where.myNext = new ListNode(o);
			return where.myNext;
		}
		
		public void append (ListNode l, ListNode where) {
			where.myNext = l;
		}
		
		public String toString () {
			return myItem.toString();
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
		return rtn;
	}

	// Return true if the list contains the object 
	public boolean contains (Object obj) {
		for (ListNode p = myHead; p != null; p = p.myNext) {
			if (p.myItem.equals (obj)) {
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
		mySize ++;
	}

	public boolean equals (Object obj) {
		if (!(obj instanceof List)) return false;
		List lObj = (List)(obj);
		if (myHead == null) return lObj.myHead == null;
		return this.myHead.equals(lObj.myHead);
	}

	//Adds to end.
	public void add (Object x) {
		if (this.myHead == null) {
			this.myHead = new ListNode(x);
			this.myTail = myHead;
		} else {
			myTail = this.myHead.add(x,myTail);
		}
		mySize ++;
	}

	//Add List l to the end of this list.
	public void appendInPlace (List l) {
		if (l == null || l.myHead == null) return;
		if (myHead == null) {
			myHead = l.myHead;
			myTail = l.myTail;
			return;
		}
		myHead.append(l.myHead,myTail);
		myTail = l.myTail;
		mySize += l.mySize;
	}
	
	public void remove (Object o) {
		if (myHead == null) return;
		ListNode temp = myHead;
		ListNode previous = null;
		while (temp != null) {
			if (temp.myItem == null || temp.myItem.equals(o)) {
				if (previous == null) {
					myHead = myHead.myNext;
				}
				else {
					previous.myNext = temp.myNext;
				}
			}
			else {
				previous = temp;
			}
			temp = temp.myNext;
		}
	}
	
	public void doubleInPlace() {
		
		if (myHead == null) return;
		
		ListNode last = myHead;
	    for (ListNode p = myHead; p.myNext != null; p = p.myNext.myNext) {
	        ListNode duplicate = new ListNode(p.myItem, p.myNext);
	        p.myNext = duplicate;
	        last = p.myNext.myNext;
	        //p = duplicate.myNext;
	    }
	    ListNode duplicate1 = new ListNode(last.myItem);
	    last.myNext = duplicate1;
	    // TODO And maybe here as well
	}
	
	public void reverse () {
        if (myHead == null || myHead.myNext == null) return;
		myTail = myHead;
		myHead = reverseHelper(myHead,null);
		myTail.myNext = null;
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
	
	private static ListNode reverseHelper (ListNode head) {
		if (head.myNext == null) return head;
		ListNode previous = head;
		ListNode next = head;
		for (ListNode p = head.myNext; next != null; p = next) {
			next = p.myNext;
			p.myNext = previous;
			previous = p;
		}
		return previous;
	}
	
	public Iterator iterator() {
	    return new ElementIterator();
	}

	public class ElementIterator implements Iterator {

	    // State variable(s) to be provided.
		private ListNode myPosition;

	    public ElementIterator() {
	        // TODO code to be provided
	    	myPosition = myHead;
	    }

	    public boolean hasNext() {
	    	if (myPosition == null) return false;
	        return myPosition.myNext != null;
	        // TODO code to be provided
	    }

	    public Object next() {
	    	ListNode nextPosition = myPosition;
	    	myPosition = myPosition.myNext;
	        return nextPosition.myItem;
	        // TODO code to be provided
	    }

	    public void remove() {
	        // not used; do not implement
	    }
	}
	
	public void isOK() {
		int checkMySize = 0;
		ListNode temp = myHead;
		boolean nonNull = true;
		while (temp != null) {
			if (temp.myItem == null) {
				nonNull = false;
				break;
			}
			checkMySize++;
			temp = temp.myNext;
		}
		if (checkMySize != mySize) throw new IllegalStateException("size is inconsistent");
		
		if (nonNull == false) throw new IllegalStateException("list contains null item(s)");
		
		if (!(myTail.myNext == null)) throw new IllegalStateException("myTail is pointing to the wrong list item");
		
	}

}
