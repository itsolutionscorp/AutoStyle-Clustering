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
		
		private ListNode removeHelper(Object o) {
			if (myNext == null) {
				if (myItem.equals(o)) {
					return null;
				} else {
					return this;
				}
			} else if (myItem.equals(o)) {
				return myNext.removeHelper(o);
			} else {
				myNext = myNext.removeHelper(o);
				return this;
			}
		}

	}
	
	public void remove(Object o) {
		myHead = myHead.removeHelper(o);
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
		// TODO Your code here 
		List comp = (List) obj;
		if (size() != comp.size()) {
			return false;
		} else if (size() == 0 && comp.size() == 0) {
			return true;
		}
		for (int i = 0; i < size(); i++) {
			if (get(i).equals(comp.get(i)) == false) {
				return false;
			} else {
				continue;
			}
		}
		return true;
	}

	public void add (Object x) {
		// TODO Your code here 
		mySize++;
		if (isEmpty()) {
			myHead = new ListNode(x);
			myTail = myHead;
		} else {
			for (ListNode p = myHead; p != null; p = p.myNext) {
				if (p.myNext == null) {
					p.myNext = new ListNode(x);
					myTail = p.myNext;
					return;
				} else {
					continue;
				}
			}
		}
	}

	public void appendInPlace (List l) {
		// TODO Your code here 
		if (isEmpty()) {
			myHead = l.myHead;
		} else {
			for (ListNode p = myHead; p != null; p = p.myNext) {
				if (p.myNext == null) {
					p.myNext = l.myHead;
					return;
				} else {
					continue;
				}
			}
		}
		mySize += l.mySize;
		myTail = l.myTail;
	}
	
	public boolean isOK() {
		int node_counter = 0;
		for (ListNode p = myHead; p != null; p = p.myNext) {
			node_counter++;
			if (p.myItem == null) {
				return false;
			} else if (p == myTail || myTail == null && myHead == null) {
				break;
			} else if (p.myNext == null) {
				return false;
			}
		}
		return node_counter == size();
	}
	
	public void doubleInPlace() {
		mySize *= 2;
	    for (ListNode p = myHead; p != null; p = p.myNext) {
	         ListNode holder = p.myNext;
	         p.myNext = new ListNode(p.myItem, holder);
	         p = p.myNext;
	    }
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
	    	return reverseHelper ( temp , l);
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
	
	public Iterator iterator() {
	    return new ElementIterator();
	}

	public class ElementIterator implements Iterator {

	    // State variable(s) to be provided.
		private int index;

	    public ElementIterator() {
	        // TODO code to be provided
	    	index = -1;
	    }

	    public boolean hasNext() {
	        // TODO code to be provided
	    	return index < size() - 1;
	    }

	    public Object next() {
	        // TODO code to be provided
	    	index++;
	    	return get(index);
	    	
	    }

	    public void remove() {
	        // not used; do not implement
	    }
	}

}
