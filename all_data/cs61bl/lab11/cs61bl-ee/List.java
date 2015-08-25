import java.util.*;
public class List implements Iterable{

	public ListNode myHead;
	int mySize = this.size();
	public ListNode myTail;

	public List() {
		myHead = null;
		myTail = null;
	}

	public boolean isEmpty() {
		return myHead == null;
	}
   
	public Iterator iterator() {
	    return new ElementIterator();
	}

	public class ElementIterator implements Iterator {
		int index;
		private ListNode p = myHead;

	    // State variable(s) to be provided.

	    public ElementIterator() {
	        // TODO code to be provided
	    	index = 0;
	    }

	    public boolean hasNext() {
	        // TODO code to be provided
	      return  index < mySize -1;
	    }

	    public Object next() {
	        
	        // TODO code to be provided
	      
	        	Object currentItem = p.myItem;
	        	p = p.myNext;
	        	
	        	index = index +1;
	        	return currentItem;
	    
	        
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
		mySize += 1;
	}

	public boolean equals (Object obj) {
		// TODO Your code here 
      List lst2 = (List) obj;
      ListNode lst2Head = lst2.myHead;
      if(lst2.size() != this.size()){
    	  return false;
      }
      else if(lst2.size() == 0 && this.size() == 0){
    	  return true;
      }
      else{
    	  for (ListNode p = myHead; p != null; p = p.myNext){
    		  if(p.myItem != lst2Head.myItem){
    			  return false;
    		  }
    		  else{  			  
    			  lst2Head = lst2Head.myNext;
    		  }
    		  }
    	  return true;
    	  }
      }
	

	public void add (Object x) {
		// TODO Your code here 
		if(myHead == null){
			myHead = new ListNode(x);
			myTail = myHead;
		}
		else{
			for (ListNode p = myHead; p != null; p = p.myNext){
				if (p.myNext == null){
					p.myNext = new ListNode(x);
					break;
				}
				
			}
			myTail = myTail.myNext;
		}
		mySize += 1;
		
		
	}

	public void appendInPlace (List l) {
		// TODO Your code here 

		if(myHead == null){
			myHead = l.myHead;
		}
		else{
			for (ListNode p = myHead; p != null; p = p.myNext){
				if (p.myNext == null){
					p.myNext = l.myHead;
					break;
				}
			}
		}
		mySize = mySize + l.size();
		if(l.myHead != null){
			myTail = l.myTail;
		}
	}

	public void isOK() throws IllegalStateException{
		if(this.mySize != this.size()) {
			throw new IllegalStateException("mySize is not conststent with size()");
		}
		for (ListNode p = myHead; p != null; p = p.myNext){
			if(p.myItem == null){
				throw new IllegalStateException("all myItem objects in this list are supposed to be non-null");
			}
		}
		if(myHead == null){
			if(myTail != null){
				throw new IllegalStateException("head and tail should be consistently null");
			}
		}
		if(myTail == null){
			if(myHead != null){
				throw new IllegalStateException("head and tail should be consistently null");
			}
		}
		if(myTail.myNext != null){
			throw new IllegalStateException("My tail is in wrong node");
		}
		if(this.get(0) != myHead.myItem){
			throw new IllegalStateException("My head is in wrong node");
		}
		
	}
	public void remove(Object obj){
		if(this.myHead == null){
			return;
		}
		ListNode prevP = this.myHead;
		for (ListNode p = myHead; p != null; p = p.myNext){
			if(p.myItem == obj){
				
				prevP.myNext = p.myNext;
				prevP = p.myNext;
			}
			else{
				prevP = p;
			}
		}
		
}
	public void doubleInPlace() {
		ListNode pointer = myHead;

	    for (ListNode p = myHead; p != null; p = p.myNext) {
	        // TODO Your code here
	    	pointer = pointer.myNext;
	    	p.myNext = new ListNode(p.myItem,pointer);
	    	p = p.myNext;
	    }
	    // TODO And maybe here as well
	}
	public void reverse() {
	    // ...
		ListNode l = this.myHead;
		ListNode soFar = null;
		reverseHelper(l);
	}
	
	private static ListNode reverseHelper(ListNode l, ListNode soFar) {
	    if (l == null) {
	        return soFar;
	    } else {
	    	ListNode temp = l.myNext;
	    	l.myNext = soFar;
	    	return reverseHelper (temp ,l );
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
	
}