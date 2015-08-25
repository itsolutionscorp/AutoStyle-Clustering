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
//		return myHead == null;
		return mySize == 0;
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
	}

	public boolean equals (Object obj) {
		// TODO Your code here 
		if ((obj instanceof List) && (this.size() == ((List) obj).size())) {
			ListNode curList = this.myHead;
			ListNode curNode = ((List) obj).myHead;
			while (curList != null) {
				if (curList.myItem != curNode.myItem) {
					return false;
				}
				curList = curList.myNext;
				curNode = curNode.myNext;
			}
			return true;
		} else {
			return false;
		}
	}

	public void add (Object x) {
		// TODO Your code here 
		if (myTail == null && myHead == null) {
			myHead = new ListNode(x);
			myTail = myHead;
		} else {
			myTail.myNext = new ListNode(x);
			myTail = myTail.myNext;
		}
		mySize++;
//		ListNode cur = this.myHead;
//		
//		if (isEmpty()) {
//			this.myHead = new ListNode(x);
//			return;
//		}
//		
//		while(cur.myNext != null) {
//			cur = cur.myNext;
//		}
//		cur.myNext = new ListNode(x);
	}

	public void appendInPlace (List l) {
		// TODO Your code here 
//		ListNode cur = this.myHead;
//		
//		if (isEmpty()) {
//			this.myHead = l.myHead;
//			return;
//		}
//		
//		while(cur.myNext != null) {
//			cur = cur.myNext;
//		}
//		cur.myNext = l.myHead;
		if (l.size() == 0) {
			return;
		}
		if (this.size() == 0) {
			myHead = l.myHead;
			myTail = l.myTail;
		} else {
			myTail.myNext = l.myHead;
			myTail = l.myTail;
			mySize += l.size();
		}
	}
	
	public Iterator iterator() {
	    return new ElementIterator();
	}

	public class ElementIterator implements Iterator {

	    // State variable(s) to be provided.
		public int i;

	    public ElementIterator() {
	        // TODO code to be provided
	    	i = -1;
	    }

	    public boolean hasNext() {
	        // TODO code to be provided
	    	return i+1 != size();
	    }

	    public Object next() {
	        // TODO code to be provided
	    	if (hasNext()) {
	    		ListNode cur = myHead;
	    		int pos = i+1;
	    		while (pos > 0) {
	    			cur = cur.myNext;
	    			pos--;
	    		}
	    		i++;
	    		return cur.myItem;
	    	} else {
	    		return null;
	    	}
	    }

	    public void remove() {
	        // not used; do not implement
	    }
	}
	
	public boolean isOK() {
		int size = 0;
		ListNode prev = null;
		ListNode p = null;
		
		if (myHead == null || myTail == null) {
			throw new IllegalArgumentException(); 
		}
		for (p = myHead, prev = null; p != null; prev = p, p = p.myNext) {
			if (p.myItem == null) {
				throw new IllegalArgumentException(); 
			}
			size++;
		}
		
		
	
		if (size != size()) {
			throw new IllegalArgumentException();
		}
		
		if (prev != myTail) {
			throw new IllegalArgumentException();
		}
		return true;
	}
	
	public void remove(Object obj) {
		if (myHead == null || myTail == null) {
			return;
		}
		
		if (myHead == myTail) {
			if (myHead.myItem.equals(obj)) {
				myHead = null;
				myTail = null;
			}
		}
	
		for (ListNode prev = null, p = myHead; p != null; prev = p, p = p.myNext) {
			if (p.myItem.equals(obj)) {
				if (p == myHead) {
					myHead = p.myNext;
				} else if (p == myTail) {
					myTail = prev;
					myTail.myNext = null;
				} else {
					prev.myNext = p.myNext;
					p = prev;
				}
			}
		}		
	}
	
	public void doubleInPlace() {
		if (myHead == null || myTail == null) {
			return;
		}

	    for (ListNode p = myHead; p != null; p = p.myNext) {
	        // TODO Your code here
	    	p.myNext = new ListNode(p.myItem, p.myNext);
	    	p = p.myNext;
	    	mySize++;
	    }
	    // TODO And maybe here as well
	    myTail = myTail.myNext;
	}
	
	public void reverse() {
		if (myHead != null) {
			myTail = myHead;
//			myHead = reverseHelper(myHead, null);
			myHead = reverseHelper(myHead);
		}
	}

//	public static ListNode reverseHelper(ListNode l, ListNode soFar) {
//		if (l == null) {
//			return soFar;
//		} else {
//			ListNode temp = l.myNext;
//			l.myNext = soFar;
//			return reverseHelper(temp, l);
//		}
//	}
	
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