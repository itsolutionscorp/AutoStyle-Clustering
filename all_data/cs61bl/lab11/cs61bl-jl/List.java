import java.util.*;

public class List implements Iterable {

	private ListNode myHead;
	private int mySize;
	private ListNode myTail;

	public Iterator iterator() {
	    return new ElementIterator();
	}

	public class ElementIterator implements Iterator {

	    // State variable(s) to be provided.
		private int myCount;

	    public ElementIterator() {
	        myCount = 0;
	    }

	    public boolean hasNext() {
	    	return myCount < size();
	    }

	    public Object next() {
	        Object temp = get(myCount);
	    	myCount ++;
	    	return temp;
	    }
	}
	
	public List() {
		myHead = null;
		myTail = null;
		mySize = 0;
	}

	public boolean isEmpty() {
		return myHead == null;
	}

	public boolean isOK(){
		ListNode head = myHead;
		ListNode tail = null;
		int size = 0;
		for(ListNode p = myHead; p != null; p = p.myNext){
			size ++;
			tail = p;
			if (p.myItem == null){
				return false;
			}
		}
		return (mySize == size) && (myHead == head) && (myTail == tail);
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
		mySize ++;
		if (mySize == 1){
			myTail = myHead;
		}
	}

	public boolean equals (Object obj) {
		if (this.size() != ((List) obj).size()){
			return false;
		} 
		for (int i = 0; i < this.size(); i++){
			if (this.get(i) != ((List) obj).get(i)){
				return false;
			}
		}
		return true;
	}

	public void add (Object x) {
		if (myHead == null){
			myHead = new ListNode(x);
			myTail = myHead;
		} else{
			myTail.myNext = new ListNode(x);
			myTail = myTail.myNext;
		}
		mySize ++;
	}

	public void appendInPlace (List l) {
		if (myHead == null){
			 myHead = l.myHead;
			 myTail = l.myTail;
		} 	
		else if (!l.isEmpty()){
			myTail.myNext = l.myHead;
			myTail = l.myTail;
		}
		mySize += l.size();
	}
	
	public void remove(Object o){
		if (!this.isEmpty()){
			while (myHead.myItem.equals(o)){
				myHead = myHead.myNext;
				mySize --;
			}
		ListNode p = myHead;
		
		while (p.myNext.myNext != null){
			if (p.myNext.myItem.equals(o)){
				p.myNext = p.myNext.myNext;
			} else{
				p = p.myNext;
			}
			
		}if (p.myNext.myItem.equals(o)){
				p.myNext = null;
		}
		}
	}
	
	public void doubleInPlace() {
	    for (ListNode p = myHead; p != null; p = p.myNext) {
	    	ListNode copy = new ListNode(p.myItem);
	    	ListNode temp = p.myNext;
	    	p.myNext = copy; 
	    	p.myNext.myNext = temp; 
	    	p = p.myNext; //why do i need this line? 
	    }
	}
	
	public void reverse() {
		this.myHead = reverseHelper(this.myHead, null);
	}

	public static ListNode reverseHelper(ListNode l, ListNode soFar) {
		   if (l == null) {
		        return soFar;
		    } else {
		    	ListNode temp = l.myNext;
		    	l.myNext = soFar;
		    	return reverseHelper(temp, l);
		    }
	}
	
	public void reverseIterative() {
		this.myHead = reverseHelper(this.myHead);
	}
	
	private static ListNode reverseHelper(ListNode head) {
	    ListNode p, soFar;
	    // p plays the role of l in the previous version.
	    for (p = head, soFar = null; p != null;) {
	        ListNode temp = p;
	        ListNode next = p.myNext;
	        p.myNext = soFar; 
	        if(soFar == null) {
	        	soFar = temp; 
	        } else {
	            soFar = p; 
	        }
	        p = next; 
	    }
	    return soFar;
	}

}
