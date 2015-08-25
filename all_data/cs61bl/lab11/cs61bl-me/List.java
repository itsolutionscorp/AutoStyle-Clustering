import java.util.*;

public class List {

	private ListNode myHead;
	private int mySize;
	private ListNode myTail;

	public List() {
		myHead = null;
		myTail = myHead;
		mySize = 0;
	}

	public Object getTail() {
		if (myTail == null) {
			return null;
		} else {
			return myTail.myItem;
		}
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
		ListNode curr;

	    public ElementIterator() {
	        // TODO code to be provided
	        curr = myHead;
	    }

	    public boolean hasNext() {
	        // TODO code to be provided
	    	if (isEmpty()) {
	    		return false;
	    	} else {
	    		return curr.myNext != null;
	    	}
	    }

	    public Object next() {
	        // TODO code to be provided
	       	if (hasNext()) {
	       		Object toReturn = curr.myItem;
	       		curr = curr.myNext;
	       		return toReturn;
	       	} else {
	       		return null;
	       	}
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
		// int rtn = 0;
		// for (ListNode p = myHead; p != null; p = p.myNext) {
		// 	rtn++;
		// }
		// return rtn;
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
		if (((List) obj).isEmpty() && isEmpty()) {
			return true;
		}
		ListNode temp1 = myHead;
		ListNode temp2 = ((List) obj).myHead;
		while (temp1 != null && temp2 != null) {
			if (temp1.myItem.equals(temp2.myItem)) {
				temp1 = temp1.myNext;
				temp2 = temp2.myNext;
				if (temp1 == null && temp2 == null) {
					return true;
				}	
			} else {
				return false;
			}
		} return false;
	}

	public void add (Object x) {
		// TODO Your code here 
		if (isEmpty()) {
			myHead = new ListNode(x);
			mySize++;
			return;
		}
		ListNode p = myHead;
		while (p.myNext != null) {
			p = p.myNext;
		}
		ListNode add = new ListNode(x);
		p.myNext = add;
		myTail = add;
		mySize++;
		return;
	}

	public void appendInPlace (List l) {
		// TODO Your code here 
		if (isEmpty()) {
			myHead = l.myHead;
			mySize = l.size();
			myTail = l.myTail;
			return;
		}
		ListNode p = myHead;
		while (p.myNext != null) {
			p = p.myNext;
		}
		p.myNext = l.myHead;
		myTail = l.myTail;
		mySize = mySize + l.size();
	}

	public boolean isOK() {
		int i = 0;
		for (ListNode p = myHead; p != null; p = p.myNext) {
			if (p.myItem == null) {
				System.out.println("myItem cannot be null");
				return false;
			}
			i++;
		}
		if (i != size()) {
			System.out.println("size does not match");
			System.out.println(i);
			System.out.println(size());
			return false;
		}
		if ((myHead != null || myTail != null)) {
			try {
				ListNode temp = myHead;
				while (temp != myTail) {
					temp = temp.myNext;
				}
			} catch (NullPointerException e) {
				return false;
			}
		}
		return true;
	}

	public void remove(Object obj) {
		if (myHead == null) {
			System.out.println("Empty list.");
		} else if (myHead.myItem.equals(obj)) {
			if (myHead.myNext == null) {
				myHead = null;
				mySize--;
				return;
			} else { 
				myHead = myHead.myNext;
				mySize--;
			}
		} else if (myHead.myNext != null) {
			ListNode cur = myHead.myNext;
			ListNode prev = myHead;
			while (cur != myTail) {
				if (cur.myItem.equals(obj)) {
					prev.myNext = cur.myNext;
					mySize--;
				}
				cur = cur.myNext;
			}
		}
	}

	public void doubleInPlace() {
		if (myHead == null) {
			System.out.println("cannot double empty list");
		} else {
			ListNode cur = myHead.myNext;
			ListNode prev = myHead;
			while (cur != null) {
				ListNode add = new ListNode(prev.myItem, prev.myNext);
				prev.myNext = add;
				prev = cur;
				cur = cur.myNext;
				mySize++;
			}
			ListNode add = new ListNode(prev.myItem);
			prev.myNext = add;
			mySize++;
		}
	}

	public void reverse() {
		ListNode rev = reverseHelper(myHead);
		myHead = rev;
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
