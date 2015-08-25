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
		ListNode myCurrent;

	    public ElementIterator() {
	    	myCurrent = myHead;
	    }

	    public boolean hasNext() {
	        return myCurrent != null;
	    }

	    public Object next() {
	    	Object item = myCurrent.myItem;
	    	myCurrent = myCurrent.myNext;
	    	return item;
	    }

	    public void remove() {
	        // not used; do not implement
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
		return toString().equals(obj.toString());
	}

	public void add (Object x) {
		if(myHead == null){
			myHead = new ListNode(x);
		}
		else{
			for(ListNode p = myHead; p != null; p = p.myNext){
				if(p.myNext == null){
					p.myNext = new ListNode(x);
					myTail = p.myNext;
					break;
				}
			}
		}
		mySize++;
	}

	public void appendInPlace (List l) {
		if(myHead == null){
			myHead = l.myHead;			
		}
		else{
			for(ListNode p = myHead; p != null; p = p.myNext){
				if(p.myNext == null){
					p.myNext = l.myHead;
					break;
				}
			}
		}
		mySize += l.mySize;
		myTail = l.myTail;
	}
	public void isOK(){
		if(myHead == null && myTail == null){
			throw new IllegalStateException("myHead and myTail are both null.");
		}
		if(myHead != null && myHead == myTail){
			throw new IllegalStateException("myHead and myTail refer to same node.");
		}
		int sizeCount = 0;
		for(ListNode p = myHead; p != null; p = p.myNext){
			sizeCount++;
			if(p.myItem == null)
				throw new IllegalStateException("Null items not allowed in List.");
		}
		if(mySize != sizeCount){
			throw new IllegalStateException("Improperly stored size.");
		}
		
	}

	
	
	public void remove(Object x){
		remove(x, myHead);
	}	
	private ListNode remove(Object someObj, ListNode someNode){
		if(someNode == null) return someNode;
		someNode.myNext = remove(someObj, someNode.myNext);
		if(someNode.myItem.equals(someObj)){			
			if(someNode == myHead) myHead = myHead.myNext;
			someNode = someNode.myNext;
		}			
		return someNode;
	}
	
	
	
	public void doubleInPlace() {
	    for (ListNode p = myHead; p != null; p = p.myNext) {
	        p.myNext = new ListNode(p.myItem, p.myNext);
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
	    	return reverseHelper ( temp , l ); 
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
	

}
