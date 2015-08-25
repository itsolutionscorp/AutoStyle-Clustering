import java.util.Iterator;



public class List {

	private ListNode myHead;
	private int mySize;
	private ListNode myTail;

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
		mySize++;
		myHead = new ListNode (obj, myHead);
	}

	@Override
	public boolean equals (Object lst) {
		// TODO Your code here 
		ListNode p = myHead;
		ListNode q = ((List) lst).myHead;
		if (isEmpty() && ((List) lst).isEmpty()) {
			return true;
		} else if (mySize != ((List) lst).mySize) {
			return false;
		} else {
			for (; p != null; p = p.myNext) {
				if (!p.myItem.equals(q.myItem)) {
					return false;
					}
				q = q.myNext;
			}
			return true;
		}
	}

	public void add (Object x) {
		// TODO Your code here
		mySize++;
		if (isEmpty()) {
			myHead = new ListNode(x);
			myTail = myHead;
		} else {
			myTail.myNext = new ListNode(x);
			myTail = myTail.myNext; 
		}
	}

	public void appendInPlace (List l) {
		// TODO Your code here 
		mySize += l.size();
		if (l.isEmpty()) {
		} else if (isEmpty()) {
			myHead = l.myHead;
			myTail = l.myTail;
		} else {
			myTail.myNext = l.myHead;
			myTail = l.myTail;
		}
	}
	
	public void remove (Object obj) {
		while (myHead.myItem.equals(obj) && myHead != null) {
			myHead = myHead.myNext;
		}
		if (myHead == null) {
			myTail = null;
			return;
		}
		ListNode p = myHead;
		myTail = p;
		while (p.myNext != null) {
			if (p.myNext.myItem.equals(obj)) {
				p.myNext = p.myNext.myNext;
			} else {
				p = p.myNext;
				myTail = p;
			}
		}
	}
	
/**
 * the value stored in mySize is the number of nodes in this list
all myItem objects in this list are non-null
either both myHead and myTail are null, or myTail is a reference to the last node
 in the list whose first node is the node that myHead refers to.
 * @return
 */
	public void isOK() throws IllegalStateException {
		ListNode p = myHead;
		if (myHead == null) {
			if (myTail != null) {
				throw new IllegalStateException("myHead is null but myTail is not");
			}
		} else {
			if (myTail == null) {
				throw new IllegalStateException("myHead is not null but myTail is");
			}
			
			int s = 0;
			for (; p != null; p = p.myNext) {
				if (p.myItem == null) {
					throw new IllegalStateException("An item of this list is null");
				}
				s++;
				if (p.myNext == null && p != myTail) {
					throw new IllegalStateException("myTail is not pointing to the last list node of the list");
				}
			}
			if (s != size()) {
				throw new IllegalStateException("Size value is inconsistent");
			}
		}
	}
	
	public void doubleInPlace() {

		for (ListNode p = myHead; p != null; p = p.myNext.myNext) {
			ListNode temp = p.myNext;
			p.myNext = new ListNode(p.myItem, temp);
		}
	}
	

	public void reverse() {		
		ListNode answer = reverseHelper(myHead);
		myHead = answer;
	}

//	private static ListNode reverseHelper(ListNode l, ListNode soFar) {
//	    if (l == null) {
//	        return soFar;
//	    } else {
//	    	ListNode temp = l.myNext;
//	    	l.myNext = soFar;
//	    	return reverseHelper ( temp, l );
//	    }
//	}
	
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

	public Iterator iterator() {
	    return new ElementIterator();
	}

	public class ElementIterator implements Iterator {

	    // State variable(s) to be provided.
		ListNode ln;
		
	    public ElementIterator() {
	        // TODO code to be provided
	    	ln = myHead;
	    }

	    public boolean hasNext() {
	        // TODO code to be provided
	    	return ln != null;
	    }

	    public Object next() {
	        // TODO code to be provided
	    	Object i = ln.myItem;
	    	ln = ln.myNext;
	    	return i;
	    }

	    public void remove() {
	        // not used; do not implement
	    }
	}

}
