import java.util.Iterator;

public class List implements Iterable {

	private ListNode myHead;
	private ListNode myTail;
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
	
	public Iterator iterator() {
	    return new ElementIterator();
	}

	public class ElementIterator implements Iterator {

	    // State variable(s) to be provided.
		ListNode a;
		
	    public ElementIterator() {
	        // TODO code to be provided
	    	a = myHead;
	    }

	    public boolean hasNext() {
	        return a != null;
	        // TODO code to be provided
	    }

	    public Object next() {
	    	ListNode k = a;
	    	a = a.myNext;
	        return k.myItem;
	        // TODO code to be provided
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
	}

	public boolean equals (Object obj) {
		// TODO Your code here 
		ListNode a, b;
		a = myHead;
		b = ((List) obj).myHead;
		while (a != null && b != null) {
			if (a.myItem == b.myItem) {
				a = a.myNext;
				b = b.myNext;
			} else {
				return false;
			}
		}
		return (a == null && b == null);
	}

	public void add (Object x) {
		// TODO Your code here 
		if (myHead == null) {
			myTail = new ListNode(x);
			myHead = myTail;
			mySize++;
		} else {
			myTail = new ListNode(x);
			ListNode a = myHead;
			while (a.myNext != null) {
			a = a.myNext;
			}
			a.myNext = myTail;
			mySize++;
		}
	}

	public void appendInPlace (List l) {
		// TODO Your code here 
		if (myHead == null) {
			myHead = l.myHead;
			myTail = l.myTail;
		} else {
			myTail = l.myTail;
			ListNode a = myHead;
			while (a.myNext != null) {
			a = a.myNext;
			}
			a.myNext = l.myHead;
			mySize = mySize + l.size();
		}
	}
	
	public void remove(Object obj) {
		if (myHead == myTail) {
			if (myHead == null) {
				return;
			} else {
			ListNode b = myHead;
			for (ListNode a = myHead.myNext; a != null; a = a.myNext) {
				if (b.myItem == obj) {
				myHead = myHead.myNext;
				mySize--;
				} else {
					if (a.myItem == obj) {
					b.myNext = a.myNext;
					mySize--;
				} else {
					b = b.myNext;
				}
			}
			}
			ListNode p;
			for (p = myHead; p.myNext != null;) {
				p = p.myNext;
			}
			myTail = p;
		}
		}
	}
	
	public void doubleInPlace() {

	    for (ListNode p = myHead; p != null; p = p.myNext) {
	        // TODO Your code here
	    	p.myNext = new ListNode(p.myItem, p.myNext);
	    	p = p.myNext;
	    	mySize++;
	    }
	    // TODO And maybe here as well
	    myTail = myTail.myNext;
	}
	
	public void reverse() {
		myHead = reverseHelper(myHead); //reverseHelper(myHead,null);
		if (myHead != null) {
			ListNode p;
			for (p = myHead; p.myNext != null;) {
				p = p.myNext;
			}
			myTail = p;
		}
	}
	
	private static ListNode reverseHelper(ListNode l, ListNode soFar) {
	    if (l == null) {
	        return soFar;
	    } else {
	    	ListNode temp = l.myNext;
	    	l.myNext = soFar;
	    	return reverseHelper (temp , l);
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
	
	public static void main (String[] args) {
		List l1 = new List();
		l1.add("a");
		l1.add("b");
		l1.add("c");
		l1.remove("c");
		 System.out.println(l1.toString());
	}
	
	public boolean isOk() {
		
		if (myHead != null && myTail != null) {
			for (ListNode a = myHead; a != null; a = a.myNext) {
				if (a.myItem == null) {
					throw new IllegalStateException("You cannot have a null item");
				} else {
					if (a.myNext == null) {
						if (a != myTail) {
							throw new IllegalStateException("Your tail is not the last node of your head");
						}
					}
				}
			}
		} else {
			if (myHead != null || myTail != null) {
				throw new IllegalStateException("Your tail and head are not both null");
			} else {
				int rtn = 0;
				for (ListNode p = myHead; p != null; p = p.myNext) {
					rtn++;
				}
				if (mySize != rtn) {
					throw new IllegalStateException("Size is not correct");
				}
			}
		}
		return true;
	}
}
