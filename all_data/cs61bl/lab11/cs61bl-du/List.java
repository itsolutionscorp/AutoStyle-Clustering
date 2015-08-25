import java.util.Iterator;


public class List implements Iterable{
	
	public Iterator iterator() {
	    return new ElementIterator();
	}

	public class ElementIterator implements Iterator {

	    // State variable(s) to be provided.
		ListNode currentNode;

	    public ElementIterator() {
	        // TODO code to be provided
	    	currentNode = myHead;
	    }

	    public boolean hasNext() {
	        return (currentNode != null);
	        // TODO code to be provided
	    }

	    public Object next() {
	    	ListNode temp = currentNode;
	    	currentNode = currentNode.myNext;
	        return temp.myItem;
	        // TODO code to be provided
	    }

	    public void remove() {
	        // not used; do not implement
	    }
	}
	
	
	

	private ListNode myHead;
	private int mySize;
	private ListNode myTail = myTail();

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
		mySize = 0;
		for (ListNode p = myHead; p != null; p = p.myNext) {
			mySize++;
		}
		return mySize;
	}
	
	public int sizeTester() {
		int sz = 0;
		for (ListNode p = myHead; p != null; p = p.myNext) {
			sz++;
		}
		return sz;
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
		size();
	}

	public boolean equals (Object obj) {
		ListNode p;
		ListNode p2 = ((List) obj).myHead;
		for (p = myHead; p != null; p = p.myNext) {
			if ((p == null) || (p2 == null)) {
				return (p == p2);
			}
			if (!p.myItem.equals(p2.myItem)) {
				return false;
			}
			p2 = p2.myNext;
		}
		if ((p == null) && (p2 != null)) {
			return false;
		}
		return true;
	}

	public ListNode myTail() {
		for (ListNode p = myHead; p != null; p = p.myNext) {
			if (p.myNext == null) {
				myTail = p;
				return myTail;
			}
		}
		myTail = myHead;
		return myTail;
	}
	
	public ListNode myTailTester() {
		for (ListNode p = myHead; p != null; p = p.myNext) {
			if (p.myNext == null) {
				return p;
			}
		}
		return myHead;
	}
	
	public void add (Object x) {
		if (myTail() == null) {
			myHead = new ListNode (x, null);
			size();
		} else {
			myTail().myNext = new ListNode(x, null);
			size();
		}
	}

	public void appendInPlace (List l) {
		if (myTail() == null) {
			myHead = l.myHead;
			size();
		} else {
			myTail().myNext = l.myHead;
			size();
		}
	}
/*
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
	    	return reverseHelper ( temp, l );
	    }
	}
*/
	public void reverse() {
	    if (myHead != null) {
	    	myHead = reverseHelper(myHead);
	    }
	}
	
	private static ListNode reverseHelper(ListNode head) {
	    ListNode p, soFar;
	    // p plays the role of l in the previous version.
	    for (p = head, soFar = null; p != null;) {
	    	ListNode temp = soFar;
	    	soFar = new ListNode(p.myItem, temp);
	    	p = p.myNext;
	    }
	    return soFar;
	}

	
	public void doubleInPlace() {
	    for (ListNode p = myHead; p != null; p = p.myNext) {
	        p.myNext = new ListNode(p.myItem, p.myNext);
	    	p = p.myNext;
	    }
	    // TODO And maybe here as well
	}
	
	public void remove(Object obj){
		if (myHead == null) {
		} else if (myHead.myItem == obj) {
			myHead = myHead.myNext;
			remove(obj);
		} else {
		removeHelper (obj, myHead);
		}
	}
	
	public static void removeHelper (Object obj, ListNode ln) {
		if (ln.myNext == null) {
		} else if (ln.myNext.myItem == obj) {
			ln.myNext = (ln.myNext.myNext);
			removeHelper (obj, ln);
		} else {
			removeHelper (obj, ln.myNext);
		}
	}
	
	public boolean isOK(){
		Iterator itr = new ElementIterator();
		boolean myItemsAreNull = true;
		while(itr.hasNext()) {
			if (itr.next() != null) {
				myItemsAreNull = false;
			}
		}
		if(mySize != sizeTester()) {
			return false;
		} else if (myItemsAreNull) {
			return false;
		} else if (myHead == null) {
			if (myTail == null) {
				return true;
			} else {
				return false;
			}
		} else if (myTailTester() != myTail) {
			return false;
		} else {
			return true;
		}
	}
		

}
