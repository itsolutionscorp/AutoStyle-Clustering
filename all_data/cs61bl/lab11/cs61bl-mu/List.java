 import java.util.*;
public class List {

	private ListNode myHead;
	private ListNode myTail;
	private int mySize;

	public List() {
		myHead = null;
	}

	public boolean isEmpty() {
		return myHead == null;
	}
	
	public Iterator iterator() {
	    return new ElementIterator();
	}

	public class ElementIterator implements Iterator {

	    // State variable(s) to be provided.
		private ListNode values;
	    int index; // number of values in the sequence

	    public ElementIterator() {
	        // TODO code to be provided
	    	values = myHead;
	    	index = 0;
	    }

	    public boolean hasNext() {
	    	return (values != null);
	       
	        // TODO code to be provided
	    }

	    public Object next() {
	    	if(hasNext() == true){
	    		Object now = values.myItem;
	    		index++;
	    		values = values.myNext;
	    		return now;
	    	}else{
	    		return null;
	    	}
	        
	        // TODO code to be provided
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
//		int rtn = 0;
//		for (ListNode p = myHead; p != null; p = p.myNext) {
//			rtn++;
//		}
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
		if(mySize == 1){
			myTail = myHead;
		}
	}

	public boolean equals (Object obj) {
		// TODO Your code here 
		if(((List)obj).isEmpty() &&  this.isEmpty()){
			return true;
		}
		if(((List)obj).isEmpty()){
			return false;
		}
		if(this.isEmpty()){
			return false;
		}
		ListNode node = myHead;
		List current = (List)obj;
		ListNode next = current.myHead;
		int i = this.size();
		int k = current.size();
		if(i != k){
			return false;
		}
		
		for(int j= 0; j < k; j++){
			if(next.myItem != node.myItem){
				return false;
			}
			node = node.myNext;
			next = next.myNext;
		}
		return true;
	}

	public void add (Object x) {
		ListNode node = myHead;
		
		
		if(this.isEmpty() == true){
			myHead = new ListNode(x);
			myTail = myHead;
			mySize++;
			return;
		}
		for (ListNode p = myHead; p != null; p = p.myNext){
			node = p;
		}
		node.myNext = new ListNode(x); 
		myTail = node.myNext;
		mySize++;
		
		// TODO Your code here 
	}

	public void appendInPlace (List l) {
		// TODO Your code here 
		
		ListNode node = myHead;
		List current = l;
		ListNode next = current.myHead;
		if(this.isEmpty() && l.isEmpty()){
			return;
		}
		if(this.isEmpty()){
			myHead= next;
			mySize = l.mySize;
			return;
		}
		if(l.isEmpty()){
			return;
		}
		if(node == null){
			myHead = next;
			return;
		}
		while(node.myNext != null){
			node = node.myNext;
		}
		node.myNext = next;
		
		mySize = mySize+l.mySize;
		while(node.myNext != null){
			node = node.myNext;
		}
		myTail = node;
	}
	
	public boolean isOK(){
		int count = 1;
		ListNode node = myHead;
		ListNode nodes = myHead;
		if(myHead == null && myTail == null){
			return true;
		}
		
		while(node.myNext != null){
			node = node.myNext;
			count++;
		}
		
		if(count != mySize){
			return false;
		}
		
		
		while(nodes.myNext != null){
			if(nodes.myItem == null){
				return false;
			}
			nodes = nodes.myNext;
		}
		
		if(node != myTail){
			return false;
		}
		
		return true;
	}
	
	public void remove(Object obj){
		ListNode node = myHead;
		if(obj == null){
			return;
		}
		if(node == null ){
			return;
		}
		
		if(node.myItem.equals(obj)){
			int yes = 0;
			if(myHead.myNext != null){
			if(myHead.myItem == myHead.myNext.myItem){
			yes =1;	
			}else{
				yes =0;
			}
			}
			myHead= myHead.myNext;
			this.mySize = this.mySize -1;
			if(yes == 1){
				remove(obj);
				return;
			}
			if(myHead == null){
				myTail = null;
				return;
			}
			
		}
		while(node.myNext.myNext != null){
			if(node.myNext.myItem.equals(obj)){
				node.myNext = node.myNext.myNext;
				this.mySize = this.mySize -1;
			}else{
			node = node.myNext;
			}
		}
		if(node.myNext.myItem.equals(obj)){
			myTail = node;
			node.myNext = null;
			this.mySize = this.mySize -1;
		}
	}
	public void doubleInPlace() {
		ListNode temp = null;
	    for (ListNode p = myHead; p != null; p = p.myNext) {
	        // TODO Your code here
	    	temp = p.myNext;
	    	p.myNext = new ListNode(p.myItem);
	    	p = p.myNext;
	    	p.myNext = temp;
	    }
	    // TODO And maybe here as well
	}
//	public void reverse() {
//	    ListNode node = myHead;
//	    ListNode temp = null;
//	    while(node != null){
//	    	temp = new ListNode(node.myItem, temp);
//	    	node = node.myNext;
//	    }
//	    myHead = temp;
//	}

//	private static ListNode reverseHelper(ListNode l, ListNode soFar) {
//	    if (l == null) {
//	        return soFar;
//	    } else {
//	        
//	    }
//	}
	
	
	
	
	
//	public void reverse() {
//		ListNode sofar = null;
//		myHead = reverseHelper(this.myHead, sofar);
//		
//	}
//	private static ListNode reverseHelper(ListNode l, ListNode soFar) {
//		if (l == null) {
//	        return soFar;
//	    } else {
//	    	ListNode temp = l.myNext;
//	    	l.myNext = soFar;
//	        return reverseHelper(temp, l);
//	    }
//	}
	
	public void reverse() {
	
		myHead = reverseHelper(this.myHead);
		
	}
	private static ListNode reverseHelper(ListNode head) {
	    ListNode p, soFar;
	    // p plays the role of l in the previous version.
	    for (p = head, soFar = null; p != null;) {
	        ListNode temp = p.myNext;
	            p.myNext = soFar;
	            soFar = p;
	            p = temp;
	    }
	    return soFar;
	}
	
	
	
	
	
	
	
	
	}
