import java.util.Iterator;

public class List implements Iterable{

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
		myHead = new ListNode (obj, myHead);
		myTail = myHead;
		mySize++;
	}

	public boolean equals (Object obj) {
		// TODO Your code here 
		List casted = (List) obj;
		ListNode temp = myHead;
		ListNode temp2 = casted.myHead;
		while (temp!=null) {
			if (temp2==null) {
				return false;
			}
			if (temp.myItem!= temp2.myItem) {
				return false;
			} 
			temp = temp.myNext;
			temp2 = temp2.myNext;
		}
		if (temp2!=null) {
			return false;		
		}
		return true;
	}

	public void add (Object x) {
		// TODO Your code here 
		ListNode temp = new ListNode(x);
		if (myHead==null) {
			myHead = temp;
			myTail = temp;
		} else {
			myTail.myNext= temp;
			myTail = temp;
		}
		mySize++;
	}

	public void appendInPlace (List l) {
		// TODO Your code here 
		ListNode temp = myHead;
		if (temp==null) {
			myHead = l.myHead;
			myTail = myHead;
		} else {
			if (l.myHead!=null) {
				myTail.myNext = l.myHead;
				myTail = l.myTail;
			}
		}
		mySize += l.mySize;
	}
	
	public boolean isOK(){
		ListNode temp = myHead;
		int length = 0;
		if (temp==null) {
			return myTail==null;
		}
		while (temp.myNext!=null) {
			if (temp.myItem==null) {
				System.out.println("null item");
				return false;
			}
			length++;
			temp = temp.myNext;
		}
		if (temp!=myTail) {
			System.out.println("wrong tail");
			return false;
		}
		if (temp.myItem==null) {
			System.out.println("null tail");
		}
		if (length!=mySize-1) {
			System.out.println("size");
			return false;
		}
		return true;
		
	}
	
	public void remove(Object a) {
		if(myHead==null) {
			return;
		}
		while (myHead!=null&&myHead.myItem.equals(a)){
			myHead = myHead.myNext;
			mySize--;
		}
		if (myHead==null) {
			myTail = null;
			return;
		}
		ListNode temp = myHead;
		while (temp.myNext!=null) {
			if (temp.myNext.myItem.equals(a)) {
				if (temp.myNext.myNext==null) {
					myTail = temp;
					temp.myNext = null;
					mySize--;
					break;
				}
				temp.myNext = temp.myNext.myNext;
				mySize--;
			} else {
				temp = temp.myNext;
			}
			}
		myTail = temp;
	}
	public void doubleInPlace() {

	    for (ListNode p = myHead; p != null; p = p.myNext) {
	        // TODO Your code here\
	    	ListNode temp = p.myNext;
	    	ListNode toAdd = new ListNode(p.myItem);
	    	p.myNext = toAdd;
	    	p.myNext.myNext = temp;
	    	if (temp==null) {
	    		myTail = toAdd;
	    	}
	    	p = p.myNext;
	    }
	    mySize = 2*mySize;
	    // TODO And maybe here as well
	}
	public void reverse() {
		myTail = myHead;
		myHead = reverseHelper(myHead);
	}
	private static ListNode reverseHelperA(ListNode l, ListNode soFar) {
	    
		if (l == null) {
	        return soFar;
	    } else {
	        ListNode temp = l.myNext;
	        l.myNext = soFar;
	        soFar = l;
	        soFar = reverseHelperA(temp, soFar);
	        return soFar;
	    }
	}
	private static ListNode reverseHelper(ListNode head) {
		ListNode p, soFar;
		for (p=head, soFar = null; p!=null;) {
			ListNode temp = p;
			p = p.myNext;
			temp.myNext = soFar;
			soFar = temp;
		}
		return soFar;
	}

	public Iterator iterator() {
	    return new ElementIterator();
	}

	public class ElementIterator implements Iterator {

	    ListNode curr;
		// State variable(s) to be provided.

	    public ElementIterator() {
	        // TODO code to be provided   	
	    	curr = myHead;
	    }

	    public boolean hasNext() {
	        return curr!=null;
	        // TODO code to be provided
	    }

	    public Object next() {
	        Object elem = curr.myItem;
	        curr = curr.myNext;
	    	return elem;
	        // TODO code to be provided
	    }

	    public void remove() {
	        // not used; do not implement
	    }
	}
}
