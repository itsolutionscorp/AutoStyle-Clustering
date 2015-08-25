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
	public Iterator iterator() {
	    return new ElementIterator();
	}
	public boolean isOk(){
		return this.correctSizeHelper(mySize) && this.itemObjectsHelper() && this.headTailCheckHelper();
	}
	public boolean correctSizeHelper(int sizeCheck){
		List tester = this;
		if (tester.myHead == null){
			if (sizeCheck == 0){
				return true;
			}return false;
		}else if (tester.myHead.myNext == null){
			if (sizeCheck == 1){
				return true;
			}return false;
		}
		tester.myHead = myHead.myNext;
		return tester.correctSizeHelper(sizeCheck - 1);
	}
	public boolean itemObjectsHelper(){
		if (myHead == null){
			return true;
		}else{
			List tester = this;
			if (tester.myHead.myItem == null){
				return false;
			}
			tester.myHead = myHead.myNext;
			return tester.itemObjectsHelper();
		}
	}
	public boolean headTailCheckHelper(){
		List tester = this;
		if (tester.myHead == null && tester.myTail == null){
			return true;
		}else if (tester.size() > 1){
			int counter = 0;
			while (counter < this.size()){
				tester.myHead = tester.myHead.myNext;
			}
		}
		return tester.myHead == tester.myTail;
	}
	public void remove(Object x) {
		if (myHead == null) {
			
		} else if (myHead == myTail){
			if (myHead.myItem.equals(x)) {
				myHead = null;
				myTail = null;
			}
		} else {
			ListNode pointer1 = myHead;
			ListNode pointer2 = myHead.myNext;
			while (pointer1.myItem.equals(x)) {
				if (pointer2.myNext == null) {
					if (pointer2.myItem.equals(x)) {
						myHead = null;
						myTail = null;
					} else {
						myHead = pointer2;
						myTail = pointer2;
					}
				} else {
					myHead = pointer2;
					pointer1.myNext = null;
					pointer1 = pointer2;
					pointer2 = pointer2.myNext;
				}
			}
			if (myHead == null) {
				
			} else if (myHead == myTail) {
				
			} else {
				while (pointer2.myNext != null) {
					if (pointer2.myItem.equals(x)) {
						pointer1.myNext = pointer2.myNext;
						pointer2.myNext = null;
						pointer2 = pointer1.myNext;
					} else {
						pointer1 = pointer2;
						pointer2 = pointer2.myNext;
					}
				}
				if (pointer2.myItem.equals(x)) {
					myTail = pointer1;
					pointer1.myNext = null;
				} else {
					
				}
				
			}
		} 	
	}
	public void doubleInPlace() {

	    for (ListNode p = myHead; p != null; p = p.myNext) {
	        p.myNext = new ListNode(p.myItem, p.myNext);
	    }
	    if (myHead == null && myTail == null){
	    	
	    }else{
	    	myTail = myTail.myNext;
	    }
	}
	public void reverse() {
		ListNode soFar = null;
		ListNode.reverseHelper(this.myHead, soFar);
	}
	public void reverseIterative(){
		ListNode.reverseHelper(this.myHead);
	}



	public class ElementIterator implements Iterator {

	    ListNode currentNode;

	    public ElementIterator() {
	        currentNode = myHead;
	    }

	    public boolean hasNext() {
	        if (currentNode == null || currentNode.myNext == null){
	        	return false;
	        }
	    	return true;
	    }

	    public Object next() {
	        Object rtnValue = currentNode.myItem;
	        currentNode = currentNode.myNext;
	    	return rtnValue;
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
		        p = p.myNext;
		        temp.myNext = soFar;
		        soFar = temp;
		    }
		    return soFar;
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
		if (myHead == null){
			this.myHead = new ListNode(obj);
			this.myTail = this.myHead;
		}else{
			this.myHead = new ListNode (obj, this.myHead);
		}
		mySize++;
	}

	public boolean equals (Object obj) {
		if (this.size() > ((List)obj).size() || this.size() < ((List)obj).size()) {
			return false;
		}
		for (int counter = 0; counter < this.size(); counter++){
			if (this.get(counter) != ((List)obj).get(counter)){
				return false;
			}
		}
		return true;
	}

	public void add (Object x) {
		if (this.myHead == null){
			this.myHead = new ListNode(x);
			this.myTail = myHead;
		}else if (myHead == myTail) {
			this.myHead.myNext = new ListNode(x);
			this.myTail = myHead.myNext;
		}else{
			this.myTail.myNext = new ListNode(x);
			this.myTail = this.myTail.myNext;
		}
		this.mySize++;
	}

	public void appendInPlace (List l) {
		if (l.size() == 0){
			
		}else{
			if (this.myHead == null){
				this.myHead = l.myHead;
				this.myTail = l.myTail;
				
			}else if (myHead == myTail){
				this.myHead.myNext = l.myHead;
				this.myTail = l.myTail;
			}
			else{
				myTail.myNext = l.myHead;
				myTail = l.myTail;
			}
		}
		mySize += l.mySize;
		
	}

}
