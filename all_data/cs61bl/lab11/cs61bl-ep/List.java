import java.util.*;

public class List {

	private ListNode myHead; //reference to first node in list;
	private int mySize; //length of list
	private ListNode myTail; //reference to last node in list;
	//both myHead and myTail will contain null if list is empty; both will point to same node if only one node is in list
	

	public List() {
		myHead = null;
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
		/*int rtn = 0;
		for (ListNode p = myHead; p != null; p = p.myNext) {
			rtn++;
		}
		return rtn;*/
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
		if (size() != ((List)obj).size() ) {
			return false;
		}
		else if (isEmpty() && ((List)obj).isEmpty()) {
			return true;
		} else {
			ListNode o = ((List)obj).myHead;
			for (ListNode p = myHead; p != null; p = p.myNext, o = o.myNext) {
				if (!p.myItem.equals(o.myItem)) {
					return false;
				}
			}
		}
		return true;
	}

	public void add (Object x) {
		if (isEmpty()) {
			myHead = new ListNode(x);
			myTail = myHead;
		} else {
			ListNode p = myHead;
			while (p.myNext != null) {
				p = p.myNext;
			}
			p.myNext = new ListNode(x);
			myTail = p.myNext;
		}
		mySize++;
	}

	public void appendInPlace (List l) {
		if (isEmpty()) {
			myHead = l.myHead;
		} else {
			ListNode p = myHead;
			while (p.myNext != null) {
				p = p.myNext;
			}
			p.myNext = l.myHead;
		}
		myTail = l.myTail;
		mySize += l.mySize;
	}
	
	//( a b c ) -> ( a a b b c c ); destructive method
	//Don't call any methods other than the ListNode constructor,& don't call it more than once per node in original list
	public void doubleInPlace() {

	    for (ListNode p = myHead; p != null; p = p.myNext) {
	        p.myNext = new ListNode(p.myItem, p.myNext);
	        p = p.myNext;
	    }
	    mySize *= 2;
	}
	
	
	public void reverse() {
	    //myHead = reverseHelper(myHead, null);  //with first reverseHelper
		myHead = reverseHelper(myHead);
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
	
	
	public void remove(Object o) {
		
		ListNode p = myHead;
		boolean beginning = true;
		while (beginning) {
			if (p != null && !p.myItem.equals(o)) {
				beginning = false;
				if (p.myNext != null) {
					
				} else {
					return;
				}
			} else if (p != null && p.myItem.equals(o)) {
				p = p.myNext;
				myHead = p;
				mySize--;
			} else {
				return;
			}
		}
		
		while (p.myNext != null) {
			if (p.myNext.myItem.equals(o)) {
				p.myNext = p.myNext.myNext;
				mySize--;
				if (p.myNext == null) {
					myTail = p;
				}
			} else if (!p.myNext.myItem.equals(o)) {
				p = p.myNext;
				if (p.myNext == null) {
					myTail = p;
				}
			}
		}
	}
	
	public Iterator iterator() {
	    return new ElementIterator();
	}

	public class ElementIterator implements Iterator {

	    private ListNode p;

	    public ElementIterator() {
	        p = myHead;
	    }

	    public boolean hasNext() {
	        if (p != null && p.myItem != null) { //should myItem be myNext?
	        	return true;
	        }
	        return false;
	    }

	    public Object next() {
	    	if (p != null) {
	    		Object curr = p.myItem;
	    		p = p.myNext;
	    		return curr;
	    	}
	        return null;
	        
	    }

	    public void remove() {
	        // not used; do not implement
	    }
	}
	
	public void isOK() {
		if (isEmpty()) {
			if (mySize != 0) {
				throw new IllegalStateException("mySize must be zero for an empty list!");
			}
		} else {
			int checkSize = 1;
			ListNode p = myHead;
			while (p.myNext != null) {
				p = p.myNext;
				checkSize++;
			}
			if (checkSize != mySize) {
				throw new IllegalStateException("mySize does not say the correct number of nodes in the list!");
			}
		}
		
		if (!isEmpty()) {
			ListNode p = myHead;
			while(p.myNext != null) {
				if (p.myItem == null) {
					throw new IllegalStateException("myItem cannot be null!");
				}
				p = p.myNext;
			}
		}
		
		if (isEmpty()) {
			if (myHead != null || myTail != null) {
				throw new IllegalStateException("myHead and myTail must be null for empty lists!");
			}
		} else {
			ListNode p = myHead;
			while (p.myNext != null) {
				p = p.myNext;
			}
			if (!myTail.equals(p)) {
				throw new IllegalStateException("myTail must reference the last node in the list whose first node is the node myHead refers to!");
			}
		}
		
	}

}
