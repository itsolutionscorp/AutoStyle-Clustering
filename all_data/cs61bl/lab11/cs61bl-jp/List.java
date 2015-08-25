import java.util.*;

public class List implements Iterable {

	private ListNode myHead;
	private ListNode myTail;
	private int mySize;
	private static ListNode sooFar;
	
	public List() {
		myHead = null;
		myTail = null;
		mySize = 0;
		sooFar = null;
	}
	
	public ListNode myHead() {
		return myHead;
	}

	public boolean isEmpty() {
		return myHead == null;
	}
	
	public Iterator iterator() {
		return new ListIterator(this);
	}
	
	public class ListIterator implements Iterator {
		private List l;
		
		public ListIterator(List g) {
	        this.l = g;
	    }

	    public boolean hasNext() {
	        if (l.size() <= 1) {
	        	return false;
	        } else {
	        	return true;
	        }
	    }

	    public Object next() {
	        ListNode temp = l.myHead.myNext;
	        Object ret = l.myHead.myItem;
	        l.myHead = temp;
	        l.mySize--;
	        return ret;
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

	public String toString() {
		String rtn = "( ";
		for (ListNode p = myHead; p != null; p = p.myNext) {
			rtn = rtn + p.myItem + " ";
		}
		return rtn + ")";
	}
	
	public boolean isOK() {
		int size = 0;
		if (myHead == null && myTail != null || myHead != null && myTail == null) {
			return false;
		}
		for (ListNode p = myHead; p != null; p = p.myNext) {
			size++;
			if (p.myItem == null) {
				return false;
			}
			if (p.myNext == null) {
				if (p != myTail) {
					return false;
				}
			}
		}
		if (size != mySize) {
			return false;
		}
		return true;
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
		List k = (List) obj;
		return k.toString().equals(this.toString());
	}

	public void add (Object x) {
		ListNode temp = myHead;
		mySize++;
		if (temp == null) {
			myHead = new ListNode (x, null);
			myTail = myHead;
			return;
		}
		while (temp != null) {
			if (temp.myNext == null) {
				temp.myNext = new ListNode(x, null);
				myTail = temp.myNext;
				break;
			}
			temp = temp.myNext;
		}
	}

	public void appendInPlace (List l) {
		ListNode temp = myHead;
		mySize += l.mySize;
		if (temp == null) {
			myHead = l.myHead;
			myTail = l.myTail;
			return;
		}
		while (temp != null) {
			if (temp == myTail) {
				temp.myNext = l.myHead;
				myTail = l.myTail;
				break;
			}
			temp = temp.myNext;
		}
	}
	
	public void remove(Object arg) {
		for (ListNode p = myHead; p != null; p = p.myNext) {
			if (p.myItem.equals(arg)) {
				if (p == myHead) {
					myHead = myHead.myNext;
					mySize--;
				} else {
					if (p.myNext == null) {
						myTail = p.myNext;
					}
				}
			} else if (p.myNext == null) {
				break;
			} else if (p.myNext.myItem.equals(arg)) {
				p.myNext = p.myNext.myNext;
			}
		}
	}
	
	public void doubleInPlace() {
	    for (ListNode p = myHead; p != null; p = p.myNext.myNext) {
	        ListNode dup = new ListNode(p.myItem, p.myNext);
	        p.myNext = dup;
	    }
	}
	
	public void reverse() {
//		for (int i = 0; i < mySize; i++) {
//			reverseHelper(myHead, sooFar);
//			myHead = myHead.myNext;
//		}
//		myHead = sooFar;
		myHead = reverseHelper(myHead);
	}

	public static void reverseHelper(ListNode l, ListNode soFar) {
	    if (soFar == null) {
	    	soFar = new ListNode(l.myItem, null);
	    	l = l.myNext;
	    } else {
	    	ListNode g = soFar;
	    	soFar = new ListNode(l.myItem, g);
	    	l = l.myNext;
	    }
	    sooFar = soFar;
	}
	
	private static ListNode reverseHelper(ListNode head) {
	    ListNode p, soFar;
	    for (p = head, soFar = null; p != null;) {
	        ListNode temp = p;
	        if (soFar == null) {
		    	soFar = new ListNode(temp.myItem, null);
		    	p = p.myNext;
		    } else {
		    	ListNode g = soFar;
		    	soFar = new ListNode(temp.myItem, g);
		    	p = p.myNext;
		    }
	    }
	    return soFar;
	}

}
