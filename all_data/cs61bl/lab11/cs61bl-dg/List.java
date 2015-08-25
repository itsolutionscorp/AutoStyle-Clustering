import java.util.*;

public class List implements Iterable{

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

	public Iterator iterator() {
    	return new ElementIterator();
	}

	public class ElementIterator implements Iterator {

	    ListNode nextnode;

	    public ElementIterator() {
	        nextnode = myHead;
	    }

	    public boolean hasNext() {
	        return nextnode != null;
	    }

	    public Object next() {
	        Object toreturn = nextnode.myItem;
			nextnode = nextnode.myNext;
			return toreturn;
	    }

	    public void remove() {
	        return;
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
		if (myTail == null) {myTail = myHead;}
		mySize++;
	}

	public boolean equals (Object obj) {
		ListNode r = ((List)obj).myHead;
		for (ListNode p = myHead; p != null; p = p.myNext) {
			if (r == null || !p.myItem.equals(r.myItem)) {return false;}
			r = r.myNext;
		}
		return (r == null);
	}

	public void add (Object x) {
		if (myTail == null) {
			addToFront(x);
		} else {
		myTail.myNext = new ListNode(x);
		myTail = myTail.myNext;
		mySize++;
		}
	}

	public void appendInPlace (List l) {
		if (myTail == null) {
			myHead = l.myHead;
			myTail = l.myTail;
			mySize = l.mySize;
		} else {
		myTail.myNext = l.myHead;
		mySize += l.mySize;
		if (l.myTail != null) {myTail = l.myTail;}
		}
	}

	public boolean isOK () {
		int size = 0;
		ListNode last = null;
		for (ListNode p = myHead; p != null; p = p.myNext) {
			if (p.myItem == null) {return false;}
			last = p;
			size++;
		}
		if (mySize == 0) {return (myHead == null || myTail == null);}
		if (mySize == 1) {return (myHead == myTail);}
		return ((myTail == last) && (mySize == size));
	}

	public void remove(Object obj) {
		while (myHead != null && myHead.myItem.equals(obj)) {
			myHead = myHead.myNext;
			mySize--;
		}
		if (myHead == null) {
			return;
		}
		for (ListNode p = myHead; p.myNext != null; p = p.myNext) {
			if (p.myNext.myItem.equals(obj)) {
				p.myNext = p.myNext.myNext;
				mySize--;
				if (p.myNext == null) {
					myTail = p;
					return;
				}
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
	    reverseHelper(this.myHead, null);
		ListNode temp = myHead;
		myHead = myTail;
		myTail = temp;
	}

	private static ListNode reverseHelper(ListNode l, ListNode soFar) {
		if (l == null) {
		    return soFar;
		} else {
			ListNode temp = l.myNext;
			l.myNext = soFar;
			return reverseHelper(temp,l);
		}
	}

	public void reverseInPlace() {
		reverseHelper(myHead);
		ListNode temp = myHead;
		myHead = myTail;
		myTail = temp;
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

}
