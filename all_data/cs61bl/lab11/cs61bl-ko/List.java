import java.util.*;

public class List implements Iterable {

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
		ListNode nodeToReturn;

	    public ElementIterator() {
	        // TODO code to be provided
	    	nodeToReturn = myHead;
	    }

	    public boolean hasNext() {
	        // TODO code to be provided
	    	if (nodeToReturn == null) {
	    		return false;
	    	} return true;
	    }

	    public Object next() {
	        // TODO code to be provided
	    	Object answer = nodeToReturn.myItem;
	    	nodeToReturn = nodeToReturn.myNext;
	    	return answer;
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
		mySize++;
		myHead = new ListNode (obj, myHead);
		if (myTail == null) {
			myTail = myHead;
		}
	}

	public boolean equals (Object obj) {
		List troll = (List) obj;
		ListNode temp = troll.myHead; 
		if (size() != troll.size()) {
			return false;
		}
		if (myHead == null) {
			return temp == null;
		}
		if (temp == null) {
			return myHead == null;
		}
		for (ListNode p = myHead; p != null; p = p.myNext) {
			if (!p.myItem.equals(temp.myItem)) {
				return false;
			}
			temp = temp.myNext;
		}
		return true;
	}

	public void add (Object x) {
		mySize++;
		if (myHead == null) {
			myHead = new ListNode(x);
			myTail = myHead;
			return;
		}
		for (ListNode p = myHead; p != null; p = p.myNext) {
			if (p.myNext == null) {
				p.myNext = new ListNode(x, null);
				myTail = p.myNext;
				return;
			}
		}
	}

	public void appendInPlace (List l) {
		if (l.myHead == null) {
			return;
		}
		mySize += l.mySize;
		myTail = l.myTail;
		if (myHead == null) {
			myHead = l.myHead;
			return;
		}
				for (ListNode p = myHead; p != null; p = p.myNext) {
			if (p.myNext == null) {
				p.myNext = l.myHead;
				return;
			}
		}
	}
	
	public boolean isOK() {
		int temp = 0;		
		for (ListNode p = myHead; p != null; p = p.myNext) {
			temp++;
			if (p.myNext == null && p != myTail) {
				return false;
			}
			if (p.myItem == null) {
				return false;
			}
		}
		if (mySize != temp) {
			return false;
		}
		if (myHead == null) {
			return (myTail == null);
		}
		if (myTail == null) {
			return (myHead == null);
		}
		return true;
	}
	
	public void remove(Object obj) {
		for (ListNode p = myHead; p != null;) {
			if (p == myHead && p.myItem.equals(obj)) {
				p = p.myNext;
				myHead = p;
				mySize--;
				if (myHead == null) {
					myTail = null;
				}
			}
			else if (p.myNext == null) {
				if (p.myItem.equals(obj)) {
					myHead = null;
					myTail = null;
					mySize--;
					return;
				}
				p = p.myNext;
			}
			else if (p.myNext.myItem.equals(obj)) {
				mySize--;
				p.myNext = p.myNext.myNext;
				if (p.myNext == null) {
					myTail = p;
					return;
				}
			}
			else {
				p = p.myNext;
			}
		}
	}
	
	public void doubleInPlace() {
		if (myHead == null) {
			return;
		}
	    for (ListNode p = myHead; p != null; p = p.myNext.myNext) {
	        ListNode dub = new ListNode(p.myItem, p.myNext);
	        p.myNext = dub;
	    }
	    mySize *= 2;
	    myTail = myTail.myNext;
	    // TODO And maybe here as well
	}
	
	public void reverse() {
		myTail = myHead;
	    myHead = reverseHelper(myHead);
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
