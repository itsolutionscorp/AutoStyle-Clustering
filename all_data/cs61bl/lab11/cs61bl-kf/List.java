import java.util.Iterator;

public class List {

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
		//		int rtn = 0;
		//		for (ListNode p = myHead; p != null; p = p.myNext) {
		//			rtn++;
		//		}
		//		return rtn;
		return this.mySize;
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
		mySize ++;
	}

	public boolean equals (Object obj) {
		// TODO Your code here 
		if (this.size() != ((List) obj).size()) {

			return false;
		} else {
			ListNode q = ((List) obj).myHead;
			for (ListNode p = myHead; p != null; p = p.myNext) {
				if (!p.myItem.equals(q.myItem)) {
					return false;
				}
				q = q.myNext;
			}
			return true;
		}		
	}

	public void add (Object x) {
		// TODO Your code here 
		if (this.isEmpty()) {
			this.addToFront(x);
			myTail = myHead;
		} else {
			for (ListNode p = myHead; p!=null; p = p.myNext) {
				if (p.myNext == null) {
					p.myNext = new ListNode (x);
					mySize ++;
					myTail = p.myNext;
					break;
				}
			}
		}
	}
	
	public void remove (Object x) {
		if (this.isEmpty()) {
			return;
		}
		
		int counter = 0;
		ListNode pointer = this.myHead; 
		if (this.get(0).equals(x)) {
			myHead = myHead.myNext;
			mySize --;
		}
		
		while (counter < this.size() - 1) {
			if (this.get(counter + 1).equals(x) && counter != mySize -1) {
				pointer.myNext = pointer.myNext.myNext;
				mySize --;
			}
			
			else if (this.get(counter + 1).equals(x)) {
				pointer.myNext = null;
				mySize --;
				myTail = pointer;
			}
			counter ++;
			pointer = pointer.myNext;
		}
		
	}
	
	public void appendInPlace (List l) {
		// TODO Your code here \
		if (this.isEmpty()) {
			this.myHead = l.myHead;
			this.mySize = l.mySize;
			this.myTail = l.myTail;
		}else {
			ListNode p = myHead;
			while (p.myNext != null) {
				p = p.myNext;
			}
			p.myNext = l.myHead;
			this.mySize += l.mySize;
			this.myTail = l.myTail;
		}
	}
	
	public void doubleInPlace() {
		if (this.isEmpty()) {
			return;
		}
		
		ListNode p1 = myHead.myNext;		
		
	    for (ListNode p = myHead; p != null; p = p.myNext) {
	        // TODO Your code here
	    	if (p1 == null) {
	    		p.myNext = new ListNode(p.myItem);
	    		break;
	    	}
	    	p.myNext = new ListNode(p.myItem, p1);
	    	
	    	p1 = p1.myNext;	
	    	p = p.myNext;	    	
	    }
	    // TODO And maybe here as well
	    mySize *= 2;
	}

	public Iterator iterator() {
		return new ElementIterator();
	}

	public class ElementIterator implements Iterator {

		// State variable(s) to be provided.
		int index;

		public ElementIterator() {
			// TODO code to be provided
			index = -1;
		}

		public boolean hasNext() {
			// TODO code to be provided
			if (index >= size() - 1) {
				return false;
			}
			return true;
		}

		public Object next() {
			// TODO code to be provided
			index ++;
			return get(index);
		}

		public void remove() {
			// not used; do not implement
		}
	}
	
	public void reverse() {
		this.myHead = reverseHelper(this.myHead, null);
//		this.myHead = reverseHelper(this.myHead);

	}

	private static ListNode reverseHelper(ListNode l, ListNode soFar) {
	    if (l == null) {
	        return soFar;
	    } else {
	    	ListNode temp = l.myNext;
	    	l.myNext = soFar;
	    	soFar = l;
	    	l = temp;
	    	return reverseHelper(l, soFar);   
	    }
	}
//	
//	private static ListNode reverseHelper(ListNode head) {
//	    ListNode p, soFar;
//	    // p plays the role of l in the previous version.
//	    for (p = head, soFar = null; p != null;) {
//	        ListNode temp = head.myNext;
//	        p.myNext = soFar;
//	        soFar = p;
//	        p = temp;
//	    }
//	    return soFar;
//	}

	public boolean isOK() {
		int rtn = 0;
		for (ListNode p = myHead; p != null; p = p.myNext) {
			rtn++;
			if (p.myItem == null) {
				return false;
			}
			if (p.myNext == null) {
				if(myTail != p) {
					return false;
				}
			}
		}
		if (rtn != mySize) {
			return false;
		}

		if (myHead != null && myTail == null) {
			return false;
		}
		else {
			return true;
		}
	}
	
	
}

