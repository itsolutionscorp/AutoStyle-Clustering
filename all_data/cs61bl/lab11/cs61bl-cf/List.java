import java.util.*;
import java.lang.IllegalStateException;

public class List implements Iterable{

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
	}

	public boolean equals (Object obj) {
		// TODO Your code here 
		List listObj;
		if (obj instanceof List == false) {
			return false;
		} else{
			listObj = (List) obj;
		}
		
		if (this.size() == 0 && (listObj).size() == 0) {
			return true;
		}
		
		if (this.size() != (listObj).size()) {
			System.out.println(this.size());
			System.out.println(listObj.size());
			return false;
		}
		
		for (int i = 0; i < this.size(); i++) {
			System.out.println("comparing " + this.get(i) + " with " + listObj.get(i));
			if ( !this.get(i).equals(listObj.get(i)) ) {
				return false;
			}			
		}
		return true;
	}

	public void add (Object x) {
		// TODO Your code here
		if (this.size() == 0) {
			myHead = new ListNode(x);
			myTail = myHead;
//			System.out.print("test");
		}
		
		 else {
			myTail.myNext = new ListNode(x); 
			myTail = myTail.myNext;
		}
		mySize++;
	}


	public void appendInPlace (List l) {
		// TODO Your code here
		if (l.size() == 0) {
			return;
		}
		
		else if (this.size() == 0) {
			this.myHead = l.myHead;
		}
		
		else {
			myTail.myNext = l.myHead;
			myTail = l.myTail;
		}
		mySize = mySize + l.mySize;
	}
	
	public Iterator iterator() {
	    return new ElementIterator();
	}

	public class ElementIterator implements Iterator {

	    ListNode currentNode;

	    public ElementIterator() {
	        currentNode = myHead;
	    }

	    public boolean hasNext() {
	        return currentNode != null;
	    }

	    public Object next() {
	    	Object temp = currentNode.myItem;
	    	currentNode = currentNode.myNext;
	        return temp;
	    }

	    public void remove() {
	        // not used; do not implement
	    }
	}
	
	public void isOK() throws IllegalStateException{
		int rtn = 0;
		for (ListNode p = myHead; p != null; p = p.myNext) {
			rtn++;
		}
		if(rtn != mySize){
			throw new IllegalStateException();
		}
		for(int i=0; i<size(); i++){
			if(this.get(i) == null){
				throw new IllegalStateException();
			}
		}
		if(myHead == null && myTail != null){
			throw new IllegalStateException();
		}
		if(myHead != null && myTail == null){
			throw new IllegalStateException();
		}
		if(myHead.myItem != get(0)){
			System.out.println("Head: " + myHead.myItem);
			throw new IllegalStateException();
		}
		if(myTail.myItem != get(size()-1)){
			System.out.println("Tail: " + myTail.myItem);
			throw new IllegalStateException();
		}
	} //remember to test later
	
	public void remove(Object obj){
		ListNode current = myHead;
		ListNode previous = null;
		while(current != null){
			if(current == myHead){
				System.out.println("in head");
				if(obj.equals(current.myItem)){
					System.out.println("remove head");
					myHead = myHead.myNext;
					mySize--;
				} else {
					previous = current;
				}
				current = current.myNext;
			} else if( current == myTail){
				if(obj.equals(current.myItem)){
					myTail = previous;
					mySize--;
				}
				current = current.myNext;
			} else {
				if(obj.equals(current.myItem)){
					previous.myNext = current.myNext;
					mySize--;
				} else {
					previous = current;
				}
				current = current.myNext;
			}
			
		}
		
	}
	
	public void doubleInPlace() {
		for (ListNode p = myHead; p != null; p = p.myNext) {
	        ListNode temp = p.myNext;
	        p.myNext = new ListNode(p.myItem, temp);
	        mySize++;
	        p = p.myNext;
	    }
	}
	
	public void recursiveReverse() {
		myTail = myHead;
	    myHead = reverseHelper(myHead, null);
	}

	private static ListNode reverseHelper(ListNode l, ListNode soFar) {
	    //System.out.println("in helper method");
		if (l == null) {
	    	//System.out.print(soFar);
	    	//soFar.toString();
			System.out.println("l is null");
	        return soFar;
	    } else {
	    	ListNode temp = l.myNext;
	    	l.myNext = soFar;
	    	return reverseHelper (temp, l);
	    }
	}
	
	public void iterativeReverse() {
		myTail = myHead;
		myHead = reverseHelper(myHead);
	}
	
	private static ListNode reverseHelper(ListNode head) {
	    ListNode p, soFar;
	    // p plays the role of l in the previous version.
	    for (p = head, soFar = null; p != null;) {
	        ListNode temp = p;
	        p = p.myNext;
	        Object tempItem = temp.myItem;
	        soFar = new ListNode(tempItem,soFar);
	    }
	    return soFar;
	}
	
	private static ListNode iterativeReverseHelper(ListNode head) {
	    ListNode p, soFar;
	    // p plays the role of l in the previous version.
	    for (p = head, soFar = null; p != null;) {
	        ListNode temp = p;
	        p.myNext = soFar;
	    }
	    return soFar;
	}
	
}
