import java.util.*;

public class List implements Iterable{

	private ListNode myHead;
	private int mySize;
	private ListNode myTail;

	public List() {
		myHead = null;
		mySize = 0;
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
		if (size() != ((List) obj).size()) {
			return false;
		}
		ListNode p = myHead;
		ListNode q = ((List) obj).myHead;
		while (p != null) {
			if (!p.myItem.equals(q.myItem)) {
				return false;
			}
			p = p.myNext;
			q = q.myNext;
		}
		return true;
	}

	public void add (Object x) {
		// TODO Your code here 
		if (this.isEmpty()) {
			myHead = new ListNode(x);
			myTail = myHead;
		} else {
			myTail.myNext = new ListNode(x);
			myTail = myTail.myNext;
			
			}
		mySize++;
		}

	public void appendInPlace (List l) {
		// TODO Your code here 
		if(l.isEmpty()){
			
		}else if (this.myHead == null){
		
			myHead = l.myHead;
			myTail = l.myTail;
		}
		else{
			myTail.myNext = l.myHead;
			myTail = l.myTail;
		}
		mySize += l.size(); 
		
	}
	public Iterator iterator() {
	    return new ElementIterator();
	}
	public boolean isOK(){
		int count = 0;
		for (ListNode p = myHead; p != null; p = p.myNext){
			count++;
			if(p.myItem == null){
				throw new IllegalStateException("items cannot be equal to null");
				
			}
			if(p.myNext == null && !(p == myTail)){
				throw new IllegalStateException("head and tail do not refer to the same list");
				
			}
		}
		if (mySize != count){
			throw new IllegalStateException("mySize does not equal the number of nodes in the list");
			
		}
		
		
		
		return true;
		
	}
	
	public void remove(Object o){
		ListNode prev = myHead;
		for(ListNode temp = myHead; temp != null;){
			if (temp.myItem.equals(o)){
				if(temp == myHead){
					myHead = myHead.myNext;
				}else{
				prev.myNext = temp.myNext;
				}
			}else{
				prev = temp;
			}
			temp = temp.myNext;
		}
	}
	
	public void doubleInPlace() {

	    for (ListNode p = myHead; p != null; ) {
	        p.myNext = new ListNode(p.myItem, p.myNext);
	        p = p.myNext.myNext;
	    }
	    // TODO And maybe here as well
	}
	
	public void reverse() {
	    myHead = reverseHelper(this.myHead);
	}

	private static ListNode reverseHelper(ListNode l, ListNode soFar) {
	    if (l == null) {
	        return soFar;
	    } else {
	    	ListNode temp = l.myNext;
	    	l.myNext = soFar;
	    	
	    	return reverseHelper ( temp , l);
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

	public class ElementIterator implements Iterator {

	    // State variable(s) to be provided.
		ListNode tempList;
	    public ElementIterator() {
	        // TODO code to be provided
	    	tempList = myHead;
	    	
	    }

	    public boolean hasNext() {
	    	if(tempList == null){
	    		return false;
	    	}
	        return tempList.myNext != null;
	        // TODO code to be provided
	    }

	    public Object next() {
	    	if (tempList == null){
	    		return null;
	    	}
	    	else{
	    		Object temp = tempList.myItem;
	    		tempList = tempList.myNext;
	    		return temp;
	    	}
	        
	        // TODO code to be provided
	    }

	    public void remove() {
	        // not used; do not implement
	    }
	}

}