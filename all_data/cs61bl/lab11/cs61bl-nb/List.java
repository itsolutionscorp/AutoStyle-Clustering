import java.util.*;
public class List implements Iterable {

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

	    private int index;// State variable(s) to be provided.

	    public ElementIterator() {
	        // TODO code to be provided
	    	index = 0;
	    	
	    }

	    public boolean hasNext() {	       
	        // TODO code to be provided
	        return (index < size());
	    }

	    public Object next() {	        
	        // TODO code to be provided
	    	Object temp = get(index);
	    	index++;
	    	return temp;
	        
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
		if(!this.isEmpty()){
			myHead = new ListNode (obj, myHead);
			mySize++;
		} else {
			myHead = new ListNode (obj, myHead);
			mySize++;
			myTail = myHead;
		}
	}

	public boolean equals (Object obj) {
		// TODO Your code here
		if(this.isEmpty()){
			if(((List)obj).isEmpty()){
				return true;
			} else {
				return false;
			}
		} else {
			if(((List)obj).isEmpty()){
				return false;
			} else {
				if(((List)obj).size() == this.size()){
					int i = 0;
					for(ListNode temp = myHead; temp != null; temp = temp.myNext){
						if(temp.myItem.equals(((List)obj).get(i))){
							i++;
							continue;
						} else {
							return false;
						}
					}
					return true;
				} else {
					return false;
				}
			}
	}
  } 

	public void add (Object x) {
		// TODO Your code here 
		ListNode p = myHead;
		if(this.isEmpty()){
			myHead = new ListNode(x,null);
			myTail = myHead;	
		} else {
			myTail.myNext = new ListNode(x,null);		
			myTail = myTail.myNext;	
		}
		mySize++;
	}

	public void appendInPlace (List l) {
		// TODO Your code here 
		ListNode p = myHead;
		if(this.isEmpty()){
			myHead = l.myHead;
			myTail = l.myTail;
		} else if(l.isEmpty()){
			return;
		} else {
			myTail.myNext = l.myHead;
			myTail = l.myTail;			
		}
		mySize += l.size();
		
	}
	
	public boolean isOk(){
		int size = 0;
		ListNode p = myHead;
		if(myHead == null && myTail == null){
			return true;
		} else {
			while(p.myNext != null){ 
				size++;
				p = p.myNext;
				if(p.myItem == null){
					return false;
				} 
			}	
			if(myTail == p  && (size+1) == size()){
				return true;
			} else {
				return false;
			}
		}		
	}
	
	public void remove(Object obj){
		if(this.isEmpty()){
			return;
		} 
		while(myHead.myItem.equals(obj)){
			myHead = myHead.myNext;
			mySize--;
			if(myHead == null){
				myTail = null;
				return;
			}
		}
		ListNode p = myHead;
		while(p.myNext != null){
			if(p.myNext.myItem.equals(obj)){
				p.myNext = p.myNext.myNext;
				mySize--;
				if(p.myNext == null){
					myTail = p;
					return;
				}
			}
			if(p.myNext == null){
				myTail = p;
				return;
			}
			p = p.myNext;
			
		}
		
	}
	
	public void doubleInPlace() {
		if(this.isEmpty()){
			return;
		}
		ListNode p = myHead;
	    while(p != null) {
	    	ListNode temp = p.myNext;
	        p.myNext = new ListNode(p.myItem, temp);
	        if(temp == null){
	        	myTail = p.myNext;
	        }
	        p = temp;
	        mySize++;
	        
	    }
	   
	}
	
	private static ListNode reverseHelper(ListNode l, ListNode soFar) {
	    if (l == null) {
	        return soFar;
	    } else {
	    	ListNode temp1 = l;
	    	ListNode temp2 = l.myNext;
	    	l.myNext = soFar;
	    	soFar = temp1;
	    	l = temp2;
	        return reverseHelper ( l , soFar  );
	    }
	}
	
	private static ListNode reverseHelper(ListNode head) {
	    ListNode p, soFar;
	    // p plays the role of l in the previous version.
	    for (p = head, soFar = null; p != null;) {
	        ListNode temp = p;
	        ListNode temp2 = p.myNext;
	        p.myNext = soFar;
	        soFar = temp;
	        p = temp2;        
	    }
	    return soFar;
	}

	public void reverse(){
		if(this.isEmpty()){
			return;
		}
		//ListNode soFar = null;
		reverseHelper(myHead);
		ListNode temp = myTail;
		myTail = myHead;
		myHead = temp;

		
		
	}
}
