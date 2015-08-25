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
			myTail = p;
		}
		mySize = rtn;
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
		return ((this.toString()).equals(obj.toString()));
	}

	public void add (Object x) {
		if (isEmpty()) {
			addToFront(x);
			return;
		}
		ListNode p = myHead;
		while (p.myNext != null) {
			p = p.myNext;
		}
		p.myNext = new ListNode(x);
		myTail = p.myNext;
		mySize++;
		
	}

	public void appendInPlace (List l) {
		if (isEmpty()) {
			myHead = l.myHead;
			return;
		}
		ListNode p = myHead;
		while (p.myNext != null) {
			p = p.myNext;
		}
		p.myNext = l.myHead;
		myTail = l.myTail;
		mySize += l.mySize;
	}
	
	public void remove(Object obj) {
		ListNode myPrev = myHead;
		ListNode p = myHead;
		if (myHead == null || obj == null) {
			return;
		}
		while (p.myItem.equals(obj)) {
			myHead = myHead.myNext;
			p = myHead;
			if (myHead == null) {
				myTail = null;
			}
			mySize--;
		}
		for (p = myHead; p != null; p = p.myNext) {
			if (p.myItem.equals(obj)) {
				myPrev.myNext = p.myNext;
				mySize--;
			}
			myPrev = myTail = p;
		}
	}
	
	public void doubleInPlace() {
	    for (ListNode p = myHead; p != null; p = p.myNext) {
	        p.myNext = new ListNode(p.myItem, p.myNext);
	        p = p.myNext;
	    }
	}
	
	public void reverse() {
		if (myHead != null) {
//			myHead = reverseHelper(myHead, null);
			myHead = reverseHelper(myHead);
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
	
	public void isOk() {
		size();
		int count = 0;
		if (isEmpty()) {
			if (count != mySize) {
				throw new IllegalStateException("mySize is should equal the number of nodes in this list.");
			} else if (!(myHead == null && myTail == null)) {
				throw new IllegalStateException("If one ListNode is null, the other should be as well.");
			}
		}
		count++;
		ListNode p = myHead;
		ListNode checkTail = p;
		while (p.myNext != null) {
			if (p.myItem == null) {
				throw new IllegalStateException("myItem should not be null.");
			}
			p = p.myNext;
			checkTail = p;
			count++;
		}
		if (count != mySize) {
			throw new IllegalStateException("mySize is should equal the number of nodes in this list.");
		} else if (checkTail != myTail) {
			throw new IllegalStateException("myTail must refer to the last node in the list.");
		}
	}
	
	public Iterator iterator() {
	    return new ElementIterator();
	}

	public class ElementIterator implements Iterator {

		private Object nextItem;
	    private ListNode nextNode;

	    public ElementIterator() {
	    	nextNode = myHead;
	    }

	    public boolean hasNext() {
	        return (nextNode != null);
	    }

	    public Object next() {
	        if (hasNext()) {
	        	nextItem = nextNode.myItem;
	        	nextNode = nextNode.myNext;
	        }
	        return nextItem;
	    }

	    public void remove() {
	        // not used; do not implement
	    }
	}


}
