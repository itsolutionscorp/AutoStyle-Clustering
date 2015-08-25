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
		/** 
		int rtn = 0;
		for (ListNode p = myHead; p != null; p = p.myNext) {
			rtn++;
		}
		return rtn;
		**/
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
		if (mySize == 0) { // meaning it is an empty List 
			myHead = new ListNode(obj);
			myTail = myHead;
			mySize += 1;
		} else {
			ListNode alpha = new ListNode(obj, myHead);
			myHead = alpha;
			mySize += 1;
		}
	}

	public boolean equals (Object obj) {
		if (!(obj instanceof List)) throw new IllegalArgumentException("Please give an list for the argument");
		if (this.size() != ((List) obj).size()) return false; // different list sizes
		if ((List)obj == null) return true; // this means both are null
		ListNode q = ((List)obj).myHead;
		for (ListNode p = myHead; p != null; p = p.myNext) {
			if (p.myItem!=q.myItem) return false;
			q = q.myNext;
		}
		return true;
	}

	public void add (Object x) {
		if (myHead == null) {
			myHead = new ListNode(x);
			myTail = myHead; // since this the first node, myHead (first node) & myTail (last node) are the same
			mySize += 1;
		}
		else {
			for (ListNode p = myHead; p != null; p = p.myNext) {
				if (p.myNext == null) {
					p.myNext = new ListNode(x);
					myTail = p.myNext; // myTail will always point to the last node
					mySize += 1;
					return;
			 	} 
			}

		}
	}

	public void appendInPlace (List l) {
		if (myHead == null) {
			myHead = l.myHead;
			myTail = l.myTail;
			mySize = l.size();
			return;
		}
		for (ListNode p = myHead; p != null; p = p.myNext) {
			if (p.myNext == null) { // last node
				p.myNext = l.myHead;
				myTail = l.myTail;
				mySize += l.size();
				return;
			}
		}
	}
	
	public void remove(Object a) {
		int count = 0;
		if (mySize == 0) return; // empty LinkedList has nothing to remove
		ListNode prevP = null;
		for (ListNode p = myHead; p != null; prevP = p , p = p.myNext) {
			if (p.myItem == a) {
				if (prevP == null) myHead = p.myNext;
				else prevP.myNext = p.myNext;
				count++;
			}
		}
		// we need to reset myTail
		for (ListNode p = myHead; p != null; p = p.myNext) {
			if (p.myNext == null) { // last node
				myTail = p;
			}
		}
		mySize -= count;
	}
	
	public void doubleInPlace() {
		if (mySize == 0) return;
	    for (ListNode p = myHead; p != null; p = p.myNext.myNext) {
	        ListNode temp = new ListNode(p.myItem, p.myNext);
	        p.myNext = temp;
	    }
	    myTail = myTail.myNext;
	    mySize = mySize + mySize;
	}

	
	public void reverse() {
		myTail = myHead;
		myHead = reverseHelper(myHead,null);
	}

	private static ListNode reverseHelper(ListNode l, ListNode soFar) {
	    if (l == null) {
	        return soFar;
	    } else {
	    	ListNode temp = l.myNext;
	    	l.myNext = soFar;
	    	return reverseHelper(temp,l);
	    }
	}
	
	public void isOK() throws IllegalStateException{
		//checking for errors in mySize
		if (mySize < 0) throw new IllegalStateException("Size cannot be below 0");
		int rtn = 0;
		for (ListNode p = myHead; p != null; p = p.myNext) {
			rtn++;
		}
		if (rtn != mySize) throw new IllegalStateException("The size is not correct.");
		//checking for myItems that are null
		for (ListNode p = myHead; p != null; p = p.myNext) {
			if (p.myItem == null)  {
				throw new IllegalStateException("The item is null.");
			}
		}
		//checking for myHead & myTail wrongly referenced
		if (myHead == myTail) {
			if (myHead == null) { // meaning no head and tail so much be empty
				if (mySize != 0) throw new IllegalStateException("There is somehting wrong with this linked list");
				return;
			} else { // meaning there is 1 head and 1 tail and they are the same object so one node only
				if (mySize != 1) throw new IllegalStateException("There is something wrong with this linked list");
			}
		} else if (myHead == null || myTail == null) {
			throw new IllegalStateException("myHead cannot be null");
		} else {
			if (myTail.myNext != null) throw new IllegalStateException("Either myHead or myTail is wrong!");
		}
	}

public Iterator iterator() {
    return new ElementIterator();
}

public class ElementIterator implements Iterator {

    // State variable(s) to be provided.
	private ListNode node;

    public ElementIterator() {
        // TODO code to be provided    
    	node = myHead;
    }

    public boolean hasNext() {
    	if (node == null) {
        return false;
        }
    	else return true;
        // TODO code to be provided
    }

    public Object next() {
    	Object result = node.myItem;
    	node = node.myNext;
        return result;
        // TODO code to be provided
    }

    public void remove() {
        // not used; do not implement
    }
}
}
