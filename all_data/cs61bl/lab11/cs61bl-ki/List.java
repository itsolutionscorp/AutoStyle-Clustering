import java.util.Iterator;

@SuppressWarnings("rawtypes")
public class List implements Iterable {
	
	public Iterator iterator() {
	    return new ElementIterator();
	}

	public class ElementIterator implements Iterator {

		ListNode iterHead;
	    // State variable(s) to be provided.

	    public ElementIterator() {
	        iterHead = myHead;
	    }

	    public boolean hasNext() {
	    	if (iterHead == null){
	    		return false;
	    	}
	        return iterHead.myNext!=null;
	        
	        // TODO code to be provided
	    }

	    public Object next() {
	    	Object rtn = iterHead.myItem;
	    	iterHead = iterHead.myNext;
	        return rtn;
	        // TODO code to be provided
	    }

	    public void remove() {
	        // not used; do not implement
	    }
	}

	private ListNode myHead;
	private ListNode myTail;
	private int mySize;

	public List() {
		myHead = null;
		myTail = null;
	}

	public boolean isEmpty() {
		return myHead == null;
	}
	
	public boolean isOK(){
		int size = 0;
		for (ListNode p = myHead; p != null; p = p.myNext) {
			if (p.myItem == null){
				return false;
			}
			size++;
		}
		if (size!=mySize){
			return false;
		} 
		if (!(myHead==null && myTail==null)){
			for (ListNode p = myHead; p != null; p = p.myNext) {
				if (p.myNext == null){
					return p == myTail;
				}
			}	
		}		
		return true;
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
//		int rtn = 0;
//		for (ListNode p = myHead; p != null; p = p.myNext) {
//			rtn++;
//		}
//		return rtn;
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
		if (myTail == null){
			myTail = myHead;
		}
	}

	public boolean equals (Object obj) {
		List list = (List) obj;
		ListNode head = list.myHead;
		if (this.isEmpty() != list.isEmpty()){
			return false;
		}
		for (ListNode p = this.myHead; p != null ; p = p.myNext){
			if (head==null){
				return false;
			} else if (!p.myItem.equals(head.myItem)){
				return false;
			}
			head = head.myNext;
			if ((p.myNext==null) != (head==null)){
				return false;
			}
		}
		return true;	
	}

	public void add (Object x) {
		mySize++;
		if (myHead == null){
			myHead = new ListNode(x);
			myTail = myHead;
			return;
		}
		myTail.myNext = new ListNode(x);
		myTail = myTail.myNext;
		return;
	}
	
	public void remove (Object x){
		ListNode p = this.myHead;
		if (myHead !=null){
			while (myHead.myItem.equals (x)){
				myHead = myHead.myNext;
				mySize--;
				if (myHead == null){
					myTail = null;
					return;
				}
			}			
			while (p.myNext!=null){
				if (p.myNext.myItem.equals(x)){
					p.myNext = p.myNext.myNext;
					if (p.myNext == null){
						myTail = p;
					}
					mySize--;
				} else {
					p=p.myNext;
				}
			}
		}
	}

	public void appendInPlace (List l) {
		mySize += l.size();
		if (isEmpty()){
			myHead = l.myHead;
			myTail = myHead;
		} else {
			if (l.isEmpty()){
				return;
			} else {
				myTail.myNext = l.myHead;
				myTail = l.myTail;				
			}
//			ListNode head = myHead;
//			while (head.myNext != null){
//				head = head.myNext;
//			}
//			head.myNext = l.myHead;
		}
	}
	
	public void doubleInPlace() {

	    for (ListNode p = myHead; p != null; p = p.myNext) {
	    	ListNode tempNext = p.myNext;
	    	p.myNext = new ListNode(p.myItem, tempNext);
	    	p = p.myNext;
	    }
	}
	
	public void reverse(){
		List list = new List();
		myHead = reverseHelper(myHead);
	}
	
	public static ListNode reverseHelper(ListNode head){
		ListNode p, soFar;
		for (p = head, soFar = null; p !=null;) {
			ListNode pRest = p.myNext;
			p.myNext = null;
			if (soFar == null){
				soFar = p;
			} else {
				ListNode oldSoFar = soFar;
				soFar = p;
				soFar.myNext = oldSoFar;
			}
			p = pRest;
		}
		return soFar;
	}
		
	
//	public static ListNode reverseHelper(ListNode l, ListNode soFar){
//		if (l==null) {
//			return soFar;
//		} else {
//			if (soFar == null){
//				ListNode rest = l.myNext;
//				l.myNext = null;
//				soFar = l;				
//				l = rest;				
//				return reverseHelper(l, soFar);
//			} else {
//				ListNode oldSoFarHead = soFar;
//				ListNode rest = l.myNext;
//				l.myNext = null;
//				soFar = l;
//				soFar.myNext = oldSoFarHead;
//				l = rest;
//				return reverseHelper(l, soFar);
//			}	
//		}
//	}
	
	public String getTail(){
		return myTail.toString();
	}

}
