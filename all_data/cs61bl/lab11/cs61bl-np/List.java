import java.util.*;

public class List implements Iterable{

	private ListNode myHead;
	private ListNode myTail;
	private int mySize;
	
	
	public List() {
		myHead = null;
		myTail = null;
		mySize = 0;
	}

	public boolean isEmpty() {
		return myHead == null && myTail == null;
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
		/*int rtn = 0;
		for (ListNode p = myHead; p != null; p = p.myNext) {
			rtn++;
		}
		return rtn;*/
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
	public ListNode getNode (int pos) {
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
				return p;
			}
			k++;
		}
		return null;
	}
	public void addToFront (Object obj) {
		myHead = new ListNode (obj, myHead);
		mySize ++;
	}
	public void doubleInPlace() {
	    for (ListNode p = myHead; p != null; p = p.myNext) {
	        ListNode dub  =  new ListNode(p.myItem, p.myNext);
	        p.myNext = dub;
	        p = p.myNext;
	    }
	}
	public boolean equals (Object obj) {
		if (obj instanceof List){
			List c = this;
			List d = (List) obj;
			ListNode a = this.myHead;
			ListNode b = ((List) obj).myHead;
			if (a == null && b ==null){
				return true;
			}
			if (a == null || b == null){
				return false;
			}
			if (c.size() != d.size()){
				return false;
			}
			while(a != null){
				if (a.myItem.equals(b.myItem)){
					if (a.myNext == null && b.myNext == null){
						return true;
					}
					else{
						a = a.myNext;
						b = b.myNext;
					}
				}
				if (!a.myItem.equals(b.myItem)){
					return false;
				}
			}
			return false;
		}
		return false;
	}

	public void add (Object x) {
		ListNode a = this.myHead;
		mySize++;	
			if(a == null){
				this.myHead = new ListNode(x, null);
			}
			else{
			while(a.myNext != null){
				a = a.myNext;
			}
			a.myNext = new ListNode(x, null);
			myTail = a.myNext;
			}
	}

	public void appendInPlace (List l) {
		ListNode a = this.myHead;
		mySize++;
		if(a == null){
			this.myHead = l.myHead;
		}
		else{
		while(a.myNext != null){
			a = a.myNext;
		}
		a.myNext = l.myHead;
		}
	}
	public void isOk(){
		int rtn = 0;
		if(!(myHead == null && myTail == null) || !(this.contains(myHead)) || !(this.contains(myTail))){
			throw new IllegalArgumentException("wrong headers/tails");
		}
		for (ListNode p = myHead; p != null; p = p.myNext) {
			rtn++;
			if(p.myItem == null){
				throw new IllegalArgumentException("wrong");
			}
		}
		if (rtn!= mySize){
			throw new IllegalArgumentException("wrong");
		}	
	}
	
	
	public void remove(Object obj){
    	if (mySize ==1){
    		myHead = null;
    	}
    	else{
		if (myHead.myItem.equals(obj)){
    		myHead = myHead.myNext;
    		mySize--;
    	}
		for (int i =0; i < mySize; i++){
			if (getNode(i).myNext == null){
				System.out.println("asd");
				getNode(i - 1).myNext = null;
				myTail = getNode(i - 1);
				mySize--;
			}
			else{
				if (get(i).equals(obj)){
					getNode(i - 1).myNext = getNode( i + 1);
					mySize--;
					}	
				}
    		}
    	}
	}
	public void reverse(){
		myHead = reverseHelper(myHead);
	}
	
	private static ListNode reverseHelper(ListNode l, ListNode soFar) {
	    if (l == null) {
	        return soFar;
	    } else {	    	
	    	return reverseHelper(l.myNext, new ListNode(l.myItem, soFar));
	    	}
	}
	private static ListNode reverseHelper(ListNode head) {
	    ListNode p, soFar;
	    // p plays the role of l in the previous version.
	    for (p = head, soFar = null; p != null;) {
	        soFar = new ListNode(p.myItem, soFar);
	        p = p.myNext;
	        
	    }
	    return soFar;
	}
	
	public Iterator iterator() {
	    return new ElementIterator();
	}

	public class ElementIterator implements Iterator {

	    private ListNode ist;
	    public ElementIterator() {
	        this.ist = myHead;
	    }

	    public boolean hasNext() {
	        if (ist == null){
	        	return false;
	        }
	        else{
	        	return true; 
	        }
	    }

	    public Object next() {
	    	Object temp = ist.myItem;
	    	ist = ist.myNext;
	    	return temp;
	    }

	    public void remove() {
	    	
			}
	    }
	}