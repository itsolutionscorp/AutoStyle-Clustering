import java.util.*;

public class List implements Iterable {

	private ListNode myHead;
	private int mySize;
	private ListNode myTail;

	public List() {
		myHead = null;
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
		if (!(obj instanceof List)) {
			return false;
		}
		if (((List) obj).size()!=size()) {
			return false;
		}
		for (int i = 0; i < size(); i++) {
			if (get(i)!=((List) obj).get(i)) {
				return false;
			}
		}
		return true;
	}

	public void add (Object x) {
		if (isEmpty()) {
			myHead = myTail = new ListNode(x);
		} else {
			myTail.myNext=new ListNode(x);
			myTail = myTail.myNext;
			}
		mySize++;
	}

	public void appendInPlace (List l) {
		if (l!=null) {
			if (isEmpty()) {
				myHead = myTail = l.myHead;
			} else {
				myTail.myNext=l.myHead;
				while (myTail.myNext!= null) {
					myTail = myTail.myNext;
				}
			mySize +=l.size();
			}
		}
	}
	public Iterator iterator() {
	    return new ElementIterator();
	}
	///isOk should be void, throw exception
	public boolean isOk() {
		if (myHead == null && myTail == null && mySize == 0) {
			return true;
		}
		if (myHead == null ^ myTail == null) {
			return false;
		} else {
		int rtn = 0;
		ListNode p = myHead;
		while (p.myNext != null) {
			if (p.myItem == null) {
				return false;
			}
			p = p.myNext;
			rtn++;
			}
		return (rtn + 1 == mySize && p == myTail);
		}
	}
	
	public void remove(Object x) {
		if (isEmpty()) {
			return;
		}
		ListNode lastConfirmed = null;
		ListNode p = myHead;
		while (p != null) {
			if (p.myItem.equals(x)) {
				if (lastConfirmed == null) {
					myHead = myHead.myNext;
					p = myHead;
				} else {
					lastConfirmed.myNext = p.myNext;
					p = lastConfirmed;
				}
			} else {
				lastConfirmed = p;
				p = p.myNext;
			}
		}
	}
	
	public void doubleInPlace() {

	    for (ListNode p = myHead; p != null; p = p.myNext) {
	        p.myNext = new ListNode(p.myItem,p.myNext);
	        p = p.myNext;
	    }
	}
	public void reverse() {
	    if (isEmpty()) return;
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
	        ListNode temp = p.myNext;
	        p.myNext = soFar;
	        soFar = p;
	        p = temp;
	    }
	    return soFar;
	}
			

	public class ElementIterator implements Iterator {
		
	    // State variable(s) to be provided.
		private ListNode current;
	    public ElementIterator() {
	        current = myHead;
	    }

	    public boolean hasNext() {
	        return current != null;
	    }

	    public Object next() {
	    	if (! hasNext()) {
	    		throw new IllegalStateException("No Next Element");
	    	}
	    	Object toReturn = current.myItem;
	    	current = current.myNext;
	    	return toReturn;	  
	    }

	    public void remove() {
	        // not used; do not implement
	    }
	}

}
