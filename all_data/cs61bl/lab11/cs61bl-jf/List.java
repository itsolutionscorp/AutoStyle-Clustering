import java.util.*;

public class List implements Iterable{

	private ListNode myHead;
	private int mySize;
	private ListNode myTail;

	public List() {
		myHead = null;
	}

	public boolean isEmpty() {
		return myHead == null;
		
	}
	
	public void remove(Object args){
		ListNode firstHead = myHead;
		while(myHead.myItem.equals(args)){
			myHead = myHead.myNext;
		}
    	while(firstHead.myNext != myTail){
    		if(firstHead.myNext.myItem.equals(args)){
    			firstHead.myNext = firstHead.myNext.myNext;
    		} else {
    			firstHead = firstHead.myNext;
    		}
    	}
    	if(myTail.myItem.equals(args)){
    		myTail = firstHead;
    		myTail.myNext = null;
    	}

		
	}
	
	public void doubleInPlace() {

	    for (ListNode p = myHead; p != null; p = p.myNext) {
	        // TODO Your code here
	    	p.myNext = new ListNode(p.myItem, p.myNext);
	    	p = p.myNext;
	    	
	    }
	    // TODO And maybe here as well
	    
	}
	
	public void reverse() {
	    // ...

		myHead = reverseHelper(myHead);
	}

//	public static ListNode reverseHelper(ListNode l, ListNode soFar) {
//	    // ...
//		if(l == null){
//			return soFar;
//		}else{
//		ListNode temp = l.myNext;
//		l.myNext = soFar; 
//		soFar = l;
//		l = temp;
//		return reverseHelper(l, soFar);
//		}
//		
//	}
	
	private static ListNode reverseHelper(ListNode head) {
	    ListNode p, soFar;
	    // p plays the role of l in the previous version.
	    for (p = head, soFar = null; p != null;) {
	        ListNode temp = p;
	        p = temp.myNext;
	        temp.myNext = soFar;
	        soFar = temp;
	    }
	    return soFar;
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
		myHead= new ListNode (obj, myHead);
		if(myTail== null){
			myTail = myHead;
		}
		mySize += 1;

	}

	public boolean equals (Object obj) {
		if(this.size() != ((List)obj).size()){
			return false;
		}
		for(int i = 0; i < this.size(); i++){
			if(!this.get(i).equals(((List) obj).get(i))){
				return false;
			}
			
		}
		return true;
	}

	public void add (Object x) {
		if (myHead == null) {
			myHead = new ListNode(x, null);
			myTail = myHead;
		}else{
			ListNode next = new ListNode(x, null);
			myTail.myNext = next;
			myTail = next;
		}
		mySize += 1;
		
	}

	public void appendInPlace (List l) {	
		if(l.myHead == null ){
			return ;
		}
		if(myHead == null){
			myHead = l.myHead;
		} else { 
			 myTail.myNext = l.myHead;
		}
		myTail = l.myTail;

		mySize += l.size();
	}
	
	public Iterator iterator() {
	    return new ElementIterator();
	}

	public class ElementIterator implements Iterator {

	    // State variable(s) to be provided.
		private ListNode temp;

	    public ElementIterator() {
	    	this.temp = myHead;
	    	
	    }

	    public boolean hasNext() {
	  	   	return (temp != null);
	    }

	    public Object next() {
	    	ListNode initialTemp = temp;
	    	temp = temp.myNext;
	    	return initialTemp.myItem;
	        
	        // TODO code to be provided
	    }

	    public void remove() {
	        // not used; do not implement
	    		
	    	
	    	
	    }
	}

	
	public boolean isOK(){
		
		int counter = 0;
		for(ListNode p = myHead; p != null; p = p.myNext){
			counter++;	
			if(p.myItem == null){
				return false;
			} 
		}
		if(mySize == counter){
			return true;
		}else{
			return false;
		}
		
		
		
	}
}
