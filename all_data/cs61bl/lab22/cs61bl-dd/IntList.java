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
			soFar = insert(p, soFar);
		}
		return new IntList(soFar);
	}

	/**
	 * Inserts the node p into the list headed by head so that the node values
	 * are in increasing order.
	 */
	private ListNode insert(ListNode p, ListNode head) {
		// TODO you fill this out!
		if(head == null){
			return new ListNode(p.myItem);
		}else if(head.myNext == null){
			ListNode toAdd = new ListNode(p.myItem);
			if(toAdd.myItem > head.myItem){
				head.myNext = toAdd;
				toAdd.myPrev = head;
				return head;
			}else{
				toAdd.myNext = head;
				head.myPrev = toAdd;
				return toAdd;
			}
		}else{
			ListNode temp = head;
			ListNode toAdd = new ListNode(p.myItem);
			if(toAdd.myItem < head.myItem){
				toAdd.myNext = head;
				head.myPrev = toAdd;
				return toAdd;
			}
			while(temp.myNext != null){
				if(toAdd.myItem == temp.myItem){
					ListNode next = temp.myNext;
					temp.myNext = toAdd;
					toAdd.myPrev = temp;
					toAdd.myNext = next;
					next.myPrev = toAdd;
					return head;
				}
				if(toAdd.myItem > temp.myItem && toAdd.myItem < temp.myNext.myItem){
					ListNode next = temp.myNext;
					temp.myNext = toAdd;
					toAdd.myPrev = temp;
					toAdd.myNext = next;
					next.myPrev = toAdd;
					return head;
				}
				temp = temp.myNext;
			}
			temp.myNext = toAdd;
			toAdd.myPrev = temp;
			return head;
		}
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
		// TODO your code here!
		ListNode temp = myHead;
		for(int i = 0; i < mySize; i++){
			if(temp.myItem < divider){
				smallElements.addToEnd(temp.myItem);
			}
			if(temp.myNext != null){
				temp = temp.myNext;
			}
		}
		temp = myHead;
		for(int i = 0 ; i < mySize; i++){
			if(temp.myItem > divider){
				largeElements.addToEnd(temp.myItem);
			}
			if(temp.myNext != null){
				temp = temp.myNext;
			}
		}
			IntList rtn = smallElements.quicksort();
			rtn.addToEnd(divider);
			rtn.append(largeElements.quicksort());
			return rtn;
	}

	/**
	 * Returns the result of sorting the values in this list using the merge
	 * sort algorithm. This list is no longer usable after this operation.
	 */
	public IntList mergeSort() {
		if (mySize <= 1) {
			return this;
		}
		IntList oneHalf = new IntList();
		IntList otherHalf = new IntList();
		// TODO your code here!
		int size = 0;
		if(mySize % 2 != 0){
			size = mySize / 2 + 1;
		}else{
			size = mySize / 2;
		}
		ListNode temp = myHead;
		for(int i = 0; i < size; i++){
			oneHalf.addToEnd(temp.myItem);
			temp = temp.myNext;
		}
		for(int i = size; i < mySize; i++){
			otherHalf.addToEnd(temp.myItem);
			if(temp.myNext != null){
				temp = temp.myNext;
			}
		}
		if(oneHalf.mySize == 1 && otherHalf.mySize == 1){
			return merge(oneHalf.myHead, otherHalf.myHead);
		}else if(oneHalf.mySize == 1){
			return merge(oneHalf.myHead, otherHalf.mergeSort().myHead);
		}else if(otherHalf.mySize == 1){
			return merge(oneHalf.mergeSort().myHead, otherHalf.myHead);
		}else{
			return merge(oneHalf.mergeSort().myHead, otherHalf.mergeSort().myHead);
		}
	}

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
