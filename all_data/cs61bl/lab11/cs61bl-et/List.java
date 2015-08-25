import java.util.*;

public class List implements Iterable{
	public Iterator iterator() {
		return new ElementIterator();
	}
	private class ElementIterator implements Iterator{
		// State variable(s) to be provided.
		ListNode current1;
		public ElementIterator() {
			// TODO code to be provided
			current1 = myHead;
		}

		public boolean hasNext() {
			if (current1==null){
				return false;
			}
			return current1.myNext!=null;
		}

		public Object next() {
			// TODO code to be provided
			Object c = current1.myItem;
			current1 = current1.myNext;
			return c;
		}

		public void remove() {
			// not used; do not implement
		}
	}

	private int mySize;
	private ListNode myHead;
	private ListNode myTail;

	public List() {
		myHead = null;
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
		// TODO Your code here 
		ListNode r = ((List)obj).myHead;
		ListNode p = null;
		for (p = myHead; p!=null&&r!=null;p=p.myNext,r=r.myNext){
			if (!r.myItem.equals(p.myItem)){
				return false;
			}
		}
		if (r!=null||p!=null){
			return false;
		}else{
			return true;
		}
	}

	public void add (Object x) {
		// TODO Your code here 
		if (myHead==null){
			myHead = new ListNode(x);
			myTail = myHead;
			mySize++;
			return;
		}
		myTail.myNext=new ListNode(x);
		myTail = myTail.myNext;
		mySize++;
	}

	public void appendInPlace (List l) {
		if (myHead==null){
			myHead = l.myHead;
			myTail = l.myTail;
		}else if (l.myHead==null){
		}else{
			myTail.myNext = l.myHead;
			myTail= l.myTail;
		}
		mySize=mySize+l.mySize;
	}
	public void isOK(){
		int countSize = 0;
		for (ListNode a = myHead;a!=null;a = a.myNext){
			countSize++;
		}
		if (countSize != mySize){
			throw new IllegalArgumentException("sizecounter is wrong");
		}
		for (ListNode current= myHead; current!=null; current=current.myNext){
			if(current.myItem==null){
				throw new IllegalArgumentException("item could not be null");
			}
		}
		if ((myHead!=null&&myTail!=null)||(myHead==null&&myTail==null)){
			throw new IllegalArgumentException("it is wrong");
		}
		if (myTail.myNext!=null){
			throw new IllegalArgumentException("mytail does not point to the last one");
		}
	}
	public void remove(Object a){
		ListNode current;
		ListNode b; 
		for (b=myHead;b.myItem.equals(a);b=b.myNext){
			myHead=myHead.myNext;
		}
		for (current=myHead;current!=null;current=current.myNext){
			if (a.equals(current.myNext.myItem)){
				current.myNext=current.myNext.myNext;
			}
		}
	}
	public void reverse() {
		myHead =reverseHelper(myHead,null);
		for (ListNode current = myHead; current!=null;current=current.myNext){
			myTail=current;
		}
	}
	private static ListNode reverseHelper(ListNode l, ListNode soFar) {
	    if (l == null) {
	        return soFar;
	    } else {
	    	return reverseHelper(l.myNext, new ListNode(l.myItem, soFar));
	    }
	}
}
