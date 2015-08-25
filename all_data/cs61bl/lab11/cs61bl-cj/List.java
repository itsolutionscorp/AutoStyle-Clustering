import java.util.*;

public class List {

	private ListNode myHead;
	
	private ListNode myTail;
	
	private int mySize;

	public List() {
		myHead = null;
		myTail = null;
	}

	public boolean isEmpty() {
		return myHead == null;
	}

	public void remove(Object x){
		r_remove(myHead, x);
	}
	
	public void r_remove(ListNode l, Object x){
		if(l == null){
			return;
		}
		if (l.myNext == null){
			if (l.myItem.equals(x)){
				myTail = null;
			}
			return;
		}
		if (l.myItem.equals(x)){
			myHead = l.myNext;
		}
		if (l.myNext.myItem.equals(x)) {
			l.myNext = l.myNext.myNext;
		}
		r_remove(l.myNext, x);
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

	    // State variable(s) to be provided.
		int i;
		ListNode current;

	    public ElementIterator() {
	        // TODO code to be provided
	    	current = myHead;
	    	i = 0;
	    }

	    public boolean hasNext() {
	        // TODO code to be provided
	    	if (current == null){
	    		return false;
	    	}
	    	if (current.myNext == null){
	    		return false;
	    	}
	    	else{
	    		return true;
	    	}
	    }

	    public Object next() {
	        	ListNode temp = current;
	        	current = current.myNext;
	        	return temp.myItem;
	        // TODO code to be provided
	    }

	    public void remove() {
	        // not used; do not implement
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
		mySize += 1;
	}

	public boolean equals (Object obj) {
		// TODO Your code here 
		if (obj instanceof List){
			List a = (List) obj;
			if (myHead == null && a.myHead == null){
				return true;
			}
			if (this == null && a != null || this != null && a == null){
				return false;
			}
			if (this.size() != a.size()){
				return false;
			}
			if (!this.myHead.myItem.equals(a.myHead.myItem)){
				return false;
			}
			else {
				for (int i = 0; i < size(); i += 1){
					if (!this.get(i).equals(a.get(i))){
						return false;
					}
				}
				return true;
			}
		}
		
		return true;
	}

	public void add (Object x) {
		// TODO Your code here 
		if (this.isEmpty()){
			myHead = new ListNode(x, null);
			mySize += 1;
			myTail = myHead;
		}
		else{
			for (ListNode p = myHead; p != null; p = p.myNext){
				if (p.myNext == null){
					p.myNext = new ListNode(x, null);
					mySize += 1;
					myTail = p.myNext;
					return;
				}
			}
		}
		 
	}

	public void appendInPlace (List l) {
		if (l.myHead == null) {
			return;
		} 
		if (myHead == null){
			myHead = l.myHead;
			myTail = l.myTail;
			mySize = l.mySize;
		}
		else {
			for (ListNode p = myHead; p != null; p = p.myNext) {
				if (p.myNext == null){
					p.myNext = l.myHead;
					myTail = p.myNext;
					mySize += 1;
					return;
					}
				}
				
				
			}
		}
	
	
	public boolean isOK() {
		int count = 0;
		for (ListNode p = myHead; p != null; p = p.myNext) {
			count++;
			if (p.myItem == null) {
				return false;
			}
		}
		if (myHead == null) {
			if (myTail != null) {
				return false;
			}
		}
		if (myTail == null) {
			if (myHead != null) {
				return false;
			}
		}
		
		if (myTail.myNext != null) {
			return false;
		}
		if (get(0) != myHead.myItem) {
			return false;
		}
		
		if (count != mySize) {
			return false;
		}
		return true;
	}
	
	
	private static ListNode reverseHelper(ListNode l, ListNode soFar) {
	    if (l == null) {
	        return soFar;
	    } else {
	        return l;
	    }
	}
	
	
	
	
	}
