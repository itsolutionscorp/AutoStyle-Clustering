import java.util.Iterator;

public class List implements Iterable{

	private ListNode myHead;
	private ListNode myTail;
	private int mySize;

	public List() {
		myHead = null;
		myTail = null;
		mySize = 0;
	}
	
	public ListNode blah() {
		return this.myTail;
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
		return this.mySize;
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
		this.mySize++;
		if (this.myHead == null) {
			this.myTail = new ListNode(obj);
			this.myHead = this.myTail;
			return;
		}
		myHead = new ListNode (obj, myHead);
	}

	public boolean equals (Object obj) {
		int index = 0;
		if (this.size() != ((List) obj).size()) {
			return false;
		}
		if ((!(obj instanceof List)) || (this.size() != ((List) obj).size())) {
			return false;
		}
		for (ListNode p = myHead; p != null; p=p.myNext) {
			if (!p.myItem.equals(((List) obj).get(index))) {
				return false;
			}
			index++;
		}
		return true;
	}

	public void add (Object x) {
		this.mySize ++;
		if (this.myHead == null) {
			this.myTail = new ListNode(x);
			this.myHead = this.myTail;
			return;
		}
		ListNode store = new ListNode(x);
		this.myTail.myNext = store;
		this.myTail = store;
		return;
			
	}

	public void appendInPlace (List l) {
		if (l.myHead == null) {
			return;
		}
		if (this.myHead == null) {
			this.myHead = l.myHead;
			this.mySize = l.mySize;
			this.myTail = l.myTail;
			return;
		}
		this.mySize += l.mySize;
		this.myTail.myNext = l.myHead;
		this.myTail = l.myTail;

	}
	
	public void remove(Object obj) {
		if (this.myHead == null) {
			return;
		}
		for (ListNode p = this.myHead, q = this.myHead.myNext; q != null; ) {
			if (p.myItem == obj) {
				this.myHead = q;
				p = q;
				q = q.myNext;
				this.mySize --;
			} else if (q.myItem == obj) {
				q = q.myNext;
				p.myNext = q;
				this.mySize --;
				if (q == null) {
					this.myTail = p;
					return;
				}
			} else {
				p = q;
				q = q.myNext;
			}
		}
	}
	
	public boolean isOK() {
		int rtn = 0;
		
		if ((this.myHead == null || this.myTail == null) && !(this.myHead == null && this.myTail == null)) {
			System.out.println("head tail cluster fuck");
			return false;
		}
		
		for (ListNode p = this.myHead; p != null; p = p.myNext) {
			rtn++;
			if (p.myItem == null) {
				System.out.println("null item");
				return false;
			}
			if (p.myNext == null && p != this.myTail) {
				System.out.println("Tail is not last");
				System.out.println(this.myTail.myItem);
				return false;
			}
		}
		if (rtn != this.mySize) {
			System.out.println("You fucked up the size" + Integer.toString(rtn));
			System.out.println(mySize);
			return false;
		}
		return true;
	}
	
	public void doubleInPlace() {
	    for (ListNode p = myHead; p != null; p = p.myNext.myNext) {
	    	p.myNext = new ListNode(p.myItem, p.myNext);
	    	if (p.myNext.myNext == null) {
	    		this.myTail = p.myNext;
	    	}
	    }
	    this.mySize = this.mySize * 2;
	}
	
	public void reverse() {
//		ListNode soFar = null;
//		reverseHelper(this.myHead, soFar);
		reverseHelper(this.myHead);
		ListNode store = this.myHead;
		this.myHead = this.myTail;
		this.myTail = store;
		
	}

	public static void reverseHelper(ListNode l, ListNode soFar) {
	    if (l == null) {
	    	return;
	    }
	    ListNode temp = l.myNext;
		l.myNext = soFar;
	    reverseHelper(temp, l);
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
		ListNode curr;

	    public ElementIterator() {
	        // TODO code to be provided
	    	this.curr = myHead;
	    }

	    public boolean hasNext() {
	        return (curr != null);
	    }

	    public Object next() {
	        Object rtn = curr.myItem;
	        curr = curr.myNext;
	        return rtn;
	    }

	    public void remove() {
	        // not used; do not implement
	    }
	}

}
