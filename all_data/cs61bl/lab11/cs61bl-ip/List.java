import java.util.Iterator;

public class List {

	private int mySize;
	private ListNode myHead;
	private ListNode myTail;

	public List() {
		mySize = 0;
		myHead = null;
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
	
	public Iterator iterator() {
	    return new ElementIterator();
	}

	public class ElementIterator implements Iterator {
		
		private int index;
		
	    public ElementIterator() {
	        this.index = 0;
	    }

	    public boolean hasNext() {
	    	return !(index == size());
	    }

	    public Object next() {
	    	index++;
	    	return get(index - 1);
	    }

		@Override
		public void remove() {
			// TODO Auto-generated method stub
			
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
		int index = 0;
		while (index < size() && index < ((List) obj).size()) {
			if (!get(index).equals(((List) obj).get(index)))
				return false;
			index++;
		}
		if (size() == ((List) obj).size())
			return true;
		return false;
			
	}

	public void add (Object x) {
		ListNode a = new ListNode(x);
		if (myHead == null)
			myHead = a;
		else {
			ListNode temp = myHead;
			while (temp.myNext != null)
				temp = temp.myNext;
			temp.myNext = a;
		}
		myTail = a;
		mySize++;
	}

	public void appendInPlace (List l) {
		if (myHead == null)
			myHead = l.myHead;
		else {
			ListNode temp = myHead;
			while (temp.myNext != null)
				temp = temp.myNext;
			temp.myNext = l.myHead;
		}
		mySize += l.size();
		myTail = l.myTail;
	}
	
	public void remove (Object x) {
		if (myHead != null) {
			if (myHead.myItem.equals(x)) {
				myHead = myHead.myNext;
				mySize--;
			}
			if (myHead == null)
				return;
			
			ListNode temp = myHead;
			
			while (temp != null) {
				if (temp.myNext.myItem.equals(x)) {
					if (temp.myNext.myNext != null)
						temp.myNext = temp.myNext.myNext;
					else
						temp.myNext = null;
					mySize--;
				}
				temp = temp.myNext;
			}
		}
	}
	
	public void doubleInPlace() {
	    for (ListNode p = myHead; p != null; p = p.myNext) {
	        ListNode d = new ListNode(p.myItem, p.myNext);
	        p.myNext = d;
	        p = p.myNext;
	    }
	}
	
	public void reverse() {
		//myHead = reverseHelper(myHead, null);
		myHead = reverseHelper(myHead);
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
	
	private static ListNode reverseHelper(ListNode head) {
	    ListNode p, soFar;
	    // p plays the role of l in the previous version.
	    
	    for (p = head, soFar = null; p != null;) {
	    	ListNode temp = p;
	    	
	    	temp = temp.myNext;
	    	p.myNext = soFar;

	    	soFar = p;
	    	p = temp;
	    }
	    return soFar;
	}
	
	/**
	 * Checks if:
	 * 	- The value stored in mySize is the number of nodes in this list
	 * 	- All myItem objects in this list are non-null
	 *  - Either both myHead and myTail are null, or myTail is a reference 
	 *  	to the last node in the list whose first node is the node that myHead refers to.
	 **/
	public boolean isOk() {
		int sizeCheck = 0;
		ListNode tailCheck = myHead;
		if (myHead != null)
			sizeCheck++;
		
		while (tailCheck.myNext != null) {
			tailCheck = tailCheck.myNext;
			sizeCheck++;
		}
		
		if (myHead == null && tailCheck == null && sizeCheck == mySize)
			return true;
		else if (tailCheck == myTail && sizeCheck == mySize)
			return true;
		return false;
		
	}

}
