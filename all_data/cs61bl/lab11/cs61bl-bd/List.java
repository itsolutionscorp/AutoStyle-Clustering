import java.util.*;

public class List implements Iterable {

	private ListNode myHead;
	private int mySize;
	private ListNode myTail;

	public List() {
		myHead = null;
		myTail = myHead;
	}

	public boolean isEmpty() {
		return (myHead == null && myTail == null);
	}
	
	public Iterator iterator() {
	    return new ElementIterator();
	}
	
	public boolean isOK() {
		int rtn = 0;
		for (ListNode p = myHead; p != null; p = p.myNext) {
			rtn++;
		}
		for (ListNode p = myHead; p != null; p = p.myNext) {
			if (p.myItem == null) {
				return false;
			}
		}
		if (rtn == mySize && (myHead == null && myTail == null)) {
			for (ListNode p = myHead; p != null; p = p.myNext) {
				if (p.myNext == null) {
					ListNode endNode = p;
					if (endNode == myTail) {
						return true;
					}
				}
			}
		}
		return false;
	}
	
	public void remove(Object o) {
		removeHelper(o, this.myHead);
	}
	
	private ListNode removeHelper(Object o, ListNode n) {
		if (n == null) {
			return n;
		}
		if (myHead.myItem.equals(o)) {
			myHead = myHead.myNext;
			mySize = mySize - 1;
			return removeHelper(o, myHead);
		}
		else if (n.myItem.equals(o)) {
			n = n.myNext;
			mySize = mySize - 1;
			return removeHelper(o, n);
		}
		n.myNext = removeHelper(o, n.myNext);
		return n;
	}

	public void doubleInPlace() {

	    for (ListNode p = myHead; p != null; p = p.myNext.myNext) {
	        p.myNext = new ListNode (p.myItem, p.myNext);
	    }
	}
	
	public void reverse() {
//	    reverseHelper(this.myHead, this.myTail.myNext);
//	    ListNode tempHead = myHead;
//	    myHead = myTail;
//	    myTail = tempHead;
		
		reverseHelper(this.myHead);
		ListNode tempHead = myHead;
		myHead = myTail;
		myTail = tempHead;
	}

	public static ListNode reverseHelper(ListNode l, ListNode soFar) {
		if (l == null) {
    		return soFar;
    	}
		else {
			ListNode temp = l.myNext;
			l.myNext = soFar;
			return reverseHelper(temp, l);
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
	
	public class ElementIterator implements Iterator {

	    // State variable(s) to be provided.
		ListNode currentNode;

	    public ElementIterator() {
	        // TODO code to be provided
	    	currentNode = myHead;
	    }

	    public boolean hasNext() {
	    	// TODO code to be provided
	    	if (currentNode == null) {
	    		return false;
	    	}
	        if (currentNode.myNext == null) {
	        	return false;
	        }
	        return true;
	    }

	    public Object next() {
	        // TODO code to be provided
	    	ListNode temp = currentNode;
    		currentNode = currentNode.myNext;
    		return temp.myItem;
	    }

	    public void remove() {
	        // not used; do not implement
	    }
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
//		int rtn = 0;
//		for (ListNode p = myHead; p != null; p = p.myNext) {
//			rtn++;
//		}
//		return rtn;
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
		mySize = mySize + 1;
	}

	public boolean equals (Object obj) {
		// TODO Your code here 
		if (this.isEmpty() && ((List) obj).isEmpty()) {
			return true;
		}
		if (this.size() != ((List) obj).size()) {
			return false;
		}
		for (int i = 0; i < this.size(); i++) {
			if (!this.get(i).equals(((List) obj).get(i))) {
				return false;
			}
		}
//		for (ListNode p = myHead; p != null; p = p.myNext) {
//			if (p.myItem != obj) {
//				return false;
//			}
//		}
		return true;
	}

	public void add (Object x) {
		// TODO Your code here 
		ListNode toAdd = new ListNode(x);
		if (this.isEmpty()) {
			this.myHead = toAdd;
			this.myTail = toAdd;
			mySize = mySize + 1;
		}
		else {
			if (this.myTail != null) {
				this.myTail.myNext = toAdd;
				this.myTail = toAdd;
				mySize = mySize + 1;
			}
		}
	}

	public void appendInPlace (List l) {
		// TODO Your code here 
		
		if (this.isEmpty()) {
			this.myHead = l.myHead;
			this.myTail = l.myTail;
			mySize = l.size();
		}
		else if (l.isEmpty()) {
			// do nothing
		}
		else {
//			for (ListNode p = myHead; p != null; p = p.myNext) {
//				if (p.myNext == null) {
//					p.myNext = l.myHead;
//					size = size + l.size();
//					break;
//				}
//			}
			this.myTail.myNext = l.myHead;
			this.myTail = l.myTail;
			mySize = mySize + l.size();
		}
		
	}

}
