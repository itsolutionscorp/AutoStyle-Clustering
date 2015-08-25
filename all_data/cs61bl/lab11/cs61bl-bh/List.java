import java.util.Iterator;

public class List implements Iterable {

	private ListNode myHead, myTail;
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
		if (this.isEmpty()) {
			this.myHead = new ListNode(obj);
			this.myTail = this.myHead;
		} else {
			ListNode n = new ListNode(obj, this.myHead);
			this.myHead = n;
		}
		this.mySize++;
	}

	public boolean equals (Object obj) {
		if (obj instanceof List) {
			List l = (List) obj;
			ListNode n = l.myHead;
			ListNode curr = this.myHead;
			for (; curr != null && n != null; curr = curr.myNext, n = n.myNext) {
				if (!curr.myItem.equals(n.myItem)) {
					return false;
				}
			}
			return curr == null && n == null;
		} else return false;
	}

	public void add (Object x) {
		if (this.isEmpty()) {
			this.myHead = new ListNode(x);
			this.myTail = this.myHead;
		} else {
			ListNode temp = myHead;
			while (temp.myNext != null) {
				temp = temp.myNext;
			}
			temp.myNext = new ListNode(x);
			this.myTail = temp.myNext;
		}
		this.mySize++;
	}

	public void appendInPlace (List l) {
		this.mySize += l.mySize;
		if (this.isEmpty()) {
			this.myHead = l.myHead;
			this.myTail = l.myTail;
		} else {
			ListNode temp = myHead;
			while (temp.myNext != null) {
				temp = temp.myNext;
			}
			myTail.myNext = l.myHead;
			while (myTail.myNext != null) {
				myTail = myTail.myNext;
			}
		}
	}
	
	public Iterator iterator() {
	    return new ElementIterator();
	}

	public class ElementIterator implements Iterator {

	    ListNode myNode;

	    public ElementIterator() {
	        myNode = myHead;
	    }

	    public boolean hasNext() {
	        return myNode != null;
	    }

	    public Object next() {
	    	Object temp = myNode.myItem;
	    	myNode = myNode.myNext;
	    	return temp;
	    }

	    public void remove() {
	        // not used; do not implement
	    }
	}
	
	public boolean isOK() {
		int rtn = 0;
		for (ListNode curr = this.myHead; curr != null; curr = curr.myNext) {
			rtn++;
		}
		if (rtn != mySize) {
			return false;
		}
		for (ListNode curr = this.myHead; curr != null; curr = curr.myNext) {
			if (curr.myItem == null) {
				return false;
			}
		}
		if (this.myHead == null) {
			return this.myTail == null;
		} else {
			ListNode curr = this.myHead;
			while (curr.myNext != null) {
				curr = curr.myNext;
			}
			if (curr != this.myTail) {
				return false;
			}
		}
		return true;
	}
	
	public void remove(Object x) {
		while (this.myHead != null && this.myHead.myItem.equals(x)) {
			this.myHead = this.myHead.myNext;
		}
		ListNode curr = this.myHead;
		for (; curr != null; curr = curr.myNext) {
			if (curr.myNext.myItem.equals(x)) {
				curr.myNext = curr.myNext.myNext;
			}
		}
		this.myTail = curr;
	}
	
	public void doubleInPlace() {
	    for (ListNode p = myHead; p != null; p = p.myNext) {
	    	p.myNext = new ListNode(p.myItem, p.myNext);
	    	p = p.myNext;
	    }
	}
	
	public void reverse() {
		this.myHead = reverseHelper(this.myHead);
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