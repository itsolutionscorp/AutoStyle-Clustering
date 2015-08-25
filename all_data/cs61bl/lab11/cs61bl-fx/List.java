import java.util.*;
public class List implements Iterable<Object>{

	private ListNode myHead;
	private ListNode myTail;
	private int mySize = 0;

	public List() {
		myHead = null;
		myTail = null;
	}
	
	public boolean isEmpty() {
		return myHead == null && myTail == null;
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

	public class ElementIterator implements Iterator<Object> {
		ListNode myNext;
		Object myItem;
	    // State variable(s) to be provided.
		int myIndex;
		ListNode Head = myHead;

	    public ElementIterator() {
	        // TODO code to be provided
	    	myIndex = 0;
	    }

	    public boolean hasNext() {
	        return myIndex < size();
	        // TODO code to be provided
	    }

	    public Object next() {
	    	Object temp = Head.myItem;
	    	Head = Head.myNext;
	    	myIndex++;
	        return temp;
	        // TODO code to be provided
	    }

	    public void remove() {
	        // not used; do not implement
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
		int rtn = 0;
		for (ListNode p = myHead; p != null; p = p.myNext) {
			rtn++;
			
		}
		return rtn;
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
	
	public boolean equals (Object obj) {
		if (((List)obj).myHead == null) {
			return this.myHead == null;
		} else if (this.myHead == null) {
			return ((List)obj).myHead == null;
		} if (((List)obj).size() != this.size()) {
			return false;
		}
		ListNode o = ((List)obj).myHead;
		for (ListNode p = myHead; p != null; p = p.myNext){
			if (!p.myItem.equals(o.myItem)) {
				return false;
			} o = o.myNext;
		}
		return true;
//		try { 
//			return ((List) obj).myHead.myItem.equals((this.myHead.myItem)) && this.myHead.myNext.equals(((List)obj).myHead.myNext);}
//		catch (NullPointerException e) {
//			if (this.myHead==null && ((List)obj).myHead==null) {
//				return true;
//			}return false;
	}
	
	public void add (Object x) {
		// TODO Your code here 
		if (this.myHead == null) {
			this.myHead = new ListNode (x, null);
			myTail = this.myHead;
			mySize++;
			return;
		}
		for (ListNode p = myHead; p != null; p = p.myNext) {
			if (p.myNext == null) {
				p.myNext = new ListNode (x, null);
				myTail = p.myNext;
				mySize = size();
				return;
			}	
		}
	}
	
	public void doubleInPlace() {
	    for (ListNode p = myHead; p != null;) {
	        // TODO Your code here
	    	p.myNext = new ListNode(p.myItem, p.myNext);
	    	p = p.myNext.myNext;
	    }
	    // TODO And maybe here as well
	}
	
	public void remove (Object x) {
		if (this.myHead == null) {
			return;
		} 
		while (this.myHead.myItem.equals(x)) {
			if (this.myHead.myNext == null) {
				this.myHead = null;
				this.myTail = null;
				return;
			} else {
				this.myHead = this.myHead.myNext;
			}
		} for (ListNode p = myHead; p.myNext != null;) {
			if (p.myNext.myItem.equals(x)) {
				p.myNext = p.myNext.myNext;
			} else {
				p = p.myNext;
			}
		}
	}
	
	
		

	public void appendInPlace (List l) {
		if (this.myHead == null && l.myHead == null) {
			mySize = 0;
			return;
		}
		else if (this.myHead == null && l.myHead != null) {
			this.myHead = l.myHead; 
			this.mySize = l.size();
			this.myTail = l.myTail;
			return;
		} 
		else if (l.myHead == null) {
			return;
			}
		for (ListNode p = myHead; p != null; p = p.myNext, mySize++) {
			if (p.myNext == null) {
				mySize += l.size();
				p.myNext = l.myHead;
				myTail = l.myTail;
				return;
			}
		}
	}
	public void isOK() {
		boolean ok;
		if (mySize != size()) {
			if (mySize < size()) {
				System.out.println("mySize = " + mySize);
				System.out.println("size() = " + size());
				throw new IllegalStateException("mySize is less than size");
			}
			else {
				System.out.println("mySize = " + mySize);
				System.out.println("size() = " + size());
				throw new IllegalStateException("mySize is greater than size");
			}
		}
		for (ListNode p = myHead; p != null; p = p.myNext) {
			if (p.myItem == null) {
			throw new IllegalStateException("myItem is null");
			}
		} 
		if (myHead == null && myTail == null) {
			ok = true;
		}
		for (ListNode p = myHead; p != null; p = p.myNext) {
			if (p.myNext == null && myTail != p) {
			throw new IllegalStateException("myTail isn't my tail");
			}
		}
	}
	public void reverse() {
		this.myHead = reverseHelper(this.myHead, null);
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
}
	
