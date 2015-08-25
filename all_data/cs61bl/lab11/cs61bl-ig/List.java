import java.util.*;

public class List implements Iterable {

	private ListNode myHead, myTail;
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
		ListNode q=((List)obj).myHead;
		ListNode p = myHead;

		for (; p != null && q!=null; p = p.myNext){
			if (!p.myItem.equals(q.myItem)){
				return false;
			} 
			q=q.myNext;
		}
		
		if (q==null && p==null){

			//System.out.println("q == null, p == null" + q + " " + p);
			return true;
		}
		return false;
	}
	


	public void remove(Object o) {
		ListNode c = myHead;
		if(c == null) return;
		if(c.myItem.equals(o)) {
			mySize--;
			myHead = null;
			myTail = null;
		}
		helperRemove(c, o);
		ListNode a = myHead;
		if(a == null) {
			myHead = null;
			myTail = null;
			return;
		}
		for(a = myHead; a.myNext != null; a = a.myNext) {}
		myTail = a;
		
 	}
	
	public void helperRemove(ListNode l, Object o) {
		if(l == null) return;
		if(l.myNext == null) return;

		
		if(l.myNext.myItem.equals(o)) {
			l.myNext = l.myNext.myNext;
			mySize--;
			helperRemove(l, o);
		} else {
			if(myHead == null) {
				myHead = l.myNext;
			}

			helperRemove(l.myNext, o);
			
		}
	}


	public void add (Object x) {
		ListNode p=new ListNode(x);
		ListNode current = myHead;
		mySize++;
		myTail = p;
		if (current==null) {
			myHead=p;
			return;
		}
		
		while( current.myNext!=null){
			//System.out.println(current.myNext);
			current=current.myNext;
		}
		current.myNext=p;
		
		
	}
	
	

	public void reverse() {
		ListNode reversed = reverseHelper(myHead, null);
		myHead = reversed;
		if(reversed != null) {
			while(reversed.myNext != null) {
				reversed = reversed.myNext;
			}
			myTail = reversed;
		}

	}
	
	private static ListNode reverseHelper(ListNode head) {
	    ListNode p, soFar;
	    // p plays the role of l in the previous version.
	    for (p = head, soFar = null; p != null;) {
	        ListNode temp = p.myNext;
	        if(soFar == null) {
	        	soFar = p;
	        	soFar.myNext = null;
	        } else {
	        	p.myNext = soFar;
	        	soFar = p;
	        }
	        p = temp;
	            
	    }
	    return soFar;
	}
	
	
	private static ListNode reverseHelper(ListNode l, ListNode soFar) {
	    if (l == null) {
	        return soFar;
	    } else {
	    	ListNode temp = l.myNext;
	    	ListNode next = null;
	    	if(soFar == null) {
	    		soFar = l;
	    		soFar.myNext = null;
	    		next = soFar;
	    	} else {
	    		l.myNext = soFar;
	    		next = l;
	    	}

    		return reverseHelper(temp, next);
	    }
	}
	
	public void doubleInPlace(){
		ListNode p=myHead;
		if (p==null) return;
		for (; p!=null; p=p.myNext){
			ListNode copy=new ListNode(p.myItem,p.myNext);
			p.myNext=copy;
			p=p.myNext;
			myTail=copy;
		}
		mySize *= 2;
	}

	public void appendInPlace (List l) {
		ListNode p = l.myHead;
		if(myTail == null) {
			myHead = p;
			myTail = l.myTail;
		} else {

			myTail.myNext = p;
			myTail = l.myTail;	
		}
		mySize += l.size();
	}
	
	public boolean isOK(){
		int size=0;
		ListNode p=myHead;
		ListNode prev=myHead;
		for (; p!=null; p=p.myNext){
			if (p.myItem==null){
				return false;
			}
			size++;
			prev=p;
		}
		if (mySize!=size){
			return false;
		}
		if ((myHead!=null && myTail==null)||(myHead==null && myTail!=null) || (prev!=myTail)){
			return false;
		}
		return true;
	}
	
	
	
	public Iterator iterator() {
	    return new ElementIterator();
	}

	public class ElementIterator implements Iterator {
		ListNode index;
	    // State variable(s) to be provided.

	    public ElementIterator() {
	    	index=myHead;
	    }

	    public boolean hasNext() {
	    	if (index==null){
	        return false;
	    	} 
	    return true;
	    }

	    public Object next() {
	    	Object temp=index.myItem;
	    	index=index.myNext;
	    	return temp;
	    }

	    public void remove() { }
	}

}
