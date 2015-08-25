import java.util.*;

public class List implements Iterable {

	private ListNode myHead;
	public ListNode myTail;
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
		myHead = new ListNode (obj, myHead);
		mySize++;
		for (ListNode p = myHead; p != null; p = p.myNext) {
			if (p.myNext == null) {
				myTail = p;
				return;
			}
		}
	}

	public boolean equals (Object obj) {
		// TODO Your code here
		if (((List)obj).isEmpty() && !this.isEmpty()) {
			return false;
		}
		else if (this.isEmpty() && !((List)obj).isEmpty()) {
			return false;
		}
		else if (this.size() != ((List)obj).size()) {
			return false;
		}
		else if (!this.toString().equals(obj.toString())) {
			return false;
		}
		return true;
	}

	public void add (Object x) {
		// TODO Your code here
		if (isEmpty()) {
			addToFront(x);
		}
		else if (myTail == null) {
			myTail = new ListNode(x);
			mySize++;
		}
		else if (myTail != null) {
			myTail.myNext = new ListNode(x);
			myTail = myTail.myNext;
			mySize++;
		}
	}

	public void appendInPlace (List l) {
		// TODO Your code here 
		if (isEmpty()) {
			myHead = l.myHead;
			myTail = l.myTail;
		} 
		else if (l.isEmpty()) {
			return;
		} else {
			myTail.myNext = l.myHead;
			myTail = l.myTail;
			mySize += l.size();
		}
	}
	
	public Iterator iterator() {
	    return new ElementIterator();
	}

	public class ElementIterator implements Iterator {

	    // State variable(s) to be provided.
		
		int curr_node_index;
		ListNode curr_node;

	    public ElementIterator() {
	        // TODO code to be provided
	    	curr_node_index = 0;
	    	curr_node = myHead;
	    }

	    public boolean hasNext() {
	        // TODO code to be provided
	    	return curr_node_index < size();
	    }

	    public Object next() {
	        // TODO code to be provided
	    	ListNode old_node = curr_node;
	    	curr_node = curr_node.myNext;
	    	curr_node_index++;
	    	return old_node.myItem;
	    }

	    public void remove() {
	        // not used; do not implement
	    }
	}
	
	public boolean isOK() {
		int act_size = 0;
		boolean null_items = false;
		ListNode act_tail = null;
		for (ListNode p = myHead; p != null; p = p.myNext) {
			act_size++;
			if (p.myItem == null) {
				null_items = true;
			}
			else if (p.myNext == null) {
				act_tail = p;
			}
		}
		if (mySize != act_size) {
			System.out.println("Sizes are not the same.");
			return false;
		}
		else if (myHead == null && myTail != null) {
			System.out.println("Head is null, but tail isn't.");
			return false;
		}
		else if (myTail != act_tail) {
			System.out.println("The tail is not actually the tail.");
			return false;
		}
		else if (null_items) {
			System.out.println("You've got null items!");
			return false;
		}

		return true;
	}
	
	public void remove(Object x) {
		if (size() == 0) {
			myTail = null;
			return;
		}
		if (myHead.myItem.equals(x)) {
			mySize--;
			myHead = myHead.myNext;
			remove(x);
		} else {
			ListNode last = myHead;
			for (ListNode p = myHead; p != null; p = p.myNext) {
				if (p.myItem.equals(x)) {
					last.myNext = p.myNext;
					if (p.myNext == null) {
						myTail = last;
					}
					mySize--;
				} else {
					last = p;
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
		// ADD CODE HERE 
		//myHead = reverseHelper(this.myHead, null);
		myHead = reverseHelper(this.myHead);
	}

	public static ListNode reverseHelper(ListNode l, ListNode soFar) {
		if (l == null) {
			return soFar;	
		} else {
			// ADD CODE HERE 
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
