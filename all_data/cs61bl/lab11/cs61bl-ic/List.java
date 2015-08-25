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
		if (obj == null) {
			return false;
		}
		List lst  = (List) obj;
		if (lst.size() != size()) {
			return false;
		} else {
			ListNode currentNode = lst.myHead;
			for (ListNode p = myHead; p != null; p = p.myNext) {
				if (!currentNode.myItem.equals(p.myItem)) {
					return false;
				} else {
					currentNode = currentNode.myNext;
				}
			}
			return true;
		}
		
	}

	public void add (Object x) {
		if (myHead == null) {
			myHead = new ListNode(x);
			myTail = myHead;
			mySize++;
			return;
		}
		ListNode newTail = new ListNode(x);
		myTail.myNext = newTail;
		myTail = newTail;
		mySize++;
	}

	public void appendInPlace (List l) {
		if (myHead == null) {
			myHead = l.myHead;
			myTail = l.myTail;
			mySize += l.mySize;
			return;
		} else if (l.size() == 0) {
			return;
		}
		myTail.myNext = l.myHead;
		myTail = l.myTail;
		mySize += l.mySize;
	}
	
	public Iterator iterator() {
	    return new ElementIterator();
	}

	public class ElementIterator implements Iterator {

	    // State variable(s) to be provided.
		ListNode current;

	    public ElementIterator() {
	        // TODO code to be provided
	    	current = myHead;
	    }

	    public boolean hasNext() {
	        return current != null;
	    }

	    public Object next() {
	        Object result = current.myItem;
	        current = current.myNext;
	        return result;
	    }

	    public void remove() {
	        // not used; do not implement
	    }
	}
	
	public void isOK() {
		if (mySize == 0) {
			if (myHead != null || myTail != null) {
				throw new IllegalStateException("1");
			}
			return;
		}
		int count = 0;
		ListNode p = myHead;
		while (p.myNext != null) {
			if (p.myItem == null) {
				throw new IllegalStateException("2");
				
			}
			p = p.myNext;
			count++;
		}
		if (myTail != p) {
			throw new IllegalStateException("3");
		}
		if (count+1 != mySize) {
			throw new IllegalStateException("4");
		}
		
		if (p.myItem == null) {
			throw new IllegalStateException("5");
		}
	}
	
	public void remove(Object obj) {
		if (myHead == null) {
			return;
		}
		while (myHead.myItem.equals(obj)) {
			myHead = myHead.myNext;
			mySize--;
			if (myHead == null) {
				myTail = null;
				return;
			}
		}
		ListNode q = myHead;
		for (ListNode p = myHead.myNext; p != null; p = p.myNext) {
			if (p.myItem.equals(obj)) {
				q.myNext = p.myNext;
				mySize--;
			} else {
				q = q.myNext;
			}
			if (p.myNext == null) {
				myTail = q;
			}
		}
	}
	
	public void doubleInPlace() {

	    for (ListNode p = myHead; p != null; p = p.myNext) {
	        p.myNext = new ListNode(p.myItem, p.myNext);
	        p = p.myNext;
	    }
	    if (myTail != null) {
	    	myTail = myTail.myNext;
	    }
	    mySize *= 2;
	    // TODO And maybe here as well
	}
	
	public void reverse() {
		ListNode temp = myHead;
	    myHead = reverseHelper(myHead);
	    myTail = temp;
	    
	}

	public static ListNode reverseHelper(ListNode l, ListNode soFar) {
	    if (l == null) {
	    	return soFar;
	    } else {
	    	ListNode p = l;
	    	l = l.myNext;
	    	p.myNext = soFar;
	    	return reverseHelper(l, p);
	    }
	}
	
	private static ListNode reverseHelper(ListNode head) {
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
	
}
