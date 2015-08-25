import java.util.*;

public class List {

	private ListNode myHead;
	private ListNode myTail;
	private int mySize;

	public List() {
		myHead = null;
		myTail = myHead;
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
		mySize++;
		if (mySize == 1) {
			myTail = myHead;
		}
	}

	public boolean equals (Object obj) {
		// TODO Your code here
		ListNode thisCopy = this.myHead;
		List objCopy = (List) obj;
		ListNode thatCopy = objCopy.myHead;
		if (thisCopy == null && thatCopy == null) {
			return true;
		}
		while (thisCopy != null) {
			if (thatCopy == null) {
				return false;
			}
			if (thisCopy.myItem != thatCopy.myItem) {
				return false;
			}
			thisCopy = thisCopy.myNext;
			thatCopy = thatCopy.myNext;
		}
		if (thatCopy != null) {
			return false;
		}
		return true;
	}

	public void add (Object x) {
		// TODO Your code here 
		if (this.isEmpty()) {
			this.myHead = new ListNode(x);
			this.myTail = myHead;
			mySize++;
		}
		else {
//			ListNode thisCopy = this.myHead;
//			while (thisCopy.myNext != null) {
//				thisCopy = thisCopy.myNext;
//			}
			myTail.myNext = new ListNode(x);
			myTail = myTail.myNext;
			mySize++;
		}
	}

	public void appendInPlace (List l) {
		// TODO Your code here 
		if (this.isEmpty()) {
			this.myHead = l.myHead;
			this.myTail = l.myTail;
			mySize = l.mySize;
		}
		else if (!l.isEmpty()) {
//			ListNode thisCopy = this.myHead;
//			while (thisCopy.myNext != null) {
//				thisCopy = thisCopy.myNext;
//			}
//			thisCopy.myNext = l.myHead;
			myTail.myNext = l.myHead;
			myTail = l.myTail;
			mySize += l.mySize;
		}
		
	}

	public Iterator iterator() {
	    return new ElementIterator();
	}

	public class ElementIterator implements Iterator {

	    // State variable(s) to be provided.
		int count;

	    public ElementIterator() {
	        // TODO code to be provided
	    	count = 0;
	    }

	    public boolean hasNext() {
	        return count < size();
	        // TODO code to be provided
	    }

	    public Object next() {
	    	Object toReturn = get(count);
	    	count++;
	        return toReturn;
	        // TODO code to be provided
	    }

	    public void remove() {
	        // not used; do not implement
	    }
	}

	public boolean isOK() {
		int mySizeStore = mySize;
		ListNode thisCopy = this.myHead;
		while (thisCopy != null) {
			thisCopy = thisCopy.myNext;
			mySizeStore--;
		}
		if (mySizeStore != 0) {
			return false;
		}
		thisCopy = this.myHead;
		while (thisCopy!= null) {
			if (thisCopy.myItem == null) {
				return false;
			}
			thisCopy = thisCopy.myNext;
		}
		if (mySize == 0 && myHead == null && myTail == null) {
			return true;
		}
		else {
			ListNode currNode = myHead;
			while (currNode.myNext != null) {
				currNode = currNode.myNext;
			}
			if (currNode != myTail) {
				return false;
			}
			return true;
		}
	}

	public void remove(Object obj) {
		if (!this.isEmpty()) {
			ListNode thisCopy = this.myHead;
			ListNode thisPrevious = thisCopy;
			while (thisCopy.myItem.equals(obj)) {
				thisCopy = thisCopy.myNext;
				myHead = thisCopy;
				mySize--;
			}
			while (thisCopy != null) {
				while (thisCopy.myNext != null && thisCopy.myNext.myItem.equals(obj)) {
					thisCopy.myNext = thisCopy.myNext.myNext;
					mySize--;
				}
				thisCopy = thisCopy.myNext;
			}
		}
		this.myTail = this.myHead;
		ListNode currNode = this.myHead;
		while (currNode.myNext != null) {
			this.myTail = currNode.myNext;
			currNode = currNode.myNext;
		}
	}
	
	public void doubleInPlace() {
		ListNode currNode = this.myHead;
		while (currNode != null) {
			ListNode nextNode = currNode.myNext;
			ListNode currCopy = new ListNode(currNode.myItem);
			currNode.myNext = currCopy;
			currCopy.myNext = nextNode;
			currNode = currNode.myNext.myNext;
			if (currNode != null) {
				this.myTail = currNode;
			}
		}
		mySize *= 2;
		if (myHead != null) {
			this.myTail = this.myHead;
			currNode = this.myHead;
			while (currNode.myNext != null) {
				this.myTail = currNode.myNext;
				currNode = currNode.myNext;
			}
		}
	}
	
	private static ListNode reverseHelper(ListNode head) {
	    ListNode p, soFar;
	    // p plays the role of l in the previous version.
	    //soFar = null;
	    for (p = head, soFar = null; p != null;) {
	        ListNode temp = p;
	        p = temp.myNext;
	        temp.myNext = soFar;
	        soFar = temp;
	    }
	    head = soFar;
	    return soFar;
	}
	
	public void reverse() {
		this.myTail = this.myHead;
	    this.myHead = reverseHelper(this.myHead);
	}
	
}
