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
		boolean change_my_tail = false;
		if (myHead == null) {
			change_my_tail = true;
		}
		myHead = new ListNode (obj, myHead);
		++mySize;
		if (change_my_tail) myTail = myHead;
		
		// check for invariants
		if (!isOK()) System.err.println("Wrong in addToFront!");
	}

	public boolean equals (Object obj) {
		ListNode p_obj = ((List)obj).myHead;
		if (p_obj == null && myHead != null) return false;
		if (p_obj != null && myHead == null) return false;
		if (p_obj == null && myHead == null) return true;
		if (((List)obj).size() != size()) return false;
		for (ListNode p = myHead; p != null; p = p.myNext) {
			if (!p.myItem.equals(p_obj.myItem)) return false;
			p_obj = p_obj.myNext;
		}
		return true;
	}

	public void add (Object x) {
		if (myHead == null) {
			// adding the first node
			myHead = new ListNode(x);
			++mySize;
			myTail = myHead;
			
			
			// check for invariants
			if (!isOK()) System.err.println("Wrong in add!");
			return;
		}
		myTail.myNext = new ListNode(x);
		++mySize;
		myTail = myTail.myNext;
		
		
		// check for invariants
		if (!isOK()) System.err.println("Wrong in add!");
	}

	public void appendInPlace (List l) {
		ListNode p_l = ((List)l).myHead;
		if (p_l == null && myHead == null) return;
		if (p_l != null && myHead == null) {
			myHead = p_l;
			mySize = l.size();
			myTail = l.myTail;
			
			
			// check for invariants
			if (!isOK()) System.err.println("Wrong in appendInPlace!");
			return;
		}
		if (p_l == null && myHead != null) return;
		mySize += l.size();
		myTail.myNext = p_l;
		myTail = myTail.myNext;
		
		
		// check for invariants
		if (!isOK()) System.err.println("Wrong in appendInPlace!");
		return;
	}
	
	
	
	
	
	
	public Iterator iterator() {
	    return new ElementIterator();
	}

	public class ElementIterator implements Iterator {

	    // State variable(s) to be provided.
		private ListNode current;
		private int size;
		private int current_size;

	    public ElementIterator() {
	        current = myHead;
	        size = size();
	        current_size = 0;
	    }

	    public boolean hasNext() {
	    	return current_size < size;
	    }

	    public Object next() {
	    	Object rtn = current.myItem;
	    	current = current.myNext;
	    	current_size++;
	    	return rtn;
	    }

	    public void remove(Object obj) {
	    	// unsupported operation
	    }
	}
	
	
	
	private boolean isOK() {
		// the value stored in mySize is the number of nodes in this list
		int correct_size = 0;
		ListNode correct_tail = null;
		ListNode p  = null;
		if (myHead != null) {
			correct_size = 1;
			for (p = myHead; p.myNext != null; p = p.myNext) {
				correct_size++;
			}
			correct_tail = p;
		}
		if (correct_size != mySize) {
			return false;
		}
		// all myItem objects in this list are non-null
		for (p = myHead; p != null; p = p.myNext) {
			if (p.myItem == null) {
				return false;
			}
		}
		// either both myHead and myTail are null,
		// or myTail is a reference to the last node in the list 
		// whose first node is the node that myHead refers to
		return (myHead == myTail) || (correct_tail == myTail);
	}
	
    public void remove(Object obj) {
        if (myHead == null || obj == null) return;
        ListNode prev = myHead;
        ListNode p = myHead;
		while (p.myItem.equals(obj)) {
			--mySize;
			myHead = myHead.myNext;
			p = myHead;
			if (myHead == null) {
				myTail = null;
				
				
				// check for invariants
				if (!isOK()) System.err.println("Wrong in remove!");
				return; // everything is "Hilfinger" and we have removed all of them
			}
		}
		// at this point. there is no "Hilfinger" in the beginning
		for (p = myHead; p != null; p = p.myNext) {
			if (p.myItem.equals(obj)) {
				--mySize;
				prev.myNext = p.myNext;
			}
			else {
				// this node is not "Hilfinger"
				prev = p;
				myTail = p;
			}
		}
		
		
		// check for invariants
		if (!isOK()) System.err.println("Wrong in remove!");
		return;
    }
    
    
    
    public void reverse() {
    	if (myHead == null) return;
    	else {
        	reverseHelper(this.myHead, null);
        	if (myHead != myTail) {
        		// only need to swap when there are 2 or more nodes
            	ListNode temp = myHead;
            	myHead = myTail;
            	myTail = temp;
        	}
    	}
    	return;
    }

    public static void reverseHelper(ListNode l, ListNode soFar) {
        if (l == null) return;
        else {
        	ListNode temp = l.myNext;
        	l.myNext = soFar;
        	reverseHelper(temp, l);
        }
    }

}
