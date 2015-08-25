import java.util.*;

public class List implements Iterable {

	private ListNode myHead;
	private int mySize;
	private ListNode myTail;

	public List() {
		myHead = null;
		mySize = 0;
		myTail = myHead;
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
		if(mySize == 1)
			myTail = myHead;
	}

	public boolean equals (Object obj) {
		if (this.size() != ((List) obj).size()) {
			return false;
		}
		for (int i=0; i<size(); i++) {
			if (get(i) != ((List) obj).get(i)) {
				return false;
			}
		}
		return true;
	}

	public void add (Object x) {
		if (myHead == null) {
			addToFront(x);
		} else {
			ListNode temp = new ListNode(x);
			myTail.myNext = temp;
			myTail = myTail.myNext;
			mySize++;
		}
	} 

	public void appendInPlace (List l) {
		if (l.myTail == null) {
			//fuckthisshit
		} else if (myHead == null) {
			myHead = l.myHead;
			myTail = l.myTail;
		} else {
			myTail.myNext = l.myHead;
			myTail = l.myTail;
		}
		mySize += l.size();
	}

	public boolean isOK() {
		int count = 0;
		for (ListNode p=myHead; p!=null; p=p.myNext) {
			if (p.myItem == null) {
				return false;
			}
			count++;
		}
		if (count != mySize) {
			return false;
		}
		ListNode curNode = myHead;
		while (curNode.myNext != null) {
			curNode = curNode.myNext;
		}
		if (curNode != myTail) {
			return false;
		}
		if ( (myHead==null && myTail!= null) || (myTail==null && myHead!=null) ) {
			return false;
		}
		return true;
	}
	
	public void remove(Object obj) {
		for (ListNode p=new ListNode("sentinel", myHead); p.myNext.myNext!=null; p=p.myNext) {
			if (p.myNext.myItem.equals(obj)) {
				if (p.myNext == myHead && myHead.myNext != null) {
					myHead = p.myNext.myNext;
				}
				if (p.myNext.myNext == null) { //if a tail
					myTail = p;
				}
				p.myNext = p.myNext.myNext;
				mySize--;
			}
		}
	}
	
	public void doubleInPlace() {

	    for (ListNode p = myHead; p.myNext != null; p = p.myNext.myNext) {
	        // TODO Your code here
	    	p.myNext = new ListNode(p.myItem, p.myNext);
	    	mySize++;
	    }
	    // TODO And maybe here as well
	    myTail.myNext = new ListNode(myTail.myItem);
	    myTail = myTail.myNext;
	    mySize++;
	}
	
	public void reverse() {
	    myHead = reverseHelper(myHead);
	}

	private static ListNode reverseHelper(ListNode l, ListNode soFar) {
	    if (l == null) {
	        return soFar;
	    } else {
	    	ListNode temp = l.myNext;
	    	l.myNext = soFar;
	    	return reverseHelper ( temp , l );
	    }
	}
	
	private static ListNode reverseHelper(ListNode head) {
	    ListNode p, soFar;
	    // p plays the role of l in the previous version.
	    for (p = head, soFar = null; p != null;) {
	        ListNode temp = p;
	        soFar = new ListNode(p.myItem, soFar);
	        p = temp.myNext;
	    }
	    return soFar;
	}
	
	public Iterator iterator() {
		return new ElementIterator();
	}

	public class ElementIterator implements Iterator {

		// State variable(s) to be provided.
		int myIndex;


		public ElementIterator() {
			// TODO code to be provided
			myIndex = 0;
		}

		public boolean hasNext() {
			return (myIndex < size());
			// TODO code to be provided
		}

		public Object next() {
			myIndex = myIndex + 1;
			return get(myIndex - 1);
			// TODO code to be provided
		}

		public void remove() {
			// not used; do not implement
		}
	}

}
