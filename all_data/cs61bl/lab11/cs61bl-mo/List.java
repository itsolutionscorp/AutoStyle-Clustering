import java.util.*;

public class List implements Iterable {

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

	public Iterator iterator() {
		return new ElementIterator();
	}

	public class ElementIterator implements Iterator {

		// State variable(s) to be provided.

		public ElementIterator() {

		}

		public boolean hasNext() {
			if (myHead != null) {
				return myHead.myNext != null;
			} else {
				return false;
			}
		}

		public Object next() {
			ListNode nextObj = myHead;
			myHead = myHead.myNext;
			return nextObj.myItem;
		}

		public void remove() {
			// not used; do not implement
		}
	}

	private static class ListNode {

		private Object myItem;
		private ListNode myNext;

		private ListNode(Object item, ListNode next) {
			myItem = item;
			myNext = next;
		}

		private ListNode(Object item) {
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
	public boolean contains(Object obj) {
		for (ListNode p = myHead; p != null; p = p.myNext) {
			if (obj.equals(p.myItem)) {
				return true;
			}
		}
		return false;
	}

	// Returns the element at the given position in this list.
	public Object get(int pos) {
		if (pos < 0) {
			throw new IllegalArgumentException(
					"Argument to get must be at least 0.");
		}
		if (pos >= size()) {
			throw new IllegalArgumentException("Argument to get is too large.");
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

	public void addToFront(Object obj) {
		myHead = new ListNode(obj, myHead);
		if (mySize == 0) {
			myTail = myHead;
		}
		mySize++;
	}

	public boolean equals(Object obj) {
		if (obj instanceof List) {
			boolean same = true;
			if (this.size() != ((List) obj).size()) {
				return false;
			} else {
				ListNode q = ((List) obj).myHead;
				for (ListNode p = myHead; p != null; p = p.myNext) {
					if (p.myItem != q.myItem) {
						same = false;
					} else {
						q = q.myNext;
					}
				}
			}
			return same;
		} else {
			return false;
		}
	}

	public void add(Object x) {
		if (x == null) {
			return;
		}
		if (mySize == 0) {
			myHead = new ListNode(x);
			myTail = myHead;
			mySize++;
			return;
		} else {
			myTail.myNext = new ListNode(x);
			myTail = myTail.myNext;
			mySize++;
		}
	}

	public void appendInPlace(List l) {
		if (mySize == 0) {
			myHead = l.myHead;
			return;
		} else if (l.size() == 0) {
			return;
		} else {
			myTail.myNext = l.myHead;
			myTail = l.myTail;
			mySize += l.mySize;
		}
	}
	
	public void remove(Object o) {
		if (this.contains(o)){
			ListNode prev = null;
			for (ListNode curr = myHead; curr != null; curr = curr.myNext) {
				if (curr.myItem == o && prev == null){
					myHead = curr.myNext;
					
					
				}
				else if (curr.myItem == o && prev != null) {
					prev.myNext = curr.myNext;		
					
				}
				else if(curr.myNext == null && curr.myItem == o) {
					curr = null;
					return;
				}
			 
				else {
					prev = curr;
				
				}
			}
		}

	}
	
	public void doubleInPlace() {
	    for (ListNode p = myHead; p != null; p = p.myNext) {
	    	ListNode rest = p.myNext;
	    	ListNode newNode = new ListNode(p.myItem);
	    	p.myNext = newNode;
	    	newNode.myNext = rest;
	    	p = newNode;
	    }
	}
	
	public void reverse() {
	    myHead = reverseHelper(myHead);
	}

//	private static ListNode reverseHelper(ListNode l, ListNode soFar) {
//	    if (l == null) {
//	        return soFar;
//	    } else {
//	    	ListNode temp = l.myNext;
//	    	l.myNext = soFar;
//	    	return reverseHelper (temp , l);
//	    }
//	}
	
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

	public static void isOK(List l1) {
		int count = 0;
		Object item;
		for (ListNode p = l1.myHead; p != null; p = p.myNext) {
			count++;
		}
		if (count != l1.size()) {
			throw new IllegalStateException("Size not equal to number of nodes");
		}

		for (int i = 0; i < l1.size(); i++) {
			item = l1.get(i);
			if (item == null) {
				throw new IllegalStateException("Item can't be null");

			}
		}

		if ((l1.size() == 0 && l1.myHead != null)
				|| (l1.size() == 0 && l1.myTail != null)) {
			throw new IllegalStateException("List empty, but head or tail not null");
		}
		
		ListNode curr = l1.myHead;
		for (ListNode p = l1.myHead; p.myNext != null; p = p.myNext) {
			curr = curr.myNext;
		}
		if (l1.myTail != curr){
			throw new IllegalStateException("Tail not pointing to end of list");
		}

	}

}
