import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

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
		//mySize = rtn;
		//return mySize;
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
		mySize += 1;
		if (mySize ==1){
			myTail = myHead;
		}
	}

	public boolean equals (Object obj) {
		// TODO Your code here 
		List l = (List)obj;
		if (this.isEmpty() && (! l.isEmpty())){
			return false;
		}
		if (l.isEmpty() && (! this.isEmpty())){
			return false;
		}
		if (this.size() != ((List)obj).size()){
			return false;
		}
		for (ListNode p = this.myHead, q = ((List)obj).myHead; p!= null && q!= null; p=p.myNext, q = q.myNext){
			if (! p.myItem.equals(q.myItem)){
				return false;
			}
		}
		return true;
	}

	public void add (Object x) {
		// TODO Your code here 
		//get to the last listnode
		//set mynext to a listnode with object x as its item
		for (ListNode p = myHead; p!= null; p = p.myNext){
			if (p.myNext == null){
				p.myNext = new ListNode(x);
				myTail = p.myNext;
				mySize +=1;
				break;
			}
		}
		if (myHead==null){
			myHead = new ListNode(x);
			myTail = myHead;
			mySize += 1;
		}
	}
	

	public void appendInPlace (List l) {
		// TODO Your code here 
		if (l.myHead == null){
			
		} else {
		for (ListNode p = myHead; p!= null; p = p.myNext){
			if (p.myNext == null){
				p.myNext = l.myHead;
				this.mySize += l.mySize;
				this.myTail = l.myTail;
				break;
			}
		}
		if (myHead==null){
			myHead = l.myHead;
			this.myTail= l.myTail;
			this.mySize = l.mySize;
		}
		}
	}
	
	public void remove(Object o){
		for (ListNode p = myHead; p.myItem.equals(o);){
			//System.out.println(this);
			this.myHead= p.myNext;
			p = p.myNext;
			mySize --;
			//System.out.println(this);
			if (mySize == 0){
				myTail = null;
				break;
			}
		}
		
		for (ListNode p = myHead; p!=null;){
			if (p.myNext.myNext == null){
				if (p.myNext.myItem.equals(o)){
					p.myNext = null;
				}
				break;
			}
			if ((p.myNext.myItem).equals(o)){
				p.myNext = p.myNext.myNext;
			} else {
				p = p.myNext;
			}
		}
	}
	
	public void reverse(){
		myHead = reverseHelper(myHead);
	}
	
	public static ListNode reverseHelper(ListNode head){
		
		ListNode p, soFar;
	    // p plays the role of l in the previous version.
	    for (p = head, soFar = null; p != null;) {
	        ListNode temp = p.myNext;
	        p.myNext = soFar;
	        soFar = p;
	        p = temp;
	    }
	    return soFar;
		
		/*if (l==null){
			return soFar;
		} else {
			ListNode l2 = l.myNext;
			l.myNext=soFar;
			return reverseHelper(l2, l);
		}*/
	}
	
	public Iterator iterator() {
	    return new ElementIterator();
	}

	public class ElementIterator implements Iterator {

	    // State variable(s) to be provided.
		ListNode myNode;

	    public ElementIterator() {
	        // TODO code to be provided
	    	myNode = myHead;
	    }

	    public boolean hasNext() {
	    	if (myNode == null){
	    		return false;
	        } else {
	        	return true;
	        }
	        // TODO code to be provided
	    }

	    public Object next() {
	        Object rtnItem = myNode.myItem;
	        myNode = myNode.myNext;
	        return rtnItem;
	        // TODO code to be provided
	    }

	    public void remove() {
	        // not used; do not implement
	    }
	}
	
	public boolean isOK(){
		if (mySize != size()){
			return false;
		}
		for (ListNode p = myHead; p!= null; p = p.myNext){
			if (p.myItem == null){
				return false;
			}
			if (p.myNext == null){
				return p == myTail;
			}
		}
		if ((myHead == null && myTail != null)||(myHead != null && myTail == null)){
			return false;
		}
		return true;
	}
	
	public void doubleInPlace() {
	    for (ListNode p = myHead; p != null; p = p.myNext) {
	        p.myNext = new ListNode(p.myItem, p.myNext) ;
	        p = p.myNext;
	        mySize ++;
	        if (p.myNext ==null){
	        	myTail = p;
	        }
	    }
	}
	
	/*public static void main(String[] args){
		
		ArrayList<String> arrlist = new ArrayList<String>();
		arrlist.add("a");
		arrlist.add("b");
		arrlist.add("c");
		arrlist.add("d");
		arrlist.add("e");
		arrlist.add("f");
		arrlist.add("g");
		for (int i=0; i<10000000; i++){
			arrlist.add("h");
		}
		arrlist.add("i");
		
		LinkedList<String> lst = new LinkedList<String>();
		lst.add("a");
		lst.add("b");
		lst.add("c");
		lst.add("d");
		lst.add("e");
		lst.add("f");
		lst.add("g");
		for (int i=0; i<10000000; i++){
			lst.add("h");
		}
		lst.add("i");
		
		Timer t = new Timer();
		t.start();
		arrlist.remove(5000000);
		//arrlist.add(500000, "pls");
		//t.stop();
		arrlist.get(5000000);
		System.out.println(t.elapsed());
		t.stop();
		
		
		Timer t2 = new Timer();
		t2.start();
		lst.remove(5000000);
		lst.add(500000, "pls");
		//lst.get(5000000);
		System.out.println(t2.elapsed());
		t2.stop();
		
		//LinkedList<String> lst2 = new LinkedList<String>();
		//lst2.add("Hi");
		//lst2.get(1);
		
		LinkedList<String> lst = new LinkedList<String>();
		lst.add("a");
		lst.add("b");
		lst.add("c");
		lst.add("d");
		lst.add("e");
		lst.add("f");
		lst.add("g");
		for (int i=0; i<10000000; i++){
			lst.add("h");
		}
		lst.add("i");
		
		Timer t3 = new Timer();
		lst.peekFirst();
		System.out.println(t3.elapsed());
		t3.stop();
		
		Timer t4 = new Timer();
		lst.peekLast();
		System.out.println(t4.elapsed());
		t4.stop();
	}*/
}










