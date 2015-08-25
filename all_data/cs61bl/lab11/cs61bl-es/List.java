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
		return this.toString().equals(obj.toString());
	}

	public void add (Object x) {
		ListNode newNode = new ListNode(x);
		if (this.isEmpty()) {
			myHead = newNode;
		}
		else {
			ListNode l = myHead;
			while (l.myNext != null) {
				l = l.myNext;
			}
			l.myNext = newNode;
		}
		myTail = newNode;
		mySize++;
	}

	public void appendInPlace (List l) {
		if (this.isEmpty()) {
			myHead =  l.myHead;
		}
		else {
			myTail.myNext = l.myHead;
		}
		myTail = l.myTail;
		mySize += l.size();
	}
	
	public void isOK() throws Exception {
		int numOfNodes = 0;
		Iterator iter = this.iterator();
		while (iter.hasNext()) {
			if (iter.next() == null) {
				throw new Exception("List must contain non-null objects");
			}
			numOfNodes++;
		}
		if (numOfNodes != mySize) {
			throw new Exception("Size does not match number of nodes");
		}
		if (myHead != myTail) {
			ListNode end = myHead;
			for (ListNode p = myHead; p != null; p = p.myNext) {
				end = p;
			}
			if (myTail != end) {
				throw new Exception("myHead and myTail are not in the same list");
			}
		}
	}
	
	public void remove(Object x) {
		while (myHead != null && myHead.myItem.equals(x)) {
			mySize--;
			myHead = myHead.myNext;
		}
		if (myHead != null) {
			ListNode p = myHead;
			while (p.myNext != null) {
				if (p.myNext.myItem.equals(x)) {
					mySize--;
					p.myNext = p.myNext.myNext;
				}
				else {
					p = p.myNext;
				}
			}
			myTail = myHead;
			while (myTail.myNext != null) {
				myTail = myTail.myNext;
			}
		}
		else {
			myTail = myHead;
		}
	}
	
	public void doubleInPlace() {
	    for (ListNode p = myHead; p != null; p = p.myNext) {
	        p.myNext = new ListNode(p.myItem, p.myNext);
	        p = p.myNext;
	    }
	}
	
	public void reverse() {
		myTail = myHead;
	    myHead = reverseHelper(myHead);
	}
	
	// recursive reverse
	/*private static ListNode reverseHelper(ListNode l, ListNode soFar) {
	    if (l == null) {
	        return soFar;
	    } else {
	    	ListNode temp = l.myNext;
	    	l.myNext = soFar;
	    	return reverseHelper (temp, l);
	    }
	}*/
	
	// iterative reverse
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
		ListNode currentNode;

	    public ElementIterator() {
	        currentNode = myHead;
	    }

	    public boolean hasNext() {
	        return currentNode != null;
	    }

	    public Object next() {
	        Object rtn = currentNode.myItem;
	        currentNode = currentNode.myNext;
	        return rtn;
	    }

	    public void remove() {
	        // not used; do not implement
	    }
	}
}
