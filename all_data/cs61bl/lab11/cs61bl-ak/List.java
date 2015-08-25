import java.util.Iterator;

public class List implements Iterable{

	private ListNode myHead;
	private int mySize;
	private ListNode myTail;
	
	public List() {
		myHead = null;
		mySize = 0;
		myTail = null;
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

	    // State variable(s) to be provided.

		ListNode head;
		ListNode next;
		
	    public ElementIterator() {
	        // TODO code to be provided
	    	head = myHead;
	    	next = myHead;
	    }

	    public boolean hasNext() {
	        return next != null;
	        // TODO code to be provided
	    }

	    public Object next() {
	        // TODO code to be provided
	    	Object result = next.myItem;
	    	next = next.myNext;
	        return result;
	    }

	    public void remove() {
	        // not used; do not implement
	    }
	}
	
	public boolean equals(Object obj) {
		if (obj instanceof List) {
			List obj1 = (List) obj;
			return equalsHelper(myHead, obj1.myHead);
		}
		return false;
	}
	
	public boolean equalsHelper(ListNode lst, ListNode lst1) {
		if (lst == null && lst1 == null) {
			return true;
		} else if (lst == null) {
			return false;
		} else if (lst1 == null) {
			return false;
		} else {
			if (lst.myItem == lst1.myItem) {
				return equalsHelper(lst.myNext, lst1.myNext);
			}
			return false;
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
//		int rtn = 0;
//		for (ListNode p = myHead; p != null; p = p.myNext) {
//			rtn++;
//		}
//		return rtn;
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
		mySize ++;
	}

	public void add (Object x) {
		// TODO Your code here 
		ListNode p = myHead;
		if (p == null) {
			myHead = new ListNode(x);
			mySize++;
			myTail = myHead;
			return;
		}
		while (p.myNext != null) {
			p = p.myNext;
		}
		p.myNext = new ListNode(x);
		mySize++;
		myTail = p.myNext;
	}

	public void appendInPlace (List l) {
		// TODO Your code here 
		ListNode p = myHead;
		if (p == null) {
			myHead = l.myHead;
			myTail = l.myTail;
			mySize = l.mySize;
			return;
		}
		while (p.myNext != null) {
			p = p.myNext;
		}
		p.myNext = l.myHead;
		myTail = l.myTail;
		mySize += l.mySize;
	}
	
	public boolean isOK() {
		int count = 1;
		ListNode p = myHead;
		if (p == null) {
			if (myTail == null && mySize == 0) {
				return true;
			}
			return false;
		}
		for (; p.myNext != null; p = p.myNext) {
			if(p.myItem == null) {
				return false;
			}
			count++;
		}
		if (p != myTail) {
			return false;
		}
		if (mySize != count) {
			return false;
		}
		return true;
	}
	
	public void remove(Object x) {
		if(myHead == null) {
			return;
		}
		ListNode p = myHead;
		ListNode q = myHead.myNext;
		for (;p.myNext != null;) {
			if (q.myItem == x) {
				p.myNext = q.myNext;
				q = p.myNext;
			} else {
				p = p.myNext;
				q = q.myNext;
			}
		}
		if (myHead.myItem == x){
			myHead = myHead.myNext;
		}
	}
	
	public void doubleInPlace() {

	    for (ListNode p = myHead; p != null; p = p.myNext) {
	        // TODO Your code here
	    	p.myNext = new ListNode(p.myItem, p.myNext);
	    	p = p.myNext;
	    }
	    // TODO And maybe here as well
	}
	
	public void reverse() {
	    // ...
		myTail = myHead;
//		ListNode soFar = null;
//		ListNode l = myHead;
//		while (l != null) {
//			ListNode temp = l.myNext;
//			soFar = reverseHelper(l, soFar);
//			l = temp;
//		}
//		myHead = soFar;
//		myHead = reverseHelper(myHead, null);
		myHead = reversehelper(myHead);
	}

	public static ListNode reverseHelper(ListNode l, ListNode soFar) {
	    // ...
		if (l == null) {
			return soFar;
		} else {
			ListNode temp = l.myNext;
			l.myNext = soFar;
			return reverseHelper(temp, l);
		}
		
	}
	
	public static ListNode reversehelper(ListNode head) {
		ListNode p, soFar;
		for (p = head, soFar = null; p!= null;) {
			ListNode temp = p;
			p = head.myNext;
			head.myNext = soFar;
			soFar = head;
			head = p;
		}
		return soFar;
	}
}
