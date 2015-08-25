import java.util.*;

public class List {

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
		myTail = myHead;
	}

	public boolean equals (Object obj) {
		if(((List) obj).size() != size()) {
			return false;
		}
		int i = 0;
		for(ListNode p = myHead; p != null; p = p.myNext) {
			if(!p.myItem.equals(((List) obj).get(i))){
				return false;
			}
			i++;
		}

		return true;
	}

	public void add (Object x) {
		if (myHead == null) {
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
		if (myHead == null) {
			myHead = l.myHead;
			myTail = l.myTail; 
			mySize += l.size();
		}
		else {
			if (l.myHead != null) {
				myTail.myNext = l.myHead;
				myTail = l.myTail; 
				mySize += l.size(); 
			}
		}
	}
	
	public boolean isOK() { 
		int rtn = 0; 
		for (ListNode p = myHead; p != null; p = p.myNext) {
			if (p.myItem == null) {
				return false; 
			}
			if (p.myNext == null) {
				if (!(myTail == p)) {
					return false;
				}
			}
			rtn++; 
		}
		if (!(rtn == mySize)) {
			return false;
		} else {
			return true; 
		}
	}
	
	public void remove(Object o) {
		ListNode previous = myHead; 
		for (ListNode p = myHead; p != null; p = p.myNext) {
			if (p.myItem.equals(o)) {
				if (p == myHead) {
					myHead = p.myNext;
				} else {
					previous.myNext = p.myNext;
				}
			}
			previous = p; 
		}
	}
	
	public void doubleInPlace() {
	    for (ListNode p = myHead; p != null; p = p.myNext) {
	    	ListNode temp = p.myNext;
	        p.myNext = new ListNode(p.myItem, temp); 
	        p = p.myNext;
	    }
	}
	
	public void reverseRecursive() {
		List soFar = new List(); 
		this.myHead = reverseHelperRecursive(this.myHead, soFar.myHead); 
	}
	
	private static ListNode reverseHelperRecursive(ListNode l, ListNode soFar) {
		if (l == null) {
			return soFar;
		} else {
			ListNode temp = l.myNext; 
			l.myNext = soFar;
			return reverseHelperRecursive(temp, l); 
		}
	}
	
	public void reverse() {
		this.myHead = reverseHelper(this.myHead); 
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


	public Iterator iterator() {
	    return new ElementIterator();
	}

	public class ElementIterator implements Iterator {

	    // State variable(s) to be provided.
		private ListNode p;

	    public ElementIterator() {
	    	p = myHead;
	    }

	    public boolean hasNext() {
			return !(p == null); 
	    }

	    public Object next() {
	    	if (hasNext()) {
	    		ListNode temp = p;
	    		p = p.myNext;
	    		return temp.myItem;
	    	} else {
	    		throw new IllegalStateException("There is no more list.");
	    	}
	    }

	    public void remove() {
	        // not used; do not implement
	    }
	}

}
