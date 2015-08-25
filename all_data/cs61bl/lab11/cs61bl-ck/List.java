import java.util.Iterator;

public class List {

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
	
	public void remove(Object x) {
		if (contains(x)) {
			if (myHead.myItem == x) {
				myHead = myHead.myNext;
				mySize -= 1;
				remove(x);
			} else {
				ListNode temp = myHead;
				while (temp.myNext != null && temp != null) {
					if (temp.myNext.myItem == x) {
						temp.myNext = temp.myNext.myNext;
						mySize -= 1;
					} else {
						temp = temp.myNext;
					}
				}
			}
		}
		ListNode temp = myHead;
		while (temp != null) {
			if (temp.myNext == null) {
				myTail = temp;
			}
			temp = temp.myNext;
		}
	}
	
	
	
	public void doubleInPlace() {
		if (isEmpty()) {
			return;
		}
	    for (ListNode p = myHead; p.myNext != null; p = p.myNext) {
	        // TODO Your code here
	    	ListNode temp = p.myNext;
	    	p.myNext = new ListNode(p.myItem);
	    	p.myNext.myNext = temp;
	    	p = p.myNext;
	    	
	    }
	    // TODO And maybe here as well
	    myTail.myNext = new ListNode(myTail.myItem);
	    mySize = mySize * 2;
	    ListNode temp = myHead;
		while (temp != null) {
			if (temp.myNext == null) {
				myTail = temp;
			}
			temp = temp.myNext;
		}
	}
	
	
	public void reverse() {
		ListNode sofar = null;
		myHead = reverseHelper(myHead, sofar);
		ListNode temp = myHead;
		while (temp != null) {
			if (temp.myNext == null) {
				myTail = temp;
			}
			temp = temp.myNext;
		}
	}

	public static ListNode reverseHelper(ListNode l, ListNode soFar) {
		if (l == null) {
	        return soFar;
	    } else {
	    	ListNode temp = l.myNext;
	    	l.myNext = soFar;
	    	return reverseHelper(temp, new ListNode (l.myItem, soFar));
	    }
	}
	
	
	private static ListNode reverseHelper(ListNode head) {
	    ListNode p, soFar;
	    // p plays the role of l in the previous version.
	    for (p = head, soFar = null; p != null;) {
	        ListNode temp = p;
	        ListNode Next = p.myNext;
//	        System.out.println(temp.myItem);
	        soFar = new ListNode (temp.myItem, soFar);
//	        System.out.println(soFar.myItem);
	        p = Next;;
	    }
	    System.out.println(soFar.myItem);
	    System.out.println(soFar.myNext.myItem);
	    System.out.println(soFar.myNext.myNext.myItem);
	    return soFar;
	}
	
	
	
	
	public boolean isOK() {
		int count = 0;
		if (myHead == null && myTail != null) {
			System.out.println("head is null but tail not null");
			return false;
		}
		if (myHead != null && myTail == null) {
			System.out.println("head is not null but tail is null");
			return false;
		}
		for (ListNode p = myHead; p != null; p = p.myNext) {
			if (count == 0) {
				if (myHead.myItem != get(count)) {
					System.out.println("head is not equal to first one");
					return false;
				}
			}
			if (p.myItem == null) {
				System.out.println("item is null");
				return false;
			}
			if (p.myNext == null) {
				if (p != myTail) {
					System.out.println("tail : " + myTail.myItem);
					System.out.println("last : " + p.myItem);
					System.out.println("tail is not equal to last one");
					return false;
				}
			}
			count ++;
		}
//		if (myTail.myItem != get(count - 1)) {
//			System.out.println("tail is not equal to last one");
//			System.out.println("tail : " + myTail.myItem);
//			System.out.println("last : " + get(count - 1));
//			return false;
//		}
		if (count != mySize) {
			System.out.println("size not equal to count");
			System.out.println("count : " + count);
			System.out.println("size : " + size());
			return false;
		}
		return true;
	}
	
	public Iterator iterator() {
	    return new ElementIterator();
	}

	public class ElementIterator implements Iterator {

	    // State variable(s) to be provided.
		int NextIndex;

	    public ElementIterator() {
	        // TODO code to be provided
	    	NextIndex = 0;
	    }

	    public boolean hasNext() {
	    	// TODO code to be provided
	        return NextIndex < size();     
	    }

	    public Object next() {
	    	Object value = get(NextIndex);
	    	NextIndex ++;
	        return value;
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
		myHead = new ListNode (obj, myHead);
		mySize++;
	}

	public boolean equals (Object obj) {
		// TODO Your code here
		if (! (obj instanceof List)) return false;
		if (this.size() != ((List) obj).size()) {
			return false;
		}
		int count = 0;
		for (ListNode p = myHead; p != null; p = p.myNext) {		
			if (p.myItem != ((List) obj).get(count)) {		
				return false;
			} else {
				count++;
			}
		}
		return true;
	}

	public void add (Object x) {
		// TODO Your code here 

		if (myHead == null) {
			myHead = new ListNode(x, null);
			mySize ++;
			myTail = myHead;
			return;
		}
		ListNode p = myHead;
		while (p.myNext != null) {
			p = p.myNext;
		}
		p.myNext = new ListNode(x, null);
		myTail = p.myNext;
		mySize ++;
	}

	public void appendInPlace (List l) {
		// TODO Your code here
		if (isEmpty()) {
			myHead = l.myHead;
			mySize += l.mySize;
			myTail = l.myTail;
			return;
		}
		if (l.isEmpty()) {
			return;
		}
		ListNode p = myHead;
		while (p.myNext != null) {
			p = p.myNext;
		}
		p.myNext = l.myHead;
		mySize += l.mySize;
		myTail = l.myTail;
	}
	
	public static void main(String[] args) {
		List l1 = new List();
//		System.out.println(l1.isOK());
		List l2 = new List();
		List l3 = new List();
//		System.out.println(l1.equals(l2));
		l1.add("a");
//		System.out.println(l1.isOK());
		l1.add("b");
		l1.add("c");
		l1.add("d");
		l1.add(2);
		l1.add(3);
//		l1.add("a");
//		l1.add("c");
//		l1.add("d");
//		l1.add("a");
//		l1.add("e");
//		System.out.println(l1.toString());
//		l1.remove("a");
//		l1.remove("e");
//		l1.remove("");
//		l2.remove("e");
		System.out.println(l1.toString());
//		System.out.println(l1.isOK());
//		System.out.println(l1.equals(l2));
		l1.doubleInPlace();
		System.out.println(l1.toString());
	}

}
