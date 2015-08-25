public class IntList {

	ListNode myHead;
	ListNode myTail;
	int mySize;
	

	public IntList() {
		myHead = null;
		myTail = null;
		mySize = 0;
	}

	/**
	 * Constructs an IntList with one node. Head and tail are the same.
	 */
	public IntList(ListNode head) {
		myHead = myTail = head;
	}

	/**
	 * Returns true if this list is empty, false otherwise.
	 */
	public boolean isEmpty() {
		return mySize == 0;
	}

	/**
	 * Adds a new node with the given value to the front of this list.
	 */
	public void addToFront(int k) {
		if (myHead == null) {
			myHead = myTail = new ListNode(k);
		} else {
			myHead = new ListNode(k, null, myHead);
			myHead.myNext.myPrev = myHead;
		}
		mySize++;
	}

	/**
	 * Adds a new node with the given value to the end of this list.
	 */
	public void addToEnd(int k) {
		if (myHead == null) {
			myHead = myTail = new ListNode(k);
		} else {
			myTail.myNext = new ListNode(k, myTail, null);
			myTail = myTail.myNext;
		}
		mySize++;
	}

	/**
	 * Attaches the input list to the end of this list.
	 */
	public void append(IntList list) {
		if (list.isEmpty()) {
			return;
		}
		if (isEmpty()) {
			myHead = list.myHead;
			myTail = list.myTail;
			mySize = list.mySize;
			return;
		}
		myTail.myNext = list.myHead;
		list.myHead.myPrev = myTail;
		myTail = list.myTail;
		mySize += list.mySize;
	}

	/**
	 * Removes the node reference by p from this list.
	 */
	private void remove(ListNode p) {
		if (myHead == myTail) {
			myHead = myTail = null;
		} else if (p == myHead) {
			myHead = myHead.myNext;
			myHead.myPrev = null;
		} else if (p == myTail) {
			myTail = myTail.myPrev;
			myTail.myNext = null;
		} else {
			p.myNext.myPrev = p.myPrev;
			p.myPrev.myNext = p.myNext;
		}
	}

	@Override
	public String toString() {
		String s = "";
		for (ListNode p = myHead; p != null; p = p.myNext) {
			s = s + p.myItem + " ";
		}
		return s;
	}

	private class ListNode {

		int myItem;
		ListNode myPrev;
		ListNode myNext;

		public ListNode(int k) {
			myItem = k;
			myPrev = myNext = null;
		}

		public ListNode(int k, ListNode prev, ListNode next) {
			myItem = k;
			myPrev = prev;
			myNext = next;
		}
	}

	/**
	 * Returns the result of sorting the values in this list using the insertion
	 * sort algorithm. This list is no longer usable after this operation; you
	 * have to use the returned list.
	 */
	public IntList insertionSort() {
		ListNode soFar = null;  
		for (ListNode p = myHead; p != null; p = p.myNext) {
			 //System.out.println(p.myItem);
			soFar = insert(p, soFar);
			
		}
		return new IntList(soFar);	
	}

	/**
	 * Inserts the node p into the list headed by head so that the node values
	 * are in increasing order.
	 */
	private ListNode insert(ListNode p, ListNode head) {
		if (head == null) head = new ListNode(p.myItem); 
		else {
			if (p.myItem < head.myItem) return new ListNode(p.myItem, null, head);
			ListNode prev = head;
			ListNode next = head.myNext;
			while (next!=null) {
				if (p.myItem < next.myItem) {
					prev.myNext = new ListNode(p.myItem, prev, next);
					break;
				} else {
					prev = next;
					next = next.myNext;
				}
			}
			if (p.myItem >= prev.myItem && next == null) {
				prev.myNext = new ListNode(p.myItem); }
			}
		return head;
	}


	/**
	 * Sorts this list using the selection sort algorithm.
	 */
	public void selectionSort() {
		IntList sorted = new IntList();
		while (myHead != null) {
			int minSoFar = myHead.myItem;
			ListNode minPtr = myHead;
			// Find the node in the list pointed to by myHead
			// whose value is largest.
			for (ListNode p = myHead; p != null; p = p.myNext) {
				if (p.myItem < minSoFar) {
					minSoFar = p.myItem;
					minPtr = p;
				}
			}
			sorted.addToEnd(minSoFar);
			remove(minPtr);
		}
		myHead = sorted.myHead;
	}

	/**
	 * Returns the result of sorting the values in this list using the Quicksort
	 * algorithm. This list is no longer usable after this operation.
	 */
	public IntList quicksort() {
		
		if (mySize <= 1) {
			return this;
		}
		// Assume first element is the divider.
		IntList smallElements = new IntList();
		IntList largeElements = new IntList();
		int divider = myHead.myItem;
		ListNode current = myHead;
		while ( current.myNext!=null) {
			if (current.myItem >= divider) smallElements.addToEnd(current.myItem);
			else largeElements.addToEnd(current.myItem);
			current = current.myNext;
		}
		largeElements.addToEnd(current.myItem);
		return merge(smallElements.quicksort().myHead, largeElements.quicksort().myHead);
		
	}

	/**
	 * Returns the result of sorting the values in this list using the merge
	 * sort algorithm. This list is no longer usable after this operation.
	 */
	public IntList mergeSort() {
//		if (mySize <= 1) {
//			System.out.println("odd");
//			return this;
//		}
//		IntList oneHalf = new IntList();
//		IntList otherHalf = new IntList();
//		if (mySize == 2) { System.out.println("basecase " + myHead.myItem +" "+myTail.myItem);
//			if (myHead.myItem < myTail.myItem) {
//				oneHalf.addToEnd(myHead.myItem);
//				oneHalf.addToEnd(myTail.myItem);
//			} else {
//				oneHalf.addToEnd(myTail.myItem);
//				oneHalf.addToEnd(myHead.myItem);}
//			return oneHalf;
//		}
//		
//		int n = (myHead.myItem + myTail.myItem)/2;
//		
//		for (ListNode current = myHead; current.myNext != myTail; current= current.myNext) {
//			System.out.println(current.myItem + " was current. now my tail " + myTail.myItem
//					);
//			if (current.myItem<n) oneHalf.addToEnd(current.myItem);
//			else otherHalf.addToEnd(current.myItem);	
//		}
//		if (myTail.myPrev.myItem<n) oneHalf.addToEnd(myTail.myPrev.myItem);
//		else  otherHalf.addToEnd(myTail.myPrev.myItem);
//		if (myTail.myItem<n) oneHalf.addToEnd(myTail.myItem);
//		else  otherHalf.addToEnd(myTail.myItem);
//		return merge(oneHalf.mergeSort().myHead, otherHalf.mergeSort().myHead);
//		
//		if (mySize <= 1) {
//			return this;}
//		IntList oneHalf = new IntList();
//		IntList otherHalf = new IntList();
//		oneHalf.append(this);
//		myHead = myHead.myNext;
//		otherHalf.append(this);
//		ListNode current = myHead;
//		while (!(otherHalf.mySize > 1)) {
//			myHead = myHead.myNext;
//			otherHalf.myHead
//		}
//		oneHalf = current;
//		
		
//		return merge(oneHalf.mergeSort().myHead, otherHalf.mergeSort().myHead);
	
			if (mySize <= 1) return this;
			IntList oneHalf = new IntList();
			IntList otherHalf = new IntList();
			ListNode current = myHead;
			for (int i = 0; i < mySize; i++, current = current.myNext) {
				if (i < mySize/2) oneHalf.addToEnd(current.myItem);
				else otherHalf.addToEnd(current.myItem);
				//System.out.println("one more n");
				
			}
			return merge(oneHalf.mergeSort().myHead, otherHalf.mergeSort().myHead);
		
	}
	
	
	//  public Node MergeSort(Node headOriginal) 
//    {
//        if (headOriginal == null || headOriginal.next == null)
//            return headOriginal;
//        Node a = headOriginal;
//        Node b = headOriginal.next;
//        while ((b != null) && (b.next != null)) 
//        {
//            headOriginal = headOriginal.next;
//            b = (b.next).next;
//        }
//        b = headOriginal.next;
//        headOriginal.next = null;
//        return merge(MergeSort(a), MergeSort(b));
//    }

	/**
	 * Returns the result of merging the two sorted lists / represented by list1
	 * and list2.
	 */
	private static IntList merge(ListNode list1, ListNode list2) {
		IntList rtn = new IntList();
		while (list1 != null && list2 != null) {
			if (list1.myItem < list2.myItem) {
				rtn.addToEnd(list1.myItem);
				list1 = list1.myNext;
			} else {
				rtn.addToEnd(list2.myItem);
				list2 = list2.myNext;
			}
		}
		
		while (list1 != null) {
			rtn.addToEnd(list1.myItem);
			list1 = list1.myNext;
		}
		while (list2 != null) {
			rtn.addToEnd(list2.myItem);
			list2 = list2.myNext;
		}
		return rtn;
	}

	/**
	 * Returns a random integer between 0 and 99.
	 */
	private static int randomInt() {
		return (int) (100 * Math.random());
	}

	public static void main(String[] args) {
		IntList values;
		IntList sortedValues;
		values = new IntList();
		System.out.print("Before selection sort: ");
		for (int k = 0; k < 10; k++) {
			values.addToFront(randomInt());
		}
		System.out.println(values);
		values.selectionSort();
		System.out.print("After selection sort: ");
		System.out.println(values);

		values = new IntList();
		System.out.print("Before insertion sort: ");
		for (int k = 0; k < 10; k++) {
			values.addToFront(randomInt());
		}
		System.out.println(values);
		sortedValues = values.insertionSort();
		System.out.print("After insertion sort: ");
		System.out.println(sortedValues);

		values = new IntList();
		System.out.print("Before quicksort: ");
		for (int k = 0; k < 10; k++) {
			values.addToFront(randomInt());
		}
		System.out.println(values);
		sortedValues = values.quicksort();
		System.out.print("After quicksort: ");
		System.out.println(sortedValues);

		values = new IntList();
		System.out.print("Before merge sort: ");
		for (int k = 0; k < 10; k++) {
			values.addToFront(randomInt());
		}
		System.out.println(values);
		sortedValues = values.mergeSort();
		System.out.print("After merge sort: ");
		System.out.println(sortedValues);
	}

}
