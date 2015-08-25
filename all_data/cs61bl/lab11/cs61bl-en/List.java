import java.util.*;
public class List implements Iterable{

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
	public boolean isOK() {
		int checkedSize = 0;
		ListNode counter = myHead;
		while (counter != null) {
			if (counter.myItem == null) {
				
				return false;
			}
			checkedSize++;
			counter = counter.myNext;
		}
		System.out.println(mySize);
		
		
		if (checkedSize != mySize && checkedSize != size()) {
		
			return false;
		}
		if ((myHead == null && myTail == null) || myTail.myNext == null && myHead.myItem.equals(get(0))) {
			
			return true;
		}
		return false;
	}
	public void reverse(){
		myHead = reverseHelper(myHead);
	}
	private static ListNode reverseHelper(ListNode l, ListNode soFar) {
	    if (l == null) {
	        return soFar;
	    } else {
	    	ListNode temp = l.myNext;
	    	l.myNext = soFar;
	    	return reverseHelper(temp, l);
	    }
	}
	private static ListNode reverseHelper(ListNode head){
		ListNode p, soFar;
		for (p = head, soFar = null; p != null;){
			ListNode temp = p;
			p = p.myNext;
			temp.myNext = soFar;		
			soFar = temp;			
		}
		return soFar;
		
	}
	public void remove(Object obj) {
		
		ListNode p2 = myHead;
		ListNode hold = myHead;
		boolean first = true;
		boolean holding = false;
		
		for (ListNode p = myHead; p != null; p = p.myNext){
			if (first) {
				if (p.myItem != obj) {                     //first case
					
					holding = true;
				}else{
					p2 = p2.myNext;
					myHead = myHead.myNext;
				}
				
				first = false;
			} else {
				if (p.myItem == obj) {
					p2.myNext = p.myNext;
					
				}else{
					p2 = p2.myNext;
				}
			}	
		}if (holding) {
			hold.myNext = p2;
			 
		}
	}
	public Iterator iterator() {
	    return new ElementIterator();
	}

	public class ElementIterator implements Iterator {

	    // State variable(s) to be provided.
		ListNode p;

	    public ElementIterator() {
	        p = myHead;
	    }

	    public boolean hasNext() {
	        return (p != null);
	    }

	    public Object next() {
	        Object rtn = p.myItem;
	        p = p.myNext;
	        return rtn;
	        
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

	// Return the number of items in this list ("length" in Scheme).
	public int size() {
		int rtn = 0;
		for (ListNode p = myHead; p != null; p = p.myNext) {
			rtn++;
		}
		mySize = rtn;
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
		mySize++;
		if (mySize==1){
			myTail=myHead;
		}
		
		
	}

	public boolean equals (Object obj) {
		List obj2 = (List) obj;
		ListNode p2 = (ListNode) obj2.myHead;
		if (size() != obj2.size()) {
			return false;
		}
				
		for (ListNode p = myHead; p != null; p = p.myNext) {
			if (!p.myItem.equals(p2.myItem)) {
				return false;
			} p2 = p2.myNext;
		}
		return true;
	}
	

	public void add (Object x) {
		ListNode l = new ListNode(x);
		
		
		if (this.isEmpty()) {
			myHead = l;
		} else{
	
		ListNode p = myHead;
		for (ListNode i = myHead; i.myNext != null; i = i.myNext) {
		p = p.myNext;
		}
		p.myNext = l;
		myTail = l;
		
		}	
		mySize ++;
		
	}

	public void appendInPlace (List l) {
		ListNode ln = (ListNode) l.myHead;
		if (isEmpty()) {
			myHead = ln;
		}else{
		ListNode p = myHead;	
		while (p.myNext != null) {
			p = p.myNext;	
		}
		if(ln==null){
			myTail = p;
		}else{
		p.myNext = ln;
		while (ln.myNext != null) {
			ln = ln.myNext;	
			mySize++;
		}
		myTail = ln;
		mySize++;
		}

		
		
		}}
	public void doubleInPlace() {

	    for (ListNode p = myHead; p != null; p = p.myNext) {
	        p.myNext = new ListNode(p.myItem, p.myNext);
	        p = p.myNext;
	    }
	    // TODO And maybe here as well
	}

}

	
