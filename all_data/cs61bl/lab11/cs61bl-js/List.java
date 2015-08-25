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
		List x = (List)obj;
		ListNode q = x.myHead;
		for (ListNode p = this.myHead; p != null; p = p.myNext) {
			if(q!=null){
				if (!q.myItem.equals(p.myItem)) {
					return false;
				}
			}else{
				return false;
			}
			q = q.myNext;
		}
		if(q!=null){
			return false; 
		}
		return true;
	}

	public void add (Object x) {
		ListNode p = myTail;
		p.myNext = new ListNode(x);
		myTail = p.myNext;
		mySize++;
	}

	public void appendInPlace (List l) {
		ListNode p = myTail;
		ListNode q = l.myHead;
		if(q==null||q.myItem==null){
			return;
		}
		while(q.myItem!=null){
			p.myNext = (ListNode)q.myItem;
			p = p.myNext;
			q = q.myNext;
			mySize++;
		}
	}

	public Iterator iterator(){
		return new ElementIterator();
	}

	public class ElementIterator implements Iterator {
		private ListNode head;
	    // State variable(s) to be provided.

	    public ElementIterator() {
	        head = myHead;
	    }

	    public boolean hasNext() {
	        return head.myNext==null;
	    }

	    public Object next() {
	    	head = head.myNext;
	        return head.myItem;
	    }

	    public void remove() {
	        // not used; do not implement
	    }
	}

	public boolean isOk(){
		int counter = 0;
		for (ListNode p = myHead; p != null; p = p.myNext) {
			counter++;
		}
		if(counter!=mySize){
			return false;
		}
		for (ListNode p = myHead; p.myNext != null; p = p.myNext) {
			if(p.myItem==null){
				return false;
			}
		}
		if(myHead==null){
			if(myTail!=null){
				return false;
			}
		}
		ListNode q = myHead;
		while(q.myNext!=null){
			q = q.myNext;
		}
		if(q.myItem!=myTail){
			return false;
		}
		return true;
	}

	public void remove(Object a){
		if(this.contains(a)){
			for (ListNode p = myHead; p != null; p = p.myNext) {
				if(a.equals(p.myItem)){
					while(a.equals(p.myItem)){
						p = p.myNext;
					}
				}
			}
		}
	}

	public void doubleInPlace() {
	    for (ListNode p = myHead; p != null; p = p.myNext.myNext) {
	        p.myNext = new ListNode(p.myItem, p.myNext);
	    }
	}

	public void reverse(){
		myHead = reverseHelper(myHead, null);
	}

	public static ListNode reverseHelper(ListNode l, ListNode soFar){
		if (l == null) {
        	return soFar;
    	} else {
    		ListNode temp = l.myNext;
    		Object x = l.myItem;
			l.myNext = soFar;
			return reverseHelper (temp, new ListNode(x, soFar));
		}
	}

	private static ListNode reverseHelper(ListNode head){
		ListNode p, soFar;
    	// p plays the role of l in the previous version.
    	for (p = head, soFar = null; p != null;) {
        	ListNode temp = p;
        	soFar = new ListNode(temp.myItem, soFar);
        	p = p.myNext;
    	}
    	return soFar;
	}

}