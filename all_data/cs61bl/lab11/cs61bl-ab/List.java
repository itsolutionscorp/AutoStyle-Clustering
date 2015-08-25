import java.util.*;

public class List {

	private ListNode myHead;
	private int mySize;
	private ListNode myTail;

	public List() {
		myHead = null;
		myTail = null;
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
		List lst = (List) obj;
		if(isEmpty()) {
			return lst.isEmpty();
		} else if(size()!=lst.size()) {
			return false;
		} else {
			for(int i = 0; i<size(); i++) {
				if (get(i)!=lst.get(i)) {
					return false;
				}
			}
			return true;
		}
	}

	public void add (Object x) {
		mySize++;
		if(myHead ==null) {
			myHead = new ListNode(x);
			myTail = myHead;
		} else {
			myTail.myNext=new ListNode(x);
			myTail=myTail.myNext;
			
		}
	}

	public void appendInPlace (List l) {
		// TODO Your code here 
		mySize+=l.size();
		if(myHead == null){
			myHead = l.myHead;
			myTail=l.myTail;
			
		}else{
			if(!l.isEmpty()){
			    myTail.myNext=l.myHead;
			    myTail=l.myTail;
			}
		}
	}
	
	public void remove(Object x) {
        // not used; do not implement
		while (myHead!=null&&
				myHead.myItem ==x) {
			myHead = myHead.myNext;
		}
		if(myHead==null) {
			myTail = null;
			return;
		}
		for(ListNode p=myHead; p!=null&p.myNext!=null; p=p.myNext) {
			if(p.myNext.myItem==x) {
				p.myNext=p.myNext.myNext;
			}
		}
    }
	
	public void doubleInPlace() {
	    for (ListNode p = myHead; p != null; p = p.myNext) {
	        ListNode temp = p.myNext;
	        p.myNext = new ListNode(p.myItem);
	        p.myNext.myNext = temp;
	        p=p.myNext;
	    }
	}
	
	
	
	public Iterator iterator() {
	    return new ElementIterator();
	}

	public class ElementIterator implements Iterator {

	    // State variable(s) to be provided.
		
		private int i;

	    public ElementIterator() {
	        i=0;
	    }

	    public boolean hasNext() {
	        if (i<size()-1){
	        	return true;
	        }
	    	return false;
	        
	    }

	    public Object next() {
	    	i++;
	        return get(i-1);
	        // TODO code to be provided
	    }
	    
	    public void remove(){
	    	
	    }

	    
	}
    public boolean isOK() {
    	int count = 0;
    	for (ListNode p = myHead; p!=null; p=p.myNext) {
    		count++;
    		if (p.myNext==null&myTail!=p) {
    			return false;
    		}
    		if (p.myItem == null) {
    			return false;
    		}
    	}
    	if ((myHead==null&myTail!=null)|(myHead!=null&myTail ==null)){
    		return false;
    	}
    	return count == size();
    }
    public void reverse() {
        if (myHead!=null) {
        	myTail = myHead;
        	myHead =reverseHelper(myHead, null);
        }
    }

    private static ListNode reverseHelper(ListNode l, ListNode soFar) {
    	if (l == null) {
            return soFar;
        } else {
            ListNode temp=l;
            l=l.myNext;
            temp.myNext = soFar;
            soFar = temp;
            return reverseHelper(l, soFar);
        }
    }
}
