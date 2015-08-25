import java.util.*;

public class List {

	private ListNode myHead;
	private ListNode myTail;
	public int mySize;

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
		myHead = new ListNode (obj, myHead);
	}

	public boolean equals (Object obj) {
		if (!(obj instanceof List)) {
			return false;
		}
		if (isEmpty() && ((List)obj).isEmpty()) {
			return true;
		}
		if (isEmpty() || ((List)obj).isEmpty()) {
			return false;
		}
		if (size() != ((List)obj).size()) {
			return false;
		}
		for (int i = 0; i < size(); i++) {
			if (!(get(i).equals(((List)obj).get(i)))) {
				return false;
			}
		}
		return true;
	}

	public void add (Object x) {
		if (isEmpty()) {
			myHead = new ListNode(x);
			myTail = myHead;
			mySize += 1;
			return;
		}
		myTail.myNext = new ListNode(x);
		mySize += 1;
		myTail = myTail.myNext;
	}
//		ListNode p = myHead;
//		for (int i = 0; i <= size(); i++) {
//			if (size() == 0) {
//				myHead = new ListNode(x);
//				return;
//			}
//			if (i == size() - 1) {
//				p.myNext = new ListNode(x);
//				return;
//			}
//			p = p.myNext;
//		}
//	}

	public void appendInPlace (List l) {
		if (isEmpty()) {
			myHead = l.myHead;
			myTail = l.myTail;
			mySize = l.size();
			return;
		}
		myTail.myNext = l.myHead;
		mySize += l.size();
		if (l.myTail == null) {
			return;
		}
		myTail = l.myTail;
	}
//		ListNode p = myHead;
//		for (int i = 0; i <= size(); i++) {
//			if (size() == 0) {
//				if (l.size() == 0) {
//					return;
//				}
//				myHead = l.myHead;
//				return;
//			}
//			if (i == size() - 1) {
//				if (l.size() == 0) {
//					return;
//				}
//				p.myNext = l.myHead;
//				return;
//			}
//			p = p.myNext;
//		}
//	}
	
	public Iterator iterator() {
	    return new ElementIterator();
	}

	public class ElementIterator implements Iterator {
        private int index;

	    public ElementIterator() {
	    	index = 0;
	    }

	    public boolean hasNext() {
	        return (index < size());
	    }

	    public Object next() {
	    	Object result = get(index);
	    	index += 1;
	        return result;
	    }

	    public void remove() {
	        // not used; do not implement
	    }
	}
	
	public boolean isOK() {
		int count = 0;
		for (ListNode p = myHead; p != null; p = p.myNext) {
			if (p.myItem == null) {
				return false;
			}
			count ++;
		}
		if (count != size()) {
			return false;
		}
		if (myHead == null || myTail == null) {
			return false;
		}
		return true;
	}
	
	public void remove(Object obj) {
		for (ListNode p = myHead; p != null; p = p.myNext) {
			if (p.myNext == null) {
				break;
			}
			if (myHead.myItem.equals(obj)) {
				myHead = myHead.myNext;
			}
		}
	}
	
	public void doubleInPlace() {
	    for (ListNode p = myHead; p != null; p = p.myNext) {
	        ListNode temp = p.myNext;
	        p.myNext = new ListNode(p.myItem, temp);
	        p = p.myNext;
	    }
	    // TODO And maybe here as well
	}
	
	public void reverse() {
//		ListNode temp = myHead.myNext;
//		myHead.myNext = null;
//	    myHead = List.reverseHelper(temp, myHead);
		myHead = List.reverseHelper(myHead);
	}

//	private static ListNode reverseHelper(ListNode l, ListNode soFar) {
//	    if (l == null) {
//	        return soFar;
//	    } else {
//	    	ListNode temp = l.myNext;
//	    	l.myNext = soFar;
//	    	return reverseHelper(temp, l);
//	    }
//	}
	private static ListNode reverseHelper(ListNode head) {
	    ListNode p, soFar;
	    // p plays the role of l in the previous version.
	    for (p = head, soFar = null; p != null;) {
	        ListNode temp = p;
	        ListNode next = temp.myNext;
	        temp.myNext = soFar;
	        soFar = temp;
	        p = next;
	    }
	    return soFar;
	}
	
}
