import java.util.*;

import com.sun.corba.se.spi.ior.MakeImmutable;


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
	
	public ListNode getTail() {
		ListNode p = myHead;
		while(p.myNext != null){
			p = p.myNext;	
		}
		myTail = p;
		return myTail;
	}
	public void setTail(ListNode tail) {
		myTail = tail;
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
		myTail = getTail();
		mySize++;
	}

	public boolean equals (Object obj) {
		if (this.size() != ((List) obj).size()) {
			return false;
		}
		if (this.isEmpty() && (((List) obj).isEmpty())) {
			return true;
		}
		int k = 0;
		if (this.size() == ((List) obj).size()) {
 			
			for (ListNode p = myHead; p != null; p = p.myNext) {
				if ((((List) obj).get(k)).toString() != (p.myItem).toString()) {
					return false;					
				}
				k++;
			}
			}
		return true;
		}

	public void add (Object x) {
		if (this.isEmpty()) {
			this.myHead = new ListNode(x);
			mySize = 1;
			this.myTail = this.myHead;
		}
		else {
			this.myTail = getTail();
			myTail.myNext = new ListNode(x);
			mySize++;
		}	
	}

	public void appendInPlace (List l) {
		
		if (this.isEmpty() && !l.isEmpty()) {
            this.myHead = l.myHead;
            this.myTail = l.getTail();
            this.mySize = l.size();
		} else if (this.isEmpty() && l.isEmpty()) {
			
		} else {
			this.myTail = getTail();
			this.myTail.myNext = l.myHead;
			this.mySize += l.size();
		}
	}
	
	public boolean isOK() {
		int sizeCheck=0;
		ListNode Pointer1=myHead;	
		ListNode headCheck = myHead;
		if(myHead == null && myTail != null){			
				return false;
		}
		if(myHead != null && myTail == null){							  
				  return false;			
		}
		
		while (Pointer1!=null){			
			sizeCheck++;
			if(Pointer1.myItem==null){							
				  return false;
			}
			Pointer1 = Pointer1.myNext;		        					
		}
		if(headCheck!=null){
		for(int i = 0; i<size()-1;i++){
		    headCheck = headCheck.myNext;
		}
		if(!headCheck.equals(myTail)){
			return false;
		}
		}
		if(sizeCheck != this.size()){		
			return false;
		}
		return true;
	}
	
	public void remove(Object element_to_compare){
		
		ListNode Tail_remover = myHead; 
		ListNode Pointer= myHead;
		if(myHead==null){
			return;
		}
	
		while(Pointer!=null){
			if(element_to_compare.equals(Pointer.myItem)){
	            if(Pointer.myNext!=null){

	            	Pointer.myItem = Pointer.myNext.myItem;
	                if(Pointer.myNext.myNext!=null){
	                	Pointer.myNext = Pointer.myNext.myNext;
	                }
	                else{
	                	
	                	Pointer.myNext = null;	
	                }        
	            }
	            else{
	            	Pointer = null;
	            }
			}else{
				if(Pointer.myNext!= null && Pointer.myNext.myNext == null){
					if(element_to_compare.equals(Pointer.myNext.myItem)){
						Pointer.myNext=null;
						Pointer= Pointer.myNext;
					}
				}else{
				Pointer = Pointer.myNext;
				}
			}
		}
		if(myHead!=null && myHead.myNext ==null && element_to_compare.equals(myHead.myItem)){
			myHead = null;
		}
	}
	
	public void doubleInPlace() {
        int k =0; 
        ListNode placeholder;
	    for (ListNode p = myHead; p != null; p = p.myNext) {
            if(k==0){
            	placeholder = p.myNext;
            	p.myNext = new ListNode(p.myItem,placeholder);
                k++;       
            }
            else if(k==1){
            	k--;
            }
	    }
	}
	
	public void reverse() {
		if (this.isEmpty() || this.size() == 1) {
			return;
		} else {
			myHead = reverseHelper(this.myHead);
//			myHead = reversehelper(this.myHead, null)
		}
		
	}
	public ListNode reversehelper(ListNode l, ListNode soFar) {
		if (l == null) {
	        return soFar;
	    } else if (soFar == null) {
	    	soFar = new ListNode(l.myItem, null);
	    	return reversehelper(l.myNext, soFar);
	    }
		ListNode temp;
		temp = l;
		l = l.myNext;
		temp.myNext = soFar;
		
		return reversehelper(l, temp);
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
	
	

	
	
	public Iterator iterator() {
	    return new ElementIterator();
	}

	public class ElementIterator implements Iterator {

        public int index = 0;
        public ListNode Pointer = null;
        public ListNode p = null;
	    public ElementIterator() {
	        index = 0;
	        Pointer = myHead;
	        
	        
	    }

	    public boolean hasNext() {
	        if (Pointer == null) {
	        	return false;
	        } else {
	        	return Pointer.myNext!=null;	
	        }
	    }

	    public Object next() {
	    	index++;
	    	Object placeholder = Pointer.myItem;
	    	Pointer = Pointer.myNext;
	        return placeholder;
	    }

	    public void remove() {
	        // not used; do not implement
	    }
	    
    }
	
	
	

}
