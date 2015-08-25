import java.util.*;

public class List {

	private int mySize;
	private ListNode myTail;
	private ListNode myHead;

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
		if (obj == null) {
			return false;
		} try {
				List other = (List) obj;
				if ((this.size() == other.size()) == false) {
					return false;
				}
				for (ListNode p = myHead, q = other.myHead; p != null; p = p.myNext, q = q.myNext) {
					if ((p.myItem.equals(q.myItem)) == false) {
						return false;
					}
				} return true;
		} catch (ClassCastException e) {
			return false;
		} 
	}

	public void add (Object x) throws IllegalArgumentException {
		if (x == null) {
			throw new IllegalArgumentException ("Cannot add null");
		}
		if (myHead == null) {
			myHead = new ListNode(x);
			myTail = myHead;
			mySize++;
		} else {
			myTail.myNext = new ListNode(x);
			myTail = myTail.myNext;
			mySize++;
		}
	}

	public void appendInPlace (List l) throws IllegalArgumentException{
		if (l == null) {
			throw new IllegalArgumentException ("Cannot add null");
		}
		ListNode p = myHead;
		if (p == null) {
			myHead = l.myHead;
			myTail = myHead;
			mySize += l.mySize;
		} else {
			myTail.myNext = l.myHead;
			myTail = myTail.myNext;
			mySize += l.mySize;
		}
	}
	
	public Iterator iterator() {
	    return new ElementIterator();
	}

	public class ElementIterator implements Iterator {
		ListNode index;
	    

	    public ElementIterator() {
	        index = myHead;
	    }

	    public boolean hasNext() {
	       return (index != null);
	    }

	    public Object next() {
	        ListNode result = index;
	        index = index.myNext;
	        return result.myItem;
	    }

	    public void remove() {
	        // not used; do not implement
	    }
	}
	private void isOK() throws IllegalStateException {
		if((myHead == null && myTail != null) || (myHead != null && myTail == null)){
			throw new IllegalStateException("Error: List cannot exist, both myHead and myTail must exist");
		}
		int counter = mySize;
		for (ListNode p = myHead; p != null; p = p.myNext){
			if ((p == myTail && p.myNext != null) || (p != myTail && p.myNext == null)) {
				throw new IllegalStateException("Error: myTail should point to last item");
			} else if (p.myItem == null){
				throw new IllegalStateException("Error: All myItem objects should be non-null");
			}
			counter --;
		}
		if (!(counter == 0)){
			throw new IllegalStateException("Error: mySize should be equal to the number of nodes in this list");
		}
	}
	
	public void remove(Object o) {
		if (myHead == null) {
			return;
		} 
		while (myHead.myItem.equals(o)) {
			myHead = myHead.myNext;	
		}
		for (ListNode p = myHead; p != null; p = p.myNext) {
			if (p.myNext.myItem.equals(o)) {
				p.myNext = p.myNext.myNext;
			}
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
