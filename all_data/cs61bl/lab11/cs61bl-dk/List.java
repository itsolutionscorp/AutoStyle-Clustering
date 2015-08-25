import java.util.*;
public class List {

	private ListNode myHead;
	private ListNode myTail;
	private int mySize;

	public List() {
		myHead = null;
		myTail = null;
		mySize = 0;
	}

	public boolean isEmpty() {
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
		int i = 0;
		if (((List) obj).size() != size()) {
			return false;
		}
		for (ListNode node = myHead; node != null; node = node.myNext) {
			if (!node.myItem.equals(((List) obj).get(i))) return false;
			i++;
		}
		return true;
	}

	public void add (Object x) {
		if (myHead == null) {
			myHead = new ListNode(x, null);
			myTail = myHead;
		}
		else {
			myTail.myNext = new ListNode(x, null);
			myTail = myTail.myNext;
		}
		mySize++;
	}

	public void appendInPlace (List l) {
		if (myHead == null) myHead = l.myHead;
		else myTail.myNext = l.myHead;
		if (l.myTail != null) myTail = l.myTail;
		mySize += l.size();
	}
	


	public void doubleInPlace() {
		for (ListNode p = myHead; p != null; p = p.myNext) {
        	p.myNext = new ListNode(p.myItem, p.myNext);
        	p = p.myNext;
		}
		if (myTail != null) myTail = myTail.myNext;
	}
	
	public boolean isOk() {
		int size = 0;
		if (myHead == null ^ myTail == null) return false;
		ListNode p = myHead;
		while (p.myNext != null) {
			size++;
			p = p.myNext;
		}
		if (p != myTail) return false;
		if (size + 1 != mySize) return false;
		return true;
	}
	
	public void remove(Object obj) {
		if (myHead.myItem.equals(obj)) myHead = myHead.myNext;
		if (myHead == null) {
			myTail = null;
			return;
		}
		for (ListNode node = myHead; node.myNext != null; node = node.myNext) {
			if (node.myNext.myItem.equals(obj)) {
				if (node.myNext.myNext == null) {
					node.myNext = null;
					myTail = node;
					break;
				}
				node.myNext = node.myNext.myNext;
				remove(obj);
			}
		}
	}
	
	public void reverse() {
		if (myHead == null) return;
		myHead = reverseHelper(myHead);
		ListNode p = myHead;
		
		while (p.myNext != null) {
			p = p.myNext;
		}
		myTail = p;
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
	
	private static ListNode reverseHelper(ListNode l, ListNode soFar) {
		if (l == null) return soFar;
		ListNode temp = l.myNext;
		l.myNext = soFar;
		soFar = l;
		l = temp;
		return reverseHelper(l, soFar);
	}
	
	public Iterator iterator() {
	    return new ElementIterator();
	}

	public class ElementIterator implements Iterator {
		private ListNode node;
	    public ElementIterator() {
	        node = myHead;
	    }

	    public boolean hasNext() {
	    	if (node == null) return false;
	        return node.myNext != null;
	    }

	    public Object next() {
	        node = node.myNext;
	        return node.myItem;
	    }

	    public void remove() {
	        // not used; do not implement
	    }
	}
}
