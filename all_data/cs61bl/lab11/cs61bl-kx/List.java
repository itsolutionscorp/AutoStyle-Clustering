import java.util.*;
public class List {

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
		
		public String toString(){
			ListNode n = this;
			String s = "";
			while(n!=null){
				s+= n.myItem;
				n = n.myNext;
			}
			return s;
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
		// TODO Your code here
		if(obj instanceof List){
			List n = (List) obj;
			if(n.size()!=size()){
				return false;
			}
			ListNode head = n.myHead;
			if(myHead == null && head == null){
				return true;
			}
			for(ListNode p = myHead; p!=null; p=p.myNext){
				if(!p.myItem.equals(head.myItem)){
					return false;
				}
				head = head.myNext;
			}
			return true ;
		}
		return false;
	}

	public void add (Object x) {
		// TODO Your code here 
		ListNode n = myHead;
		mySize++;
		if(n==null){
			myHead = new ListNode(x);
			return;
		}
		while(n.myNext!=null){
			n = n.myNext;
		}
		n.myNext = new ListNode(x);
		myTail = n.myNext;
	}

	public void appendInPlace (List l) {
		// TODO Your code here 
		ListNode n = myHead;
		ListNode head = l.myHead;
		if(head==null){
			return;
		}
		while(head.myNext!=null){
			 head = head.myNext;
		 }
		mySize += l.size();
		if(n==null){
			 myHead = l.myHead;
			 myTail = head;
			 return;
		}
		while(n.myNext!=null){
			n = n.myNext;
		}
		n.myNext = l.myHead;
		myTail = head;
	}
	
	public void isOk(){
		int count = 0;
		for (ListNode p = myHead; p != null; p = p.myNext) {
			count++;
			if(p.myItem==null){
				throw new IllegalStateException("Null item");
			}
			if(myHead!=null && myTail!=null && p.myNext==null){
				if(myTail!=p){
					throw new IllegalStateException("Incorrect myTail");
				}
			}
		}
		if(count!=mySize){
			throw new IllegalStateException("Incorrect size");
		}
		
	}
	
	public void remove(Object obj){
		ListNode n = myHead;
		if(myHead==null){
			return;
		}
		while(n.myNext!=null){
			if(n.myNext.myItem.equals(obj)){
				n.myNext = n.myNext.myNext;
			}
			else{
				n = n.myNext;
			}
		}
		if(myHead.myItem.equals(obj)){
			myHead = myHead.myNext;
			return;
		}
		ListNode a = myHead;
		while(a.myNext!=null){
			a = a.myNext;
		}
		myTail = a;
	}
	
	public void doubleInPlace() {

	    for (ListNode p = myHead; p != null; p = p.myNext) {
	        // TODO Your code here
	    	p.myNext = new ListNode(p.myItem, p.myNext);
	    	p = p.myNext;
	    }
	    // TODO And maybe here as well
	    ListNode a = myHead;
	    if(a==null){
	    	return;
	    }
		while(a.myNext!=null){
			a = a.myNext;
		}
		myTail = a;
	}
	
	public void reverse() {
	    myHead = reverseHelper(this.myHead);
	    ListNode a = myHead;
	    if(a==null){
	    	return;
	    }
		while(a.myNext!=null){
			a = a.myNext;
		}
		myTail = a;
	}

	private static ListNode reverseHelper(ListNode l, ListNode soFar) {
	    if (l == null) {
	        return soFar;
	    } else {
	    	ListNode temp = l.myNext;
	    	l.myNext = soFar;
	    	return reverseHelper (temp, l);
	    }
	}
	
	private static ListNode reverseHelper(ListNode head) {
	    ListNode p, soFar;
	    // p plays the role of l in the previous version.
	    for (p = head, soFar = null; p != null;) {
	        ListNode temp = p;
	        temp = p.myNext;
	        p.myNext = soFar;
	        soFar = p;
	        p = temp;
	            
	    }
	    return soFar;
	}

	
	
	
	public Iterator iterator() {
	    return new ElementIterator();
	}
	

	public class ElementIterator implements Iterator {

	    // State variable(s) to be provided.
		private ListNode n;

	    public ElementIterator() {
	        // TODO code to be provided
	    	n = myHead;
	    }

	    public boolean hasNext() {
	        // TODO code to be provided
	    	return n != null;
	    }

	    public Object next() {
	        // TODO code to be provided
	    	Object o = n.myItem;
	    	n = n.myNext;
	    	return o;
	    }

	    public void remove() {
	        // not used; do not implement
	    }
	}

}
