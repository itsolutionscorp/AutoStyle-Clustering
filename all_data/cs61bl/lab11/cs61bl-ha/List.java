import java.util.Iterator;

public class List {

	private ListNode myHead;
	private int mysize;
	private ListNode mytail;

	public List() {
		myHead = null;
		mytail = null;
		mysize = 0;
	}

	public boolean isEmpty() {
		return myHead == null;
	}
	
	public boolean isOK() {
		int rtn = 0;
		if (myHead == null ) {
			if (mytail != null) {
				return false;
			}
		}
		
		for (ListNode p = myHead; p != null; p = p.myNext) {
			rtn++;
			if (p.myItem == null) {
				return false;
			}
			
			if (p.myNext == null) {
				if (p != mytail) {
					return false;
				}
			}
		}
		return rtn == mysize;
	}
	
	public void remove (Object element) {
		if (myHead != null) {
			for (ListNode p = myHead; p != null && p.myNext != null; p = p.myNext) {
				if (p.myNext.myItem.equals(element)) {
					if (p.myNext.myNext != null) {
						p.myNext = p.myNext.myNext;
					} else {
						p.myNext = null;
					}
				}
			}
			if (myHead.myItem.equals(element)) {
				myHead = myHead.myNext;
			}
		}						
	}
	
//	---Recursive---
	
	public void reverse() {
		myHead = reverseHelper(myHead);
	}

	public static ListNode reverseHelper(ListNode l, ListNode soFar) {
		if (l == null) {
	        return soFar;
	    } else {
	    	ListNode temp = l.myNext;
	    	l.myNext = soFar;
	    	return reverseHelper(temp, l);
	    }
	}

//	---Iterative---	
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
	
		
	public Iterator iterator() {
	    return new ElementIterator();
	}

	public class ElementIterator implements Iterator {

	    // State variable(s) to be provided.
		private ListNode currentnode;

	    public ElementIterator() {
	    	currentnode = myHead;
	    }

	    public boolean hasNext() {
	    	if (currentnode!= null) {
	    		return true;
	    	} else {
	    		return false;
	    	}
	    }
	    
	    public Object next() {
	    	if (hasNext()) {
	    		ListNode temp = currentnode;
	    		currentnode = currentnode.myNext;
	    		return temp.myItem;
	    	}
	        return null;
	    }

	    public void remove() {
	        // not used; do not implement
	    }
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
		return mysize;
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
		mysize ++;
	}

	public boolean equals (Object obj) {
		// TODO Your code here
		
		if (size() != ((List) obj).size()) {
			return false;
		} else {
			for (int i = 0; i < size(); i++) {
				if (!get(i).equals(((List) obj).get(i))) {
					return false;
				}
			}
			return true;
		}
	
	}

	public void add (Object x) {
		
		if (isEmpty()) {
			myHead = new ListNode (x);
			mytail = myHead;
			mysize++;
		} else { 
			mytail.myNext =  new ListNode (x);
			mytail = mytail.myNext;
			mysize++;
		}
	}

	public void appendInPlace (List l) {
		// TODO Your code here
		if(l.myHead != null) {
			if (myHead == null) {
				myHead = l.myHead;
				mytail = l.mytail;
				mysize = l.mysize;
			} else { 
				mytail.myNext = l.myHead;
				mytail = l.mytail;
				mysize += l.mysize;
			}
		}
	}

}
