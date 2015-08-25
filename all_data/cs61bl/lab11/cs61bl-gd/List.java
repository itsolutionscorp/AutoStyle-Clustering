import java.util.Iterator;

public class List implements Iterable {

	private ListNode myHead;
	public ListNode myTail;
	private int mySize;

	public List() {
		myHead = null;
		myTail =null;
		mySize=0;
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
		//int rtn = 0;
		//for (ListNode p = myHead; p != null; p = p.myNext) {
		//	rtn++;
		//}
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
		if(mySize==1){myTail=myHead;}
	}

	public boolean equals (Object obj) {
		// TODO Your code here 
		
		if(this.isEmpty()&&((List)obj).isEmpty()){
			return true;}
		
		if(this.isEmpty()||((List)obj).isEmpty()){
			return false;}
		
		ListNode copy = ((List) obj).myHead;
		ListNode p = myHead;
		if(this.size()!=((List)obj).size()){return false;}
			while(p!=null){
				
				if(p.myItem.equals(copy.myItem)||p.myItem==copy.myItem)
				{
					copy=copy.myNext;
					p = p.myNext;
				}else{return false;}
		}
		return copy==null;
	
	}
	

	public void add (Object x) {
		// TODO Your code here
		if(this.isEmpty()){
			this.addToFront(x);
			myTail = myHead;
		}else{mySize++;
			ListNode p = this.myHead;
			while(p.myNext != null){
				p = p.myNext;
			}
			p.myNext =new ListNode(x);
			myTail=myTail.myNext;
			
		}
	}

	public void appendInPlace (List l) {
		// TODO Your code here destructive
		mySize+=l.size();
		if(l.isEmpty()){
			return;
		}
		myTail=l.myTail;
		if(this.isEmpty()){
			this.myHead = l.myHead;
//			this.myHead.myNext = l.myHead.myNext;
			
			return;
		
		} else {
//			if (this.myHead.myItem == null) {
//				this.myHead
//			}
			ListNode p = this.myHead;
			while(p.myNext != null){
				p = p.myNext;
			}
			p.myNext = l.myHead;
			
		}
	}
	public Iterator iterator() {
	    return new ElementIterator();
	}

	public class ElementIterator implements Iterator {

	    // State variable(s) to be provided.
        public int index;
	    public ElementIterator() {
	        // TODO code to be provided
	    	index =0;
	    }

	    public boolean hasNext() {
	      
	        // TODO code to be provided
	        return index<size();
	    }

	    public Object next() {
	        
	        // TODO code to be provided
	    	Object result;
	    	result = get(index);
	    	index++;
	    	return result;
	    }

	    public void remove() {
	        // not used; do not implement
	    	
	    }
	}
	public boolean isOk(){
		//check the size
		int rtn = 0;
		for (ListNode p = myHead; p != null; p = p.myNext) {
			rtn++;
		}
		if(rtn==mySize){
			for(int k=0;k<mySize;k++){
				ListNode copy = myHead;
				if(copy.myItem==null)
				{return false;}
				copy=copy.myNext;
			}
			if(myHead==null&&myTail!=null||myHead!=null&&myTail==null)
			{return false;}
			if(myHead!=null){
				ListNode ref = myHead;
				while(ref.myNext!=null){ref=ref.myNext;}
				return ref==myTail;
			}else{return true;}
			
		}else{return false;}
	}
	public void remove(Object staff){
		if(contains(staff)){
			ListNode ref = myHead;
			while(ref.myItem.equals(staff))
			{  mySize--;
			if(mySize==0){
				myHead=null;
				myTail=null;
				return;
			}
				myHead=myHead.myNext;
				ref=myHead;
			}
			while(ref!=null&&ref.myNext!=null){
				if(ref.myNext.myItem.equals(staff)){
					ref.myNext=ref.myNext.myNext;
					mySize--;
				}
				ref=ref.myNext;
			}
			
		}
	}
	public void doubleInPlace() {
		mySize=mySize*2;
	    for (ListNode p = myHead; p != null; p = p.myNext) {
	       p.myNext=new ListNode(p.myItem,p.myNext);
	       p=p.myNext;
	    }
	    // TODO And maybe here as well
	}
	public void reverse() {
	    // ...
		if(this.isEmpty()||this.size()==1){return;}else{
		myHead= reverseHelper(this.myHead);
	}}

	private static ListNode reverseHelper(ListNode l, ListNode soFar) {
	    if (l == null) {
	        return soFar;
	    } else {
	    	ListNode temp = l.myNext;
	    	l.myNext = soFar;
	    	return reverseHelper ( temp ,l);
	       
	    }
	}
	private static ListNode reverseHelper(ListNode head) {
	    ListNode p, soFar;
	    // p plays the role of l in the previous version.
	    for (p = head, soFar = null; p != null;) {
	        ListNode temp = p;
	        
	        
	        p=p.myNext;
	        temp.myNext=soFar;
	        soFar=temp;
	    }
	    return soFar;
	}
	}

