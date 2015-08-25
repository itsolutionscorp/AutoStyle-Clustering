import java.util.*;

public class List {

	private ListNode myHead, myTail=null;
	private int mySize=0;

	public List() {
		myHead = null;
	}

	public boolean isEmpty() {
		return myHead == null;
	}

	// We can have direct access to the wrapping class even though it is private.
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
		if (size()!=((List)obj).size()){
			return false;
		}
		for (ListNode p = myHead, q = ((List)obj).myHead; p != null; p = p.myNext, q = q.myNext) {
			if (!q.myItem.equals(p.myItem)){
				return false;
			}
		}
		return true;
	}

	public void add (Object x) {
		if (isEmpty()){
			myHead = new ListNode (x);
			myTail = myHead;
		} else {
			myTail.myNext = new ListNode (x);
			myTail = myTail.myNext;
		}
		mySize++;
	}

	public void appendInPlace (List l) {
		if (!l.isEmpty()){
			if (isEmpty()){
				myHead = l.myHead;
			} else {
				myTail.myNext = l.myHead;
			}
			myTail = l.myTail;
			mySize += l.mySize;
		}
	}
	
	public boolean isOK() {
		if (myHead == null && myTail == null){
			return true;
		} else if (myHead == null || myTail == null){
			return false;
		}
		int rtn = 0;
		ListNode temp = null;
		for (ListNode p = myHead; p != null; p = p.myNext) {
			if (p.myItem == null){
				return false;
			}
			if (p.myNext == null){
				temp = p;
			}
			rtn++;
		}
		if (mySize != rtn || temp != myTail){
			return false;
		}
		return true;
	}
	
	//This function may not be of the simplest form.
	public List remove(Object obj) {
		ListNode p = myHead;
		while (p != null) {
			if (p.myItem.equals(obj)){
				if (p.myNext!=null){
					p.myItem = p.myNext.myItem;
					p.myNext = p.myNext.myNext;
				} else {
					// Wrong : p = null;
					ListNode q = myHead;
					while( q!= null) {
						if (q.myNext != null){
							if (q.myNext.myNext != null){
								q = q.myNext;
							} else {
								myTail = q; // Important!
								q.myNext = null;
								return this;
							}
						} else {
							myHead = null;
							myTail = null;
							return this;
						}
						
					}
				}
			} else {
				p = p.myNext;
			}
		}		
		return this;
	}
	
	
	/** Version 1: Recursion. Of same Importance !!!
	public void reverse() {
		ListNode p = null;
		myTail = myHead; // Important!
		myHead = reverseHelper(myHead, p);
	}

	private static ListNode reverseHelper(ListNode l, ListNode soFar) {
	    if (l == null) {
	        return soFar;
	    } else {
	    	ListNode temp = l.myNext;
	    	l.myNext = soFar;
	    	return reverseHelper ( temp , l );
	    }
	}
	**/
	
	// Version 2 : Iterative
	public void reverse() {
		myTail = myHead; // Important!
		myHead = reverseHelper(myHead);
	}

	private static ListNode reverseHelper(ListNode head) {
		ListNode p, soFar;
	    // p plays the role of l in the previous version.
	    for (p = head, soFar = null; p != null;) {
	        ListNode temp = p;
	        p = p.myNext;
	        temp.myNext = soFar;
	        soFar = temp; // Important!
	    }
	    return soFar;
	}
	
	
	public void doubleInPlace() {
	    for (ListNode p = myHead; p != null; p = p.myNext) {
	        p.myNext = new ListNode (p.myItem, p.myNext);
	        p = p.myNext;
	        myTail = p;
	    }
	}
	
	public Iterator iterator() {
	    return new ElementIterator();
	}

	public class ElementIterator implements Iterator {

	    private int iterIndex;

	    public ElementIterator() {
	    	iterIndex = 0;
	    }

	    public boolean hasNext() {
	    	ListNode temp = myHead;
	    	for (int i=0; i!=iterIndex; i++){
	    		if (temp==null){
	    			return false;
	    		}
	    		temp = temp.myNext;
	    	}
	        if (temp!=null){
	        	return true;
	        }
	        return false;
	    }

	    public Object next() {
	    	ListNode temp = myHead;
	    	for (int i=0; i!=iterIndex; i++){
	    		if (temp==null){
	    			return false;
	    		}
	    		temp = temp.myNext;
	    	}
	    	iterIndex++;
	    	return temp.myItem;
	    }

	    public void remove() {
	        // not used; do not implement
	    }
	}

}
