import java.util.*;

public class List implements Iterable {

	private ListNode myHead, myTail;
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
	public Iterator iterator() {
	    return new ElementIterator();
	}

	public class ElementIterator implements Iterator {
         
          ListNode currentItem; 

	    // State variable(s) to be provided.

	    public ElementIterator() {
	        // TODO code to be provided
	    	currentItem = myHead;
	    }

	    public boolean hasNext() {
	        if (currentItem == null) {
	        	return false;
	        } else {
	        	return true;
	        }
	    }

	    public Object next() {
	    	ListNode temp = currentItem;
	        if (hasNext()) {
	        	currentItem = currentItem.myNext;
	        } else {
	        	return null;
	        }
	        return temp.myItem;
	        // TODO code to be provided
	    }

	    public void remove() {
	        // not used; do not implement
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
	
	public void reverse() {
		myTail = myHead;
	    myHead = reverseHelper(myHead);
	}
	
	private static ListNode reverseHelper(ListNode l, ListNode soFar) { //Recursion method for reverseHelper
	    if (l == null) {
	        return soFar;
	    } else {
	    	ListNode temp = l.myNext;
	    	l.myNext = soFar;
	    	return reverseHelper ( temp , l );
	    }
	}
	
	private static ListNode reverseHelper(ListNode head) {   				//Iteration method for reverseHelper
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

	// Returns the element at the given position in this list.
	public Object get (int pos) {
		if (pos < 0) {
			throw new IllegalArgumentException (
					"Argument to get must be at least 0.");
		}
		if (pos >= mySize) {
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
		if (myTail == null) {
			myTail = myHead;
		}
		mySize++;
	}

	public boolean equals (Object obj) {
		// TODO Your code here 
		List obj1 = (List) obj;
		ListNode q = obj1.myHead;
		if (obj1.isEmpty() && this.isEmpty()) {
			return true;
		}
		
		if (this.mySize != obj1.mySize) {
			return false;
		}
		for (ListNode p = myHead; p != null; p = p.myNext) {
			if (!p.myItem.equals(q.myItem)) {
				return false;
			} else {
				q = q.myNext;
			}
		}
		return true;
	}

	public void add (Object x) {
		// TODO Your code here 
		if (x==null) {
			return;
		}
		if (myHead==null) {
			myHead = new ListNode (x, null);
			myTail = myHead;
		} else {
			myTail.myNext = new ListNode (x, null);
			myTail = myTail.myNext;
		}
		mySize++;
	}

	public void appendInPlace (List l) {
		// TODO Your code here 
		if (l.isEmpty()) {
			return;
		}
		if (this.isEmpty()) {
			myHead = l.myHead;
			return;
		}

		myTail.myNext = l.myHead;
		myTail = l.myTail;
	}
	
	public void remove (Object x) {

		ListNode temp = myHead;
		ListNode secondLast;
		if (x==null || myHead==null) {
			return;
		}
		while (myHead.myItem.equals(x)) {
			if (myHead.myNext==null) {
				mySize--;
				myHead = null;
				myTail = null;
				return;
			}
			myHead = myHead.myNext;
			temp.myNext = null;
			mySize--;
			temp = myHead;
		}
		while (myTail.myItem.equals(x)) {
			secondLast = myHead;
			while (!secondLast.myNext.equals(myTail)) {
				secondLast = secondLast.myNext;
			}
			myTail = secondLast;
			myTail.myNext = null;
			mySize--;
		}
		
		for (ListNode p = myHead; p.myNext != null;) {
			if (p.myNext==myTail) {
				break;
			}
			if (p.myNext.myItem.equals(x)) {
				temp = p.myNext;
				p.myNext = p.myNext.myNext;
				temp.myNext = null;
				mySize--;
			} else {
				p = p.myNext;
			}
		}
	}
	
	public void doubleInPlace() {

	    for (ListNode p = myHead; p != null; p = p.myNext.myNext) {
	        ListNode newDuplicate = new ListNode(p.myItem, p.myNext);
	        p.myNext = newDuplicate;
	        mySize++;
	        if (newDuplicate.myNext == null) {
	        	myTail = newDuplicate;
	        }
	    }
	    return;
	}
	
	public boolean isOK() {
		int count=0;
		if (myHead == null && myTail == null) {
			return true;
		}		
		if (myHead == null && myTail != null) {
			System.out.println("1");
			return false;
		}
		if (myTail == null) {
			System.out.println("2");
			return false;
		}
		if (myTail != null) {
			if (myTail.myNext!= null) {
				System.out.println("3");
				System.out.println(myTail.myNext);
				return false;
			}
			count = 1;
		}			
		
		for (ListNode i = myHead; i.myNext!=null; i=i.myNext) {
			count++;
			if (i.myItem == null) {
				System.out.println("4");
				return false;
			}
		}

		if (count != mySize) {
			System.out.println("5");
			System.out.println(count);
			System.out.println(mySize);
			return false;
		}
		return true;
	}

}
