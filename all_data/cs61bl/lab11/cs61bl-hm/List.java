import java.util.*;

public class List implements Iterable{

	private ListNode myHead;
	private int mySize;
	private ListNode myTail;

	public List() {
		myHead = null;
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
		boolean changeTail = false;
		if (myHead == null)
			changeTail = true;
		myHead = new ListNode (obj, myHead);
		if (changeTail)
			myTail = myHead;
		mySize++;
	}

	public boolean equals (Object obj) {
		// TODO Your code here
		if (obj == null)
			return false;
		
		
		ListNode obj_pointer = ((List) obj).myHead;
		ListNode pointer = myHead;
		
		
		while(true){
			if (obj_pointer == null && pointer == null)
				return true;
			else if (obj_pointer == null && pointer!= null)
				return false;
			else if (obj_pointer != null && pointer== null)
				return false;
			else if (pointer.myItem==null && obj_pointer == null)
				return true;
			else if (pointer.myItem!=null && obj_pointer == null)
				return false;
			else if (pointer.myItem==null && obj_pointer != null)
				return false;
			else if (!(pointer.myItem.equals(obj_pointer.myItem)))
				return false;
			else{
				obj_pointer = obj_pointer.myNext;
				pointer = pointer.myNext;
			}
			
		}
	}

	public void add (Object x) {
		ListNode pointer = this.myHead;
		if (pointer == null)
		{
			this.myHead = new ListNode(x, null);
			myTail = myHead;
		}
		else {
			while(pointer.myNext != null) {
				pointer = pointer.myNext;
			}
			pointer.myNext = new ListNode(x, null);
			myTail = pointer.myNext;
		}
		mySize++;
	}

	public void appendInPlace (List l) {
		// TODO Your code here
		ListNode pointer = this.myHead;
		if (pointer == null)
			this.myHead = l.myHead;
		else{
			for(pointer = this.myHead;pointer.myNext!=null;pointer = pointer.myNext);
			pointer.myNext = l.myHead;
		}
		mySize += l.mySize;
		myTail = l.myTail;
	}
	
	public void isOk(){
		if (myHead == null && myTail != null)
			throw new IllegalStateException("myTail and myHead should both be pointing to null.");
		else if (myHead == null && myTail == null && mySize != 0)
			throw new IllegalStateException("The size should be 0");
		ListNode pointer;
		int length = 0;
		ListNode check_tail = null;
		for(pointer = this.myHead; pointer != null; pointer = pointer.myNext){
			if(pointer.myItem==null)
				throw new IllegalStateException("myItem is null");
			length++;
			check_tail = pointer;
		}
		if (myTail != check_tail)
			throw new IllegalStateException("myTail is not pointing to the end of the linked list");
		if(length!=this.mySize){
			throw new IllegalStateException("Invalid mySize");
		}
			
	}
	
	public void doubleInPlace() {
		ListNode p;
		for (p = myHead; p != null; p = p.myNext) {
	        // TODO Your code here
			ListNode temp = new ListNode(p.myItem,p.myNext);
			mySize++;
			p.myNext = temp;
			p = p.myNext;
			myTail = p;
	    }
	}
	
	public void reverse() {
		myTail = myHead;
		this.myHead = reverseHelper(myHead);
	}
	

	public static ListNode reverseHelper(ListNode l, ListNode soFar) {
	    if (l == null)
	    	return soFar;
	    ListNode temp = l.myNext;
	    l.myNext = soFar;
	    return reverseHelper(temp, l);
	    
	}
	
	private static ListNode reverseHelper(ListNode head) {
	    ListNode p, soFar;
	    // p plays the role of l in the previous version.
	    for (p = head, soFar = null; p != null;) {
	        ListNode temp = p;
	        temp = temp.myNext;
	        p.myNext = soFar;
	        soFar = p;
	        p = temp;
	    }
	    return soFar;
	}
	public void remove(Object o){
		while(myHead.myItem.equals(o)) {
			myHead = myHead.myNext;
			mySize--;
		}
		ListNode pointer = this.myHead;
		ListNode successor = pointer.myNext;
		while(successor != null){
			if (successor.myItem.equals(o)){
				pointer.myNext = successor.myNext;
				successor = successor.myNext;
				mySize--;
			}
			else {
				successor = successor.myNext;
				pointer = pointer.myNext;
			}
		}
		myTail = pointer;
	}
	
	public Iterator iterator() {
	    return new ElementIterator();
	}

	public class ElementIterator implements Iterator {

	    ListNode pointer;

	    public ElementIterator() {
	        pointer = myHead;
	    }

	    public boolean hasNext() {
	    	return pointer != null;
	    }

	    public Object next() {
	        ListNode temp = pointer;
	        pointer = pointer.myNext;
	    	return temp.myItem;
	    }

	    public void remove() {
	        // not used; do not implement
	    }
	}

}
