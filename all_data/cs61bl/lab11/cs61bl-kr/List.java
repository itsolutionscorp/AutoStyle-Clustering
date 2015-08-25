import java.util.Iterator;

import org.junit.experimental.theories.Theories;

public class List implements Iterable{

	private ListNode myHead;
	private ListNode myTail; 
	private int mySize; 

	public List() {
		mySize = 0;
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
		ListNode p = myHead; 
		mySize = 0; 
		while (p!=null) {
			p = p.myNext;	
			mySize ++; 
		}
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
		}
		
	public boolean equals(Object obj) {
	if (((List) obj).size() != size()) {
			return false; 
		}
	if (((List) obj).isEmpty()) {
		if (isEmpty()) {
			return true; 
		}
	}
	ListNode p = myHead; 
	for (int i = 0; i < size(); i++) {
		if (!((List) obj).get(i).equals(p.myItem)) {
			return false; 
		} 
		p = p.myNext; 
		} 
	return true; 
	} 

	public void add (Object x) {
		if (isEmpty()) {
			addToFront(x); 
			return; 
		}
		ListNode p = myHead; 
		while (p.myNext!=null) {
			p = p.myNext; 
		}
		myTail = p; 
		myTail.myNext = new ListNode(x,null); 
	}			

	public void appendInPlace (List l) {
		if (isEmpty()) {
			myHead = l.myHead; 
			return; 
		}
		ListNode p = myHead; 
		while (p.myNext!=null) {
			p = p.myNext;
		}
		myTail = p;
		myTail.myNext = l.myHead;
	}
	
	public void isOk() {
		ListNode p = myHead;
		int count = 0; 
		if (myHead == null && myTail == null) {
			throw new IllegalArgumentException("Elements in list are null");
		}
		while (p.myNext!= null) {
			if(p.myItem == null) {
				throw new IllegalArgumentException("Null element within the list"); 
			}
			p = p.myNext; 
			count ++;
		}
		if (count != size()) {
			throw new IllegalArgumentException("Cannot exceed size of list"); 
		}
	}
	
	public void remove(Object toBeRemoved) {
		if (isEmpty()) {
			throw new IllegalArgumentException ("List is empty"); 
		}
		ListNode current = myHead; 
		if (myHead.myItem == toBeRemoved) {
			 myHead = myHead.myNext; 
		}
		while (current.myNext!=null) {
			if (current.myNext.myItem == toBeRemoved){
				ListNode temp = current.myNext;
				current.myNext = temp.myNext; 
				return; 
			}
			current = current.myNext;
			}
	}
	
	public void doubleInPlace() {
	    for (ListNode p = myHead; p != null; p = p.myNext) {
	    	ListNode temp = p.myNext;
	    	ListNode copy = new ListNode(p.myItem, temp); 
	    	p.myNext = copy; 
	    	p = copy; 
	    }   
	}
	
	public void reverse() {
	    if (isEmpty()) {
	    	throw new IllegalArgumentException("Empty list");
	    }
	    if (size() == 1) {
	    	return; 
	    }
	    
	    List temp = new List(); 
	  while (myHead !=null) {  
		  ListNode copy = reverseHelper(myHead); 
		  temp.add(copy.myItem);  
		  remove(copy.myItem); 
	    }
	   myHead = temp.myHead; 
	}

	private static ListNode reverseHelper(ListNode head) {
	    ListNode p, soFar;
	    // p plays the role of l in the previous version.
	    for (p = head, soFar = null; p != null;) {
	        ListNode temp = p;
	        p = p.myNext;
	        soFar = temp;  
	    }
	    return soFar;
	}

	public Iterator iterator() {
    return new ElementIterator();
}

	public class ElementIterator implements Iterator {
		int index; 

    public ElementIterator() {
        index = 0; 
    }

    public boolean hasNext() {
    	return index < size(); 
    }

    public Object next() {
        return get(index++); 
       
    }

    public void remove() {
        // not used; do not implement
    }
}
}