import java.util.*;

public class List {

	private ListNode myHead;
	private int mySize;
	private ListNode myTail;

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
		if (myHead == null) {
			myHead = new ListNode (obj, myHead);
			myTail = myHead;
		} else {
			myHead = new ListNode (obj, myHead);
		}
		mySize += 1;
	}

	public boolean equals (Object obj) {
		if (obj == null) {
			return false;
		}
		ListNode b = ((List) obj).myHead;
		for (ListNode p = myHead; p != null; p = p.myNext) {
			if (b == null || b.myItem.equals(p.myItem) == false) {
				return false;
			} else {
				b = b.myNext;
			}
		}
		
		if (b == null) {
			return true;
		}
		return false;
	}

	public void add (Object x) {
		if (myHead == null) {
			myHead = new ListNode(x);
			myTail = myHead;
		} else {
			myTail.myNext = new ListNode(x);
			myTail = myTail.myNext;
		}
		mySize += 1;
	}

	public void appendInPlace (List l) {
		if (l.myHead == null) {
			return;
		}
		if (myHead == null) {
			myHead = l.myHead;
			myTail = l.myTail;
		} else {
			myTail.myNext = l.myHead;
			myTail = l.myTail;
		}
		mySize += l.mySize;
		
	}
	
	public boolean isOK() {
		int rtn = 0;
		for (ListNode p = myHead; p != null; p = p.myNext) {
			if (p.myItem == null) {
				return false;
			}
			rtn++;
		}
		if (rtn != mySize) {
			return false;
		}
		ListNode tail = null;
		if (myHead != null) {
			for (ListNode a = myHead; a != null; a = a.myNext){
				tail = a;
			}
		}
		
		
		if ((myHead == null && myTail == null) || tail == myTail && myHead != null) {
			return true;
		}
		return false;
		
	}
	
	public void remove(Object obj){
		if (mySize == 0){
			return;
		} else if (mySize == 1) {
			if (myHead.myItem.equals(obj)) {
				myHead = null;
				myTail = myHead;
				mySize --;
			}
		} else {
			for (ListNode p = myHead; p.myItem.equals(obj); p = myHead){
				myHead = myHead.myNext;
				mySize--;
				if(myHead==null){
					return;
				}
			}
			ListNode ref = myHead;
			for (ListNode p = myHead.myNext; p != null; p = p.myNext) {
				if (p.myItem.equals(obj)) {
					ref.myNext = p.myNext;
					mySize--;
				} else {
					ref = ref.myNext;
				}

			}
			
			if (myHead == null) {
				myTail = null;
			} else {
				ListNode tail = null;
				for (ListNode a = myHead; a != null; a = a.myNext){
					tail = a;
				}
				myTail = tail;
			}
			
			
		}
		
	}
	
	public void reverse() {
		ListNode head = myHead;
		ListNode tail = myTail;
	    reverseHelper(myHead);
	    myHead = tail;
	    myTail = head;
	}

	public static ListNode reverseHelper(ListNode l, ListNode soFar) {
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
	        p = p.myNext;
	        temp.myNext = soFar;
	        soFar = temp;
	    }
	    return soFar;
	}
	
	public void doubleInPlace() {

	    for (ListNode p = myHead; p != null; p = p.myNext) {
	        ListNode temp = p;
	        ListNode copy = new ListNode(temp.myItem);
	        copy.myNext = temp.myNext;
	        temp.myNext = copy;
	        p = p.myNext;
	    }
	    ListNode tail = null;
		for (ListNode a = myHead; a != null; a = a.myNext){
			tail = a;
		}
		myTail = tail;
	}

	public Iterator iterator() {
	    return new ElementIterator();
	}

	public class ElementIterator implements Iterator {

	    // State variable(s) to be provided.
		private int index;

	    public ElementIterator() {
	        index = 0;
	    }

	    public boolean hasNext() {
	        try {
	        		get(index);
	        		return true;
	        } catch (IllegalArgumentException e) {
	        		return false;
	        }
	    }

	    public Object next() {
	        index ++;
	    		return get(index - 1);
	    }

	    public void remove() {
	        // not used; do not implement
	    }
	}

}
