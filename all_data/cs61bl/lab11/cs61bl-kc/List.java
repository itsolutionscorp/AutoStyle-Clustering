import java.util.*;

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
	
	public boolean isOK(List l) {
		int count = 0;
		for (ListNode p = myHead; p != null; p = p.myNext) {
			if (p.myItem==null) {
				return false;
			}
		}
		if ((mySize==count) && ((myHead==null && myTail==null)|| myTail==get(mySize-1))){ 
			return true; 
			}
		return false;
	}
	
	public void remove(Object obj) {
		for (ListNode p = myHead; p != null; p = p.myNext) {
			if (p.myItem.equals(obj)) {
				p.myItem = p.myNext.myItem;
				p.myNext = p.myNext.myNext;
			}
		}
		mySize--;
	}
	
	public void reverse() {
	   if (myHead != null) {
		   myHead = reverseHelper(myHead, null);
	   }
	}

	private static ListNode reverseHelper(ListNode l, ListNode soFar) {
	    if (l == null) {
	        return soFar;
	    } else {
            ListNode temp = l.myNext;
            l.myNext = soFar;
            return reverseHelper(temp, l);
        }
	}
	
	
	
	public void doubleInPlace() {

	    for (ListNode p = myHead; p != null; p = p.myNext) {
	        p.myNext = new ListNode(p.myItem, p.myNext);
	        p = p.myNext;
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
		return (this.toString().equals(obj.toString()));
	}

	public void add (Object x) {
		if (this.isEmpty()) {
			this.myHead = new ListNode(x);
			mySize++;
		}
		else {
			for (ListNode p = myHead; p != null; p = p.myNext){ 
			if (p.myNext == null){
				ListNode last = new ListNode(x);
				p.myNext = last;
				this.myTail = p.myNext;
				mySize++;
				return;
			}
		}
	}
}
	
	public void appendInPlace (List l) {
		if (this.isEmpty()) {
				this.myHead = l.myHead;
			}
		else {
			for (ListNode p = myHead; p != null; p = p.myNext){ 
			if (p.myNext == null){
				p.myNext = l.myHead;
				return;
			}
		}
	}
	this.mySize = this.mySize + l.mySize;
	this.myTail = l.myTail;
}
	
	
	public Iterator iterator() {
	    return new ElementIterator();
	}

	public class ElementIterator implements Iterator {

	    // State variable(s) to be provided.
		private ListNode iteratedList;
		
	    public ElementIterator() {
	    	 iteratedList = myHead;
	    	 
	        // TODO code to be provided
	    }
	    

	    public boolean hasNext() {
	    	if (iteratedList == null) {
	    		return false;
	    	}
	    	else {
	    		return iteratedList.myNext != null;
	    	}
	    }	    
	    
	    public Object next() {
	    		Object current = iteratedList.myItem;
	    		iteratedList = iteratedList.myNext;
	    		return current;
	    }
	    

	    public void remove() {
	        // not used; do not implement
	    }
	}
}