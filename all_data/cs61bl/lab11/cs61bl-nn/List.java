import java.util.*;

public class List implements Iterable{
	

	private ListNode myHead;
	private int mySize;
	private ListNode myTail;

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
//		int rtn = 0;
//		for (ListNode p = myHead; p != null; p = p.myNext) {
//			rtn++;
//		}
//		return rtn;
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
		if (myTail == null){
			myHead = new ListNode (obj, myHead);
			myTail = myHead;
		} else {
			myHead = new ListNode (obj, myHead);
		}
		
	}
	
	public boolean helperEquals(List a, List b){
		int k = 0;
		while (k < a.size()){
			if (a.get(k).equals(b.get(k))){
				k++;
				continue;
			} else {
				return false;
			}
		}
		return true;
	}

	public boolean equals (Object obj) {
		if (!(obj instanceof List)){
			return false;
		} else {
			List cast = (List) obj;
			if (cast.size() != size()) {
				return false;
			}
			return helperEquals(this, cast);
		}
	}

	public void add (Object x) {
		if (myTail == null){
			myHead = new ListNode(x);
			mySize++;
			myTail = myHead;
		} else {
			mySize++;
			myTail.myNext = new ListNode(x);
			myTail = myTail.myNext;
		}
	}

	public void appendInPlace (List l) {
		if (myTail == null) {
			myHead = l.myHead;
			myTail = l.myTail;
			mySize += l.size();
		} else if (l.myTail == null){
			return;
		} else {
			myTail.myNext = l.myHead;
			myTail = l.myTail;
			mySize += l.size();
		}
	}
	
	public Iterator iterator() {
	    return new ElementIterator();
	}

	public class ElementIterator implements Iterator {

	    // State variable(s) to be provided.
		private int index;

	    public ElementIterator() {
	        // TODO code to be provided
	    	index = 0;
	    }

	    public boolean hasNext() {
	        return (index < size());
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
	public boolean isOK() {
		int size = 0;
		ListNode current = myHead;
		if (myHead == null){
			return (myTail == null);
		}
		while (current != myTail){
				size++;
				current = current.myNext;
			} if (size() != size+1){
				System.out.println("test");
				return false; 
			} else if (myHead != null) {
				return (current == myTail);
		}
		for (int i = 0; i<size(); i++){
			if (get(i) == null){
				return false;
			} else {
				continue;
			}
		}
		return true;
	}
	
	public void remove(Object obj){
		for (ListNode p = myHead,q=null; p != null; p = p.myNext) {
			if(p.myItem.equals(obj)){
				q.myNext=p.myNext;
			}else{
				q=p;
			}
			
		}
	}
	/**
	 * to remove an element from List at index 
	 * 
	 * @param index
	 */
	public void removeIndex(int index){
		ListNode p =myHead,q=null;
		if(index==0){
			myHead=p.myNext;
			mySize--;
		}else{
			for(int i=0;i<index;i++){
				q=p;
				p=p.myNext;
			}
			q.myNext=p.myNext;
			mySize--;
		}
		
		
	}
	
	public void doubleInPlace() {

	    for (ListNode p = myHead; p != null; p = p.myNext.myNext) {
	        // TODO Your code here
	    	p.myNext=new ListNode(p.myItem,p.myNext);
	    }
	    // TODO And maybe here as well
	}
	
	public void reverse(){
		 if(mySize==0)
			 throw new  NoSuchElementException("can not reverse an empty list!");		 
		
		 myTail=myHead;
		 myHead=reverseHelper(myHead);
	}
	
	//recursive one
	public static ListNode reverseHelper(ListNode l, ListNode soFar) {
	    // ...
		if(l == null){
			return soFar;
		}else{
			ListNode temp= l.myNext;
			l.myNext=soFar;
			return reverseHelper(temp,l);
		}
	
		
	}
	//iterative one
	public static ListNode reverseHelper(ListNode head){
		ListNode p, soFar;
	    // p plays the role of l in the previous version.
	    for (p = head, soFar = null; p != null;) {
	        ListNode temp = p.myNext;
	        p.myNext=soFar;
	        soFar=p;
	        p=temp;
	        
	    }
	    return soFar;
	}	
	}

