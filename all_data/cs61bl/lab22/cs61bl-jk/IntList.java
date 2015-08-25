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
		ListNode copyP = new ListNode(p.myItem);//need to create a new listnode, because I need som
		
		if (head == null) {// checks for the case where there is no existing listnode and that it is an empty list.
			return copyP;
		}
		
		if(copyP.myItem < head.myItem){
			head.myPrev = copyP;
			copyP.myNext = head;
			return copyP;
		}
		
		ListNode insertInto;// 
		
		for (insertInto = head; insertInto.myNext != null; insertInto = insertInto.myNext) {
			if (insertInto.myNext.myItem > copyP.myItem) {//chekcing to see if the next item value is greater than the value that i want to insert. If greater then, I  insert in between.
				break;
			}
		}
		
		copyP.myNext = insertInto.myNext;// the insertInto.myNext was exactly the listNode that stopped during break once its value was greater than the value that I wanted to insert.
		copyP.myPrev = insertInto;
		if (copyP.myNext != null)
			copyP.myNext.myPrev = copyP;
		insertInto.myNext = copyP;
		
		
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
		IntList smallElements = new IntList();// keep a linked list of smaller numbers than the pivot
		IntList largeElements = new IntList();// keep a linked list of greater numbers than the pivot.
		int divider = myHead.myItem;// will represent the pivot's number.
		// TODO your code here!
		for(ListNode current=myHead.myNext; current != null; current = current.myNext){//this is a loop running to create lesser collection and bigger collection;
			if(current.myItem < divider){//we need to have this if case in order to determine that we are creating a lesser list.
				smallElements.addToEnd(current.myItem);
			} else{
				largeElements.addToEnd(current.myItem);// this creates a greater list.
			}
	
		}
		IntList toReturn = new IntList();// You create a new IntList, because you want to return a sorted IntList. With the newly created IntList, you are going append sorted collectios in order. small part first middle and larger 
		toReturn.append(smallElements.quicksort());// this sorts the lesser collection for preparation
		toReturn.addToEnd(divider);// this appends the pivot to the end of the lesser colleciton in the toReturn List. But will eventually become the middle element
		toReturn.append(largeElements.quicksort());// finally adds greater collection after the divider(middle)
		

		return toReturn;
	}

	/**
	 * Returns the result of sorting the values in this list using the merge
	 * sort algorithm. This list is no longer usable after this operation.
	 */
	public IntList mergeSort() {
		if (mySize <= 1) { 
			return this; //reached base case
		}
		IntList oneHalf = new IntList(); //one half of original list
		IntList otherHalf = new IntList(); //another half of original list 
		// TODO your code here!
		//split
		//call mergesort recursively
		//call merge
		
		int half = mySize / 2;//split to calculate the middle index of the original list.
		
		ListNode current = myHead; // set myHead to current because current keeps track of the posiition on the iteration.
		for(int i = 0; i < half;i++) {// this is the loop that copies the first half of the original list.
			oneHalf.addToEnd(current.myItem);// add item into the first half list.
			current = current.myNext;// set the pointer to be next of the current position.
		}
		
		for (;current != null; current = current.myNext) {//copying the second half of the original list.
			otherHalf.addToEnd(current.myItem); // appending items to the second half of the list.
		}
		
		return IntList.merge(oneHalf.mergeSort().myHead, otherHalf.mergeSort().myHead);// this is the recursive call which allows for the merge to sort each of the halves.
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
