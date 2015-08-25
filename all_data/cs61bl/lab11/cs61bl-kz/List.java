import java.util.Iterator;

public class List implements Iterable{

	private ListNode myHead;
	private ListNode myTail;
	private int mySize;

	public List() {
		myHead = null;
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
		int rtn = 0;
		for (ListNode p = myHead; p != null; p = p.myNext) {
			rtn++;
		} mySize = rtn;
		return rtn;
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
	}

	public boolean equals (Object obj) {
		// TODO Your code here
		if (this.isEmpty() && ((List) obj).isEmpty()) {
			return true;
		} else if (this.isEmpty() || ((List) obj).isEmpty()) {
			return false;
		} else if (this.size() != ((List) obj).size()) {
			return false;
		} else {
			ListNode tracker;
			tracker = ((List) obj).myHead;
			while (tracker != null) {
				for (ListNode p = myHead; p != null; p = p.myNext) {
					if (p.myItem == tracker.myItem) {
						tracker = tracker.myNext;
						continue;
					} else
						return false;
				}
				return true;
			}
		}
		return false;

	}

	public void add (Object x) {
		// TODO Your code here 
		if (this.isEmpty()) {
			this.myHead = new ListNode(x);
		} else
		for (ListNode p = myHead; p != null; p = p.myNext) {
			if (p.myNext != null) {
				continue;
			} else
				p.myNext = new ListNode(x);
				this.myTail = p.myNext;
				break;
		}
	}

	public void appendInPlace (List l) {
		// TODO Your code here
		if (this.isEmpty()) {
			this.myHead = l.myHead;
		} else
		for (ListNode p = myHead; p != null; p = p.myNext) {
			if (p.myNext != null) {
				continue;
			} else
				p.myNext = l.myHead;
				this.myTail = l.myTail;
				break;
		}
	}
	
	public boolean isOk() {
		if (this.size() != this.mySize) {
			return false;
		} if (this.myHead == null && this.myTail != null) {
			return false;	
		}for (ListNode p = myHead; p != null; p = p.myNext) {
			if (p.myNext == null) {
				return (this.myTail == p);
			}
			if (p.myItem == null) {
				return false;
			}
		}return false;
	}
	
	public ListNode remove_helper(ListNode temp, Object obj) {
		if (temp.myItem.equals(obj)) {
			if  (temp.myNext == null) {
				temp = null;
				return temp;
			}
			temp = temp.myNext;
			return remove_helper(temp, obj);	
		}if (temp.myNext == null) {
			return temp;
		}temp.myNext = remove_helper(temp.myNext, obj);
		return temp;
	}
	
	public void remove(Object obj) { 
//		while (myHead.myItem.equals(obj)) {
//			if  (myHead.myNext == null) {
//				myHead = null;
//				return;
//			}
//			myHead = myHead.myNext;
//		}
//		for (ListNode p = myHead; p != null; p = p.myNext) {
//				if (p.myItem.equals(obj)) {
//					if(p.myNext == null) {
//						p = null;
//						return;
//					}
//					p = p.myNext;
//					continue;
//				}if (p.myNext == null) {
//					return;
//				}if (p.myNext.myItem.equals(obj) && p.myNext.myNext == null) {
//					p.myNext = null;
//					myTail = p;
//					return;
//				}
//				if (p.myNext.myItem.equals(obj)) {
//					p.myNext = p.myNext.myNext;
//					continue;
//				} 
//			}
		this.myHead = remove_helper(myHead, obj);
	}
	public void reverse() {
		myHead = reverseHelper(myHead);
	}
	
	private static ListNode reverseHelper(ListNode l, ListNode soFar) {
		if (l == null) {
			return soFar;
		}else {
			ListNode temp = l.myNext;
			l.myNext = soFar;
			return reverseHelper(temp, l);
		}	
	}
	
	private static ListNode reverseHelper(ListNode head) {
	    ListNode p, soFar;
	    // p plays the role of l in the previous version.
	    for (p = head, soFar = null; p != null;) {
	        ListNode temp = p;
	        soFar = new ListNode (p.myItem, soFar);
	        p = p.myNext;
	    }
	    return soFar;
	}
	
	
	public void doubleInPlace() {
	    for (ListNode p = myHead ; p != null; p = p.myNext) {
	        // TODO Your code here
	    	ListNode temp = p.myNext;
	    	p.myNext = new ListNode(p.myItem, temp);
	    	p = p.myNext;
	    }
	    // TODO And maybe here as well
	}
	public Iterator iterator() {
	    return new ElementIterator();
	}

	public class ElementIterator implements Iterator {
	    // State variable(s) to be provided.
		ListNode current;
	    public ElementIterator() {
	    	current = myHead;
	        // TODO code to be provided
	    }

	    public boolean hasNext() {
	    	if (current !=null) {
	    		return (current.myNext != null);
	    	} else
	    		return false;
	    	}
	        // TODO code to be provided
	    public Object next() {
	    	Object currentItem = current.myItem;
	    	current = current.myNext;
	    	return currentItem;
	        // TODO code to be provided
	    }

	    public void remove() {
	        // not used; do not implement
	    	return;
	    }
	    }

	   
	}
