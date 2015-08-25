import java.util.*;
public class List implements Iterable {

	private ListNode myHead;
	private ListNode myTail;
	private int mySize;

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
		int rtn = 0;
		for (ListNode p = myHead; p != null; p = p.myNext) {
			rtn++;
		}
		mySize = rtn;
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
	}

	public boolean equals (Object obj) {
		// TODO Your code here 
		if (obj instanceof List) {			
			if (this.size() == ((List) obj).size()) {
				if (isEmpty() && ((List)obj).isEmpty()) {
					return true;
				} else {
					for (ListNode p = myHead, o = ((List) obj).myHead; p!=null; p=p.myNext, o=o.myNext) {
						if (p.myItem != o.myItem) {
							return false;
						}
					}
					return true;
				}
			}
		}
		return false;
	}

	public void add (Object x) {
		if (this.isEmpty()) {
			this.myHead = new ListNode(x);
			this.myTail = this.myHead;
		} else {
			//ListNode p = myHead;
			//for (; p.myNext != null; p = p.myNext) {
				//continue;
			//}
			this.myTail.myNext = new ListNode(x);
			this.myTail = this.myTail.myNext;
			this.mySize ++;
		}
	}

	public void appendInPlace (List l) {
		// TODO Your code here 
		if (this.isEmpty() && !l.isEmpty()) {
			this.myHead = l.myHead;
		}else if (!this.isEmpty() && !l.isEmpty()){
			//ListNode p = myHead;
			//for (; p.myNext != null; p = p.myNext) {
				//continue;
			//}
			this.myTail.myNext = l.myHead;
			this.myTail = this.myTail.myNext;
			this.mySize ++ ;
			
		}
	}
	public Iterator iterator() {
	    return new ElementIterator();
	}

	public class ElementIterator implements Iterator {

	    // State variable(s) to be provided.
		ListNode current; 

	    public ElementIterator() {
	        // TODO code to be provided
	    	current = myHead;
	    }

	    public boolean hasNext() {
	    	
	        return (current != null);
	        // TODO code to be provided
	    }

	    public Object next() {

	        	Object temp = current.myItem;
	        	current = current.myNext;
	        	return temp;
	 
	    }

	    public void remove() {
	        // not used; do not implement
	    }
	}
public void isOK() throws Exception {
		int counter= 0;
		boolean itemNull = false;
		ListNode tailCheck = myHead; 
		if (myHead == null && myTail!=null){
			throw new Exception("null but head and tail point to different");}
		
		for (ListNode p = myHead; p != null; p = p.myNext){
			if (p.myItem == null){
				itemNull = true;
				tailCheck = tailCheck.myNext;
			}
			counter++;
		}
		if (counter != mySize){
			throw new Exception("mySize is not accurate");
		}
		if (itemNull){throw new Exception("items are null");}
		if (!tailCheck.equals(myTail)){ 
			throw new Exception ("tail is not correct");
		}
	}


public void remove(Object o){
	if (!isEmpty() && o!= null){
		ListNode p = myHead;
		
		while (myHead!= null && myHead.myItem == o){
			this.myHead = p.myNext;
		}
		
		for (; p.myNext != null; p= p.myNext){
			if (p.myNext.myItem == o){
				p.myNext = p.myNext.myNext;
			}
		}
		
		
	}
}
public void doubleInPlace() {

    for (ListNode p = myHead; p != null; p = p.myNext.myNext) {
        p.myNext = new ListNode(p.myItem, p.myNext);
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
    	return reverseHelper (temp , l);
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
