import java.util.Iterator;

public class List {

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
			//System.out.println("first list" +this.toString());
			//System.out.println("second list" +((List)obj).toString());
			if (((List) obj).isEmpty()){
				return this.isEmpty();	
			}else if(myHead ==null){
				return ((List) obj).get(0) ==null;
				
			}
			else if (((List) obj).size() == this.size()){
				ListNode newlistnode = ((List)obj).myHead;
				for (ListNode p = myHead; p != null; p = p.myNext){
					if((p.myItem.equals(newlistnode.myItem))){
						newlistnode = newlistnode.myNext;
					}else{
						return false;
					}
				}
				return true;
			}
			return false;
		}
	

	public void add (Object x) {
		if (this.isEmpty()) {
			myHead = new ListNode(x);
		} else {
			ListNode p =myHead;
			while (p.myNext != null) {
				p = p.myNext;
			}
			p.myNext = new ListNode(x);
			myTail = p.myNext;
		}
		mySize++;
	}
	
	public void appendInPlace (List l) {
		if (!l.isEmpty()) {
			if (this.isEmpty()) {
				this.myHead = l.myHead;
			} else {
				ListNode p = myHead;
				while (p.myNext != null) {
					p = p.myNext;
				}
				p.myNext = l.myHead;
			}
		}
		this.myTail = l.myTail;	
		this.mySize += l.mySize;
	}
    
	public void isOK (List l) throws Exception {
		int count = 0;
		ListNode temp = l.myHead;
		while (temp != null) {
			count++;
			temp = temp.myNext;
		}
		if (l.mySize != count) {
			throw new Exception("The value stored in mySize is not the number of nodes in the list.");
		} else {
			System.out.println("mySize is correct.");
		}
		ListNode temp2 = l.myHead;
		while (temp2 != null) {
			if (temp2.myItem == null) {
				throw new Exception("myItem object cannot be null.");
			}
			temp = temp.myNext;
		}
		if ((l.myHead == null && l.myTail == null) || (l.myTail == l.get(l.mySize - 1))) {
			System.out.println("myTail and myHead are correct.");
		} else {
			throw new Exception("myTail and myHead are incorrect.");
		}

	}
	
	public void doubleInPlace() {
		Object tempItem = new Object();
		boolean counter = true;
		if (!this.isEmpty()) {
			for (ListNode p = myHead; p != null; p = p.myNext) {
				tempItem = p.myItem;
				if (counter == true) {
					p.myNext = new ListNode(tempItem, p.myNext);
					counter = false;
				} else {
					counter = true;
				}
			}
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
    	   return reverseHelper(temp, l) ;
	    }
	}
	
	private static ListNode reverseHelper(ListNode head) {
	    ListNode p, soFar;
	    // p plays the role of l in the previous version.
	    for (p = head, soFar = null; p != null;) {
	        ListNode temp = p;
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
		private ListNode p;
		
	    public ElementIterator() {
	    	p = myHead;
	    }

	    public boolean hasNext() {
	        if (p == null || p.myNext == null) {
				return false;
	        } else {
				return true;
	        }
	    }

	    public Object next() {
	    	Object next = p.myItem;
			p = p.myNext;
			return next;
	    }

	    public void remove(Object obj) {
	    	ListNode pointer = p;
	    	while (p.myItem.equals(obj)) {
	    		p = p.myNext;
	    	}
	    	while (pointer != null && pointer.myNext != null) {
	    		if (pointer.myItem.equals(obj)){
	    			pointer.myNext = pointer.myNext.myNext;
	    		}
	    	}
	
	    }
	}
}
