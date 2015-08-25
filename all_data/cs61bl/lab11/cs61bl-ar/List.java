import java.util.Iterator;

public class List implements Iterable{

	private ListNode myHead;
	private int mySize;
	private ListNode myTail;

	public List() {
		myHead = null;
		mySize = size();
	}

	public boolean isEmpty() {
		return myHead == null;
	}
    
	public Iterator iterator() {
	    return new ElementIterator();
	}

	public class ElementIterator implements Iterator {

	    // State variable(s) to be provided.

	    public ElementIterator() {
	        // TODO code to be provided
	    }

	    public boolean hasNext() {
	    	if(myHead==null){
	    		return false;
	    	}
	    	if(myHead.myNext!=null)
	    		{return true;}
	        return false;
	        // TODO code to be provided
	    }

	    public Object next() {
	    	if(hasNext()){
	    		Object temp = myHead.myItem;
	    		myHead=myHead.myNext;
	    		return temp;
	    	}else{
	    		if(myHead==null){
	    			return null;
	    		}else{
	    			return myHead.myItem;
	    		}
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
		int rtn = 0;
		for (ListNode p = myHead; p != null; p = p.myNext) {
			rtn++;
			if(p.myNext==null){
				myTail=p;
			}
		}
		mySize=rtn;
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
		if(obj!=null){
		myHead = new ListNode (obj, myHead);
		ListNode first = myHead;
		Iterator itr = this.iterator();
		while(itr.hasNext()){
               itr.next();
		}
		myTail = myHead;
		myHead = first;
		}
		mySize++;
		
	}

	public boolean equals (Object obj) {
	    ListNode curr = myHead;
	    List temp1 = (List)obj;
	    ListNode temp = temp1.myHead;
	    if(myHead==null){
	    	if(temp==null){
	    	    return true;}
	    	else if (temp!=null){
	    		return false;
	    	}
	    }
	    while(curr!=null){
	    	 if(temp == null){
	 	    	return false;
	 	    }
	    	if(curr.myItem!=temp.myItem){
	    		return false;
	    	}
	    	curr=curr.myNext;
	    	temp=temp.myNext;
	    }
	   if(temp!=null){
		   return false;
	   }
	    
		return true;
	}

	public void add (Object x) {
		if (myHead==null){			
			myTail = new ListNode(x);
			myHead = myTail;
		}else{
	    ListNode curr = myHead;
	    while(curr.myNext!=null){
	    	curr=curr.myNext;
	    }
	    ListNode add = new ListNode(x);
	    myTail = add;
	    curr.myNext=add;}
		mySize ++ ;
	}

	public void appendInPlace (List l) {
		// TODO Your code here 
		if(myHead==null){
			if(l.myHead!=null){
				myHead=l.myHead;
			}
		}else{
		    ListNode curr = myHead;
		    while(curr.myNext!=null){
			    curr=curr.myNext;
		}
		curr.myNext=l.myHead;
		}
		mySize=size()+l.size();
		myTail = l.myTail;
	}
	public void isOk(){
		boolean sizeCheck ;
		boolean myItemCheck = true ;
		boolean myHeadCheck = true ;
		boolean myTailCheck = false ;
		int size=size();
		ListNode curr = myHead;
		if(curr!=null){
		while(curr.myNext != null){
			size --;
			curr=curr.myNext;
			if(curr.myItem==null){
				myItemCheck = false;
			}
		}
		myTailCheck=(myTail.myNext==null);
		sizeCheck = (size ==1 );}
		else{
			myHeadCheck = false;
			sizeCheck = (size==0);
			myHeadCheck = (myHead == null);
			myTailCheck = (myTail == null);
		}
		if(!sizeCheck){
			throw new IllegalArgumentException("size error");
		}
		if(!myHeadCheck){
			throw new IllegalArgumentException("myHead error");
		}
		if(!myItemCheck){
			throw new IllegalArgumentException("myItem error");
		}
		if(!myTailCheck){
			throw new IllegalArgumentException("myTail error");
		}
	}
	
	public void doubleInPlace() {

	    for (ListNode p = myHead; p != null; p = p.myNext) {
	        p.myNext= new ListNode(p.myItem,p.myNext);
	        p=p.myNext;
	        
	    }
        
	}

	public void reverse() {
		ListNode temp = null;
	    myHead = reverseHelper(myHead,temp);
	}

	public static ListNode reverseHelper(ListNode l, ListNode soFar) {
		if (l == null) {
	        return soFar;
	    } else {
	    	ListNode temp = l.myNext;
	    	l.myNext = soFar;
	    	soFar = l;
		    l = temp;
	    	return reverseHelper(l, soFar);
	
	    }
	}
	private static ListNode reverseHelper(ListNode head) {
	    ListNode p, soFar;
	    for (p = head, soFar = null; p != null;) {
	        ListNode temp = p;
	        temp = temp.myNext;
	        p.myNext = soFar ;
	        soFar = p;
	        p = temp;
	    }
	    return soFar;
	}

	
	public void remove (Object obj){
		ListNode curr = myHead;
		ListNode pre  = new ListNode(null,myHead);
		if(myHead != null){			
		  while(curr.myNext!=null){
			  if(curr.myItem == obj){
				  pre.myNext=curr.myNext;
				  if(pre.myItem==null){
					  myHead  = curr.myNext;
				  }
			  }else{
				  pre=pre.myNext;
			  }
			  curr= curr.myNext;
		  }
		  if(curr.myItem==obj){
			  if(pre.myNext.myNext==null){
				  myHead = null;
			  }
			  pre.myNext=null;
		  }
		}
	}

}
