//import lab10.NonemptyListNode;

//import lab10.AbstractListNode;

import java.util.*;

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
		int rtn = 0;
		for (ListNode p = myHead; p != null; p = p.myNext) {
			rtn++;
		}
		mySize = rtn;
		return rtn;
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
		myTail = myHead;
		mySize ++;
	}

	public boolean equals (Object obj) {
		// TODO Your code here 
		
		if (this.size() != (((List)obj).size())) {
    		return false;
    	} else {
			ListNode p = ((List) obj).myHead;
    		for(ListNode node = myHead; node != null; node = node.myNext) {
    			if (!(node.myItem.equals(p.myItem))) {
    				return false;
    			}
    			p = p.myNext;
    		}
    		return true;
    	}
	}

	public void add (Object x) {
		// TODO Your code here 
		if (isEmpty()) {
    		myHead = new ListNode(x, null);
    		myTail = myHead;
    	} else {
    		for (ListNode node = myHead; node!= null; node = node.myNext) {
    			if (node.myNext == null) {
    				node.myNext = new ListNode(x, null);
    				myTail = node.myNext;
    				mySize++;
    				break;
    			}
    		}
    	}
	}

	public void appendInPlace (List l) {
		// TODO Your code here 
		if (isEmpty()) {
			myHead = l.myHead;
			myTail = l.myTail;
		} else {
			for (ListNode node = myHead; node != null; node = node.myNext) {
				if (node.myNext == null) {
					node.myNext = l.myHead;
					myTail = l.myTail;
					mySize = mySize + l.mySize;
					break;
				}
			}
		}
	}
	
	public static void isOk (List l) {
		if (l.mySize != l.size()) {
			throw new IllegalStateException("There's something wrong with mySize.");
		}
		ListNode last = null;
		for (ListNode node = l.myHead; node != null; node = node.myNext) {
			if (node.myItem == null) {
				throw new IllegalStateException("You gots a null in yo list.");
			}
			last = node;
		}
		if (last != l.myTail || (l.myHead == null && l.myTail != null) && (l.myTail == null && l.myHead != null)) {
			throw new IllegalStateException("There something wrong with yo head and tail.");
		}
	}
	
	public void remove(Object obj) {
		ListNode head = myHead;
		while (myHead.myItem.equals(obj)) {
			myHead = myHead.myNext;
			head.myNext = null;
		}
		ListNode prev = myHead;
		for (ListNode node = myHead; node != null; node = node.myNext) {
			if (node.myItem.equals(obj)) {
				prev.myNext = node.myNext;
				node.myNext = null;
				node = prev;
				mySize--;
			} else {
				prev = node;
			}
			if (node.myNext == null) {
				myTail = node;
			}
		}
	}
	
	public void doubleInPlace() {
		ListNode next;
	    for (ListNode p = myHead; p != null; p = p.myNext) {
	        // TODO Your code here
	    	next = p.myNext;
	    	p.myNext = new ListNode(p.myItem, next);
	    	p = p.myNext;
	    	mySize++;
	    }
	    // TODO And maybe here as well
	}
	
	public void reverse() {
		myHead = reverseHelper(this.myHead);
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
	
	public static ListNode reverseHelper(ListNode head) {
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
	
	public Iterator iterator() {
	    return new ElementIterator();
	}

	public class ElementIterator implements Iterator {

	    // State variable(s) to be provided.
		private int index;

	    public ElementIterator() {
	        // TODO code to be provided
	    	index = 0;
	    }

	    public boolean hasNext() {
	        // TODO code to be provided
	        if (index < size()) {
	        	return true;
	        }
	        return false;
	    }

	    public Object next() {
	        // TODO code to be provided
	    	ListNode p = myHead;
	    	for (int i = 0; i <= index; i++) {
	    		if (i == index) {
	    			index++;
	    			return p.myItem;
	    		} 
	    		p = p.myNext;
	    	}
	    	return null;
	    }

	    public void remove() {
	        // not used; do not implement
	    }
	}
	
}
