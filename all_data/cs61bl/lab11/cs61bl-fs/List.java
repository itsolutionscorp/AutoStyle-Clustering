import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

import java.util.*;

public class List implements Iterable {

	private ListNode myHead;
	private int mySize;
	private ListNode myTail;

	public List() {
		myHead = null;
		myTail = null;
		mySize = 0;
	}
	
	public Iterator iterator() {
	    return new ElementIterator();
	}

	public class ElementIterator implements Iterator {

	    // State variable(s) to be provided.
		ListNode next;

	    public ElementIterator() {
	        next = myHead;
	    }

	    public boolean hasNext() {
	        if (next != null) {
	        	return true;
	        }
	        else {
	        	return false;
	        }
	    }

	    public Object next() {
	    	Object result = next.myItem;
	    	next = next.myNext;
	        return result;
	    }

	    public void remove() {
	        // not used; do not implement
	    }
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
	
	public void reverse() {
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
	
	private static ListNode reverseHelper(ListNode l, ListNode soFar) {
	    if (l == null) {
	        return soFar;
	    } else {
	    	ListNode temp = l.myNext;
	    	l.myNext = soFar;
	    	return reverseHelper (temp, l) ;
	    }
	}
	
	public void doubleInPlace() {
	    for (ListNode p = myHead; p != null; p = p.myNext) {
	    	ListNode rest = p.myNext;
	        p.myNext = new ListNode(p.myItem, rest);
	        p = p.myNext;
	    }
	}

	public void remove(Object x) {
		if (myHead != null) {
			if (myHead.myItem.equals(x)) {
				myHead = null;
				myTail = null;
				mySize--;
			}
			else {
				ListNode prev = myHead;
				for (ListNode p = myHead.myNext; p != null; p = p.myNext) {
					if (p.myItem.equals(x)) {
						prev.myNext = p.myNext;
						mySize--;
						
					}
				}
			}
		}
	}
	
	public boolean isOk() {

		ListNode tracker = myHead;
		int counter = 0;
		if (myHead != null) {
			while (tracker.myNext != null) {
				if (tracker.myItem == null) {
					return false;
				}
				tracker = tracker.myNext;
				counter++;
			}
		}
		return (counter == mySize) && ((myHead == null && myTail == null) || (tracker == myTail));
	}
	
	public boolean isEmpty() {
		return myHead == null;
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
		List list2 = (List) obj;
		if (list2.isEmpty() && !this.isEmpty()) {
			return false;
		}
		if (!list2.isEmpty() && this.isEmpty()) {
			return false;
		}
		if (list2.size() != this.size()) {
			return false;
		}
		
		ListNode tracker = list2.myHead;
		for (ListNode p = myHead; p != null; p = p.myNext) {
			if (p.myItem != tracker.myItem) {
				return false;
			}
			tracker = tracker.myNext;
		}
		return true;
	}

	public void add (Object x) {
		if (myHead == null) {
			myHead = new ListNode(x);
			myTail = myHead;

		}
		else {
			myTail.myNext = new ListNode(x);
			myTail = myTail.myNext;
		}
		mySize++;
	}

	public void appendInPlace (List l) {
		if (this.isEmpty()) {
			this.myHead = l.myHead;
			this.myTail = l.myTail;
			this.mySize = l.mySize;
		}
		else if (!l.isEmpty()) {
			this.myTail.myNext = l.myHead;
			this.myTail = l.myTail;
			this.mySize += l.mySize;
		}		
	}
}
