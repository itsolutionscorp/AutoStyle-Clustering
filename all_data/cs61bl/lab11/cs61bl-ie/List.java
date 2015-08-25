import java.util.*;

public class List {

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
		int checkSize = 0;
		boolean nonNull = true;
		boolean rightTail = false;
		if(myHead == null && myTail == null){
			if (mySize != 0){
				throw new IllegalArgumentException("wrong size");
			}
		}
		for (ListNode p = myHead; p != null; p = p.myNext){
			checkSize++;
			if(p.myItem == null){
				throw new NullPointerException();
			}
			if(p.myNext == null){
				if(p != myTail){
					throw new IllegalArgumentException("head tail mistmatch");
				}
			}
		}
		//System.out.println(checkSize+" "+mySize+" "+nonNull+" "+rightTail);
		if(checkSize != mySize){
			throw new IllegalArgumentException("wrong size");
		}
	}

	public void remove(Object out){
		while(myHead.myItem == out){
			myHead = myHead.myNext;
		}
		ListNode temp = myHead;
		for (ListNode p = myHead.myNext; p != null; p = p.myNext){
			if(p.myItem == out){
				temp.myNext = p.myNext;
			}
			else{
				temp = p;
			}
		}
	}

	public void doubleInPlace() {
		for (ListNode p = myHead; p != null; p = p.myNext) {
			ListNode next = p.myNext;
			p.myNext = new ListNode(p.myItem, next);
			p = p.myNext;
		}
	}	

	public void reverse() {
		myHead = this.reverseHelper(myHead);
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

	private static ListNode reverseHelper_(ListNode l, ListNode soFar) {
		if (l == null) {
			return soFar;
		} else {
			ListNode temp = l.myNext;
			l.myNext = soFar;
			return reverseHelper_(temp, l);
		}
	}


	public Iterator iterator() {
		return new ElementIterator();
	}

	public class ElementIterator implements Iterator {

		private ListNode currpos;
		private ListNode pointer;

		public ElementIterator() {
			this.currpos = myHead;
			this.pointer = currpos;
		}

		public boolean hasNext() {
			if (currpos != null) {
				return true;
			} else {
				return false;
			}
		}

		public Object next() {
			if (currpos == pointer) {
				currpos = currpos.myNext;
				return pointer.myItem;
			} else {
				pointer = currpos;
				currpos = currpos.myNext;
				return pointer.myItem;
			}
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
		mySize++;
		myHead = new ListNode (obj, myHead);
	}

	public boolean equals (Object obj) {
		if (this.size() == ((List) obj).size()) {
			for (int i = 0; i < this.size(); i = i +1) {
				if (this.get(i) != ((List) obj).get(i)) {
					return false;
				} 
			}
			return true;
		} 
		return false;
	}

	public void add (Object x) {
		if(myHead == null){
			myHead = new ListNode(x);
			myTail = myHead;
		} else{
			myTail.myNext = new ListNode(x);
			myTail = myTail.myNext;
		}	
		mySize++;
	}

	public void appendInPlace (List l) {
		if(this.isEmpty()){
			if(!l.isEmpty()){
				myHead = l.myHead;
				myTail = l.myTail;	
			}
		} else if(!this.isEmpty() && !l.isEmpty()){
			myTail.myNext = l.myHead;
			myTail = l.myTail;
		}
		mySize += l.size();
	}

}
