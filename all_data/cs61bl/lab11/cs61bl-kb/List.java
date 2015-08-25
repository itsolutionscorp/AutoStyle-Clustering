import java.util.*;
public class List implements Iterable{
	
	int mySize;
	private ListNode myHead;
	private ListNode myTail;

	public List() {
		myHead = null;
		myTail = null;
	}

	public boolean isEmpty() {
		return (myHead == null) && (myTail == null);
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
	    return new ElementIterator(this);
	}

	public class ElementIterator implements Iterator {
		private int index = 0;
		private List myList;
	    // State variable(s) to be provided.

	    public ElementIterator(List l) {
	        myList = l;
	    }

	    public boolean hasNext() {
	        return index < myList.size();
	        
	    }

	    public Object next() {
	        if (hasNext()) {
	        	Object o = myList.get(index);
	        	index += 1;
	        	return o;
	        }
	        return null;
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
//		int rtn = 0;
//		for (ListNode p = myHead; p != null; p = p.myNext) {
//			rtn++;	
//		}
//		return rtn;
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
		mySize += 1;
	}

	public boolean equals (Object obj) {
		if (obj instanceof List) {
			List l = (List)obj;
			int index = 0;
			if ((this.size() == l.size())) {
				for (ListNode p = myHead; p != null; p = p.myNext) {	
					if (p.myItem == l.get(index)) {
						index += 1;
					}
					else {
						return false;
					}
				}
				return true;
			}
			else {
				return false;
			}
		}
		return false;
	}

	public void add (Object x) {
		if (this.isEmpty()) {
			this.myHead = new ListNode(x);
			mySize += 1;
			return;
		}
		for (ListNode p = myHead; p != null; p = p.myNext) {
			if (p.myNext == null) {
				p.myNext = new ListNode(x);
				myTail = p.myNext;
				mySize += 1;
				return;
			}
		}		
	}

	public void appendInPlace (List l) {
		if (this.isEmpty()) {
			this.myHead = l.myHead;
			mySize = l.size();
			return;
		}
		else {
			for (ListNode p = myHead; p != null; p = p.myNext) {
				if (p.myNext == null) {
					p.myNext = l.myHead;
					mySize = mySize + l.size();
					return;
				}
			}
		}
	}
	
	public boolean isOK() {
		boolean headTailCheck;
		ListNode tailCheck;
		ListNode headCheck;
		if (this.isEmpty()) {
			headTailCheck = ((myHead == null) && (myTail == null));
			return (mySize == 0) && (headTailCheck);
		}
		else {
			tailCheck = new ListNode(null);
			int rtn = 0;
			headCheck = myHead;
			for (ListNode p = myHead; p != null; p = p.myNext) {
				if (p.myNext == null) {
					tailCheck = p;
				}
				if (p.myItem == null) {
					return false;
				}
				rtn++;	
			}
			if ((tailCheck == myTail) && (headCheck == this.myHead)) {
				headTailCheck = true;
			}
			else {
				headTailCheck = false;
			}
		return (rtn == mySize) && headTailCheck;
		}
	}
	
	public void remove(Object o) {
		if (myHead != null) {
			if (myHead.myItem == o) {
				myHead = myHead.myNext;
				return;
			}
			for (ListNode p = myHead; p.myNext != null; p = p.myNext) {
				if (p.myNext.myItem == o) {
					p.myNext = p.myNext.myNext;
					break;
				}
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
		this.myHead = reverseHelper(this.myHead);
	}

//	public static ListNode reverseHelper(ListNode l, ListNode soFar) {
//		if (l == null) {
//			return soFar;
//		} else {
//			return reverseHelper(l.myNext, new ListNode(l.myItem, soFar));
//		}
//	}
	
	private static ListNode reverseHelper(ListNode head) {
	    ListNode p, soFar;
	    // p plays the role of l in the previous version.
	    for (p = head, soFar = null; p != null;) {
	        ListNode temp = p;
	        soFar = new ListNode(temp.myItem, soFar);
	        p = p.myNext;
	    }
	    return soFar;
	}
}
