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
		if (isEmpty()) {
			myHead = new ListNode(obj, null);
			myTail = myHead;
		} else {
			myHead = new ListNode (obj, myHead);
		}
		mySize++;
	}

	public boolean equals (Object obj) {
		if (obj instanceof List) {
			ListNode current2 = ((List) obj).myHead;
			for (ListNode current = myHead; current != null; current = current.myNext) {
				if (current2 == null) {
					return false;
				}
				if (current2.myItem != current.myItem) {
					return false;
				}
				current2 = current2.myNext;
			}
			return current2 == null;
		} else {
			return false;
		}
	}

	public void add (Object x) {
		if (isEmpty()) {
			myHead = new ListNode(x, null);
			myTail = myHead;
		} else {
			myTail.myNext = new ListNode(x, null); 
			myTail = myTail.myNext;
		}
		mySize++;
	}

	public void appendInPlace (List l) {
		if (isEmpty()) {
			myHead = l.myHead;
			myTail = l.myTail;
			mySize = l.size();
			return;
		}
		if (l.isEmpty()) {
			return;
		}
		myTail.myNext = l.myHead;
		myTail = l.myTail;
		mySize += l.size();
	}
	public Iterator iterator() {
	    return new ElementIterator();
	}

	public class ElementIterator implements Iterator {

	    private int index;

	    public ElementIterator() {
	        index = 0;
	    }

	    public boolean hasNext() {
	        return index < size();
	    }

	    public Object next() {
	    	Object temp = get(index);
	    	index++;
	    	return temp;
	    }

	    public void remove() {
	        // not used; do not implement
	    }
	}
	
	public boolean isOK() {
		ListNode firstNode = myHead;
		if (firstNode == null) {
			if (myTail != null) {
				return false;
			}
			if (mySize != 0) {
				return false;
			}
		} else {
			int count = 1;
			while (firstNode.myNext != null) {
				if (firstNode.myItem == null) {
					return false;
				}
				firstNode = firstNode.myNext;
				count++;
			}
			if (firstNode != myTail) {
				return false;
			}
			if (mySize != count) {
				return false;
			}
		}
		return true;
	}
	
	public void remove(Object o) {
		if (myHead == null) {
			return;
		}
		
		while (myHead != null && myHead.myItem.equals(o)) {
			myHead = myHead.myNext;
			mySize--;
			if (myHead == null) {
				myTail = myHead;
				return;
			}
		}
	
		ListNode currentNode = myHead;
		if (currentNode.myNext == null) {
			return;
		}
		while (currentNode.myNext != myTail) {
			while (currentNode.myNext.myItem.equals(o)) {
				currentNode.myNext = currentNode.myNext.myNext;
				mySize--;
				if (currentNode.myNext == myTail) {
					break;
				}
			}
			if (currentNode.myNext == myTail) {
				break;
			}
			currentNode = currentNode.myNext;
		}
		if (myTail.myItem.equals(o)) {
			currentNode.myNext = null;
			myTail = currentNode;
			mySize--;
		}
	}
	
	public void doubleInPlace() {

	    for (ListNode p = myHead; p != null; p = p.myNext) {
	        p.myNext = new ListNode(p.myItem, p.myNext);
	        mySize++;
	        p = p.myNext;
	        if (p.myNext == null) {
	        	myTail = p;
	        }
	    }
	}

	public void reverse() {
//		if (!isEmpty() && myHead.myNext != null) {
//			// Reverse the whole thing
//			myHead = reverseHelper(myHead, null);
//			
//			// Handle myTail
//			ListNode current = myHead;
//			while(current.myNext != null){
//				current = current.myNext;
//			}
//			myTail = current;	
//		}
		
		// Iterative Version
		if(!isEmpty() && myHead.myNext != null){
	 		// Reverse the whole thing
	 		myHead = reverseHelper(myHead);
	 		// Handle myTail
	 		ListNode current = myHead;
	 		while(current.myNext != null){
	 			current = current.myNext;
	 		}
	 		myTail = current;	
	 	}
	}

	private static ListNode reverseHelper(ListNode l, ListNode soFar) {
	    if (l == null) {
	        return soFar;
	    } else {
	    	ListNode temp = l.myNext;
	    	l.myNext = soFar;
	    	return reverseHelper(temp, l);
	    }
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
