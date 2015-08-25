import java.util.Iterator;

public class List implements Iterable {

	private ListNode myHead;
	private int mySize;
	private ListNode myTail;

	public List() {
		myHead = null;
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
		int rtn = 0;
		for (ListNode p = myHead; p != null; p = p.myNext) {
			rtn++;
		}
		this.mySize = rtn;
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
		this.mySize++;
		myHead = new ListNode (obj, myHead);
	}

	public boolean equals (Object obj) {
		if (this.myHead == null && ((List) obj).myHead == null)
			return true;
		if (this.myHead == null || ((List) obj).myHead == null)
			return false;
		if (obj instanceof List) {
			if (this.size() == ((List) obj).size()) {
				for (int i = 0; i < size(); i++) {
					if (! this.get(i).equals(((List)obj).get(i)))
						return false;
				}
				return true;
			}
		}
		return false;
	}

	public void add (Object x) {
		if(this.myHead==null){
			this.myHead = new ListNode(x);
			this.mySize++;
			this.myTail = this.myHead;
		}
		else{
			// this.myHead.add(x);
		ListNode listnode = this.myHead;
		while (listnode.myNext != null) {
			listnode = listnode.myNext;
		}
		listnode.myNext = new ListNode(x);
		this.myTail = listnode.myNext;
		this.mySize++;
		}
	}

	public void appendInPlace (List l) {
		if (this.myHead == null){
			this.myHead = l.myHead;
			this.mySize+=l.mySize;
		}
		else {
			ListNode listnode = this.myHead;
			while (listnode.myNext != null) {
				listnode = listnode.myNext;
			}
			listnode.myNext = l.myHead;
			this.mySize+=l.mySize;
			this.myTail = l.myTail;
		}
	}
	
	public void remove(Object x) {
		ListNode curr = myHead;
		
		for (int i = 1; i < this.size(); i++) {
			if (curr.myNext == null) {
				this.myTail = curr;
				break;
			}
			if (curr.myNext.myItem.equals(x)) {
				curr.myNext = curr.myNext.myNext;
				mySize--;
				i--;
				
			} else 
				curr = curr.myNext;
		}
		
		if(myHead.myItem.equals(x)) {
			mySize--;
			myHead = myHead.myNext;
		}
	}
	
	public void doubleInPlace() {

	    for (ListNode p = myHead; p != null; p = p.myNext) {
	        ListNode temp = p.myNext;
	        p.myNext = new ListNode(p.myItem, temp);
	        p = p.myNext;
	        myTail = p;
	    }
	    mySize = mySize * 2;

	}
	
	public void reverse() {
		this.myHead = reverseHelper(this.myHead);
	}
	
	public static ListNode reverseHelper(ListNode head) {
//		if (l == null) {
//	        return soFar;
//	    } else {
//	    	ListNode temp = l.myNext;
//	    	l.myNext = soFar;
//	    	return reverseHelper ( temp , l);
//	    }
		
		ListNode p, soFar;
	    // p plays the role of l in the previous version.
	    for (p = head, soFar = null; p != null;) {
	        ListNode temp = p;
	        p = p.myNext;
	        soFar = new ListNode(temp.myItem, soFar);
	        
	    }
	    return soFar;
	}

	public Iterator iterator() {
	    return new ElementIterator();
	}

	public class ElementIterator implements Iterator {
		
		ListNode me;
		
	    public ElementIterator() {
	    	me = myHead;
	    }

	    public boolean hasNext() {
	    	return (me != null);
	    }

	    public Object next() {
	    	Object item = me.myItem;
	    	me = me.myNext;
	        return item;
	        // TODO code to be provided
	    }

	    public void remove() {
	        // not used; do not implement
	    }
	}
	
	public void isOK() throws IllegalStateException {
		if (myHead == null) {
			if (myTail != null) {
				throw new IllegalStateException("myHead is pointing to null but myTail is not.");
			}
		} else if (myHead != null) {
			int count = 1;
			ListNode last = this.myHead;
			for (; last.myNext != null; last=last.myNext) {
				if(last.myItem == null) {
					throw new IllegalStateException("One of the nodes has a null for its item.");
				}
				count++;
			}
			if (last != myTail) 
				throw new IllegalStateException("myTail is not pointing to the last node.");
			if (count != this.size())
				throw new IllegalStateException("List has the wrong size.");
		} 
	}
}
