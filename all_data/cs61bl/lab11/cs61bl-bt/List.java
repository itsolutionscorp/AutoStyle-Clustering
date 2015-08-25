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
		return myHead == null;
	}
	
	public void isOK(){
		int numberOfNodes = 0;
		for (ListNode p = myHead; p != null; p = p.myNext){
			numberOfNodes++;
		}
		if (numberOfNodes != mySize){
			throw new IllegalStateException("mySize is not equal to the number of nodes.");
		}
		int x = 0;
		while (x < mySize){
			if (this.get(x) == null){
				throw new IllegalStateException("myItems cannot be null.");
			}
			x++;
		}
		ListNode n = myHead;
		for (ListNode p = myHead; p != null; p = p.myNext){
			n = p;
		}
		if (n != myTail){
			throw new IllegalStateException("myTail should be reference to the last node in the list whose first node is the node that myhead refers to.");
		}
	}
	
	public void remove(Object a){
		if (myHead.myItem.equals(a)){
			myHead = myHead.myNext;
		}
		for (ListNode p = myHead; p.myNext != null; p = p.myNext){
			if (p.myNext.myItem.equals(a)){
				if (p.myNext == myTail){
					myTail = p;
					p.myNext = null;
					mySize--;
					break;
				}
				p.myNext = p.myNext.myNext;
				mySize--;
			}
		}
	}
	
	public void reverse(){
		//this.myHead = reverseHelper(this.myHead, null);
		this.myHead = reverseHelper(myHead);
	}
	
	private static ListNode reverseHelper(ListNode l, ListNode soFar) {
	    if (l == null) {
	        return soFar;
	    } 
	    else {
	        ListNode temp = l.myNext;
	        l.myNext = soFar;
	        return reverseHelper(temp, l);
	    }
	}
	
	private static ListNode reverseHelper(ListNode head) {
	    ListNode p, soFar;
	    // p plays the role of l in the previous version.
	    for (p = head, soFar = null; p != null;) {
	    	ListNode temp = p;
	    	p = p.myNext;
	        if (soFar == null){
	        	soFar = temp;
	        	soFar.myNext = null;
	        }
	        else{
	        	temp.myNext = soFar;
	        	soFar = temp;
	        }        
	    }
	    return soFar;
	}
	
	public void doubleInPlace(){
		ListNode temp;
		for (ListNode p = myHead; p != null; p = p.myNext.myNext){
			temp = p.myNext;
			p.myNext = new ListNode(p.myItem, temp);
			if (p.myNext.myNext == null){
				myTail = p.myNext;
			}
		}
		mySize *= 2;
	}
	
	public Iterator iterator() {
	    return new ElementIterator();
	}

	public class ElementIterator implements Iterator {

	    // State variable(s) to be provided.
		private int index = 0;
		

	    public ElementIterator() {
	    }

	    public boolean hasNext() {
	        return index+1 < size();
	    }

	    public Object next() {
	        Object rtn = get(index);
	        index++;
	        return rtn;
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
		mySize = 0;
		for (ListNode p = myHead; p != null; p = p.myNext) {
			mySize++;
		}
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
		List lst = this;
		boolean equal = true;
		if(lst.size() != ((List)obj).size()){
			return false;
		}
		for(int i = 0; i < this.size(); i++){
			if(lst.get(i).equals(((List)obj).get(i))){
				equal = true;
			}
			else{
				equal = false;
				break;
			}
		}
		return equal;
	}

	public void add (Object x){
		for (ListNode p = myHead; p != null; p = p.myNext){
			if (p.myNext == null){
				p.myNext = new ListNode(x);
				myTail = p.myNext;
				mySize++;
				return;
			}
		}
		if (this.myHead == null){
			this.myHead = new ListNode(x);
			myTail = this.myHead;
			mySize++;
		}
	}
	
	public void appendInPlace (List l) {
		if (isEmpty()){
			this.myHead = l.myHead;
			myTail = l.myTail;
		}
		else{
			myTail.myNext = l.myHead;
		}
		mySize += l.size();
		for (ListNode p = l.myHead; p != null; p = p.myNext){
			myTail = p;
		}
	}

}
