import java.util.Iterator;

/**
 * OVERVIEW: This Sequence class implements a circular sequence in which the
 * item after the last is the first.
 */

public class Sequence implements Iterable {

	// Initialize an empty sequence.
	public Sequence() {
		mySize = 0;
		myHead = null;
	}

	// Return true if this list is empty, false otherwise.
	public boolean isEmpty() {
		return (mySize == 0);
	}

	// Return the number of elements in this list.
	public int size() {
		return mySize;
	}

	// Return a String representation of this list.
	// public String toString() {
	// if (isEmpty()) {
	// return "[  ]";
	// }
	// String result = "[  " + myHead.myItem.toString() + "  ";
	// for (DListNode current = myHead.myNext; current != myHead; current =
	// current.myNext) {
	// result = result + current.myItem.toString() + "    ";
	// }
	// return result + "]";
	// }

	// Add the given object to the end of this list.
	public void addToSequence(Object toAdd) {
		if (isEmpty()) {
			myHead = new DListNode(toAdd);
		} else {
			// Insert toAdd between myHead and myHead.myPrev.
			DListNode newNode = new DListNode(toAdd, myHead.myPrev, myHead);
			// Link the new node into the list.
			myHead.myPrev.myNext = newNode;
			myHead.myPrev = newNode;
		}
		mySize++;
	}

	// Delete the object at the given position in the list.
	// Precondition: the given position is at least 0 and less than mySize.
	public void delete(int deletePos) {
		if (isEmpty()) {
			return;
		}

		SequenceIterator iter = new SequenceIterator();
		int acc = 0;
		while (acc < deletePos) {
			iter.next();
			acc++;
		}
		DListNode ToBeDeleted = iter.iterHead;

		if (deletePos == 0) {
			DListNode fakeTail = myHead;
			while (iter.hasNext()) {
				iter.next();
			}
			fakeTail = iter.iterHead.myPrev;
			ToBeDeleted.myNext.myPrev = fakeTail;
			ToBeDeleted.myPrev.myNext = ToBeDeleted.myNext;
			myHead = ToBeDeleted.myNext;
			mySize--;
			return;
		}

		if (iter.returnedFirstItem) {
			ToBeDeleted.myNext.myPrev = ToBeDeleted.myPrev;
			ToBeDeleted.myPrev.myNext = myHead;
			mySize--;
			return;
		}
		ToBeDeleted.myNext.myPrev = ToBeDeleted.myPrev;
		ToBeDeleted.myPrev.myNext = ToBeDeleted.myNext;
		mySize--;
	}

	/*
	 * TODO You substitute this for the existing version after supplying code
	 * for the Iterator methods. public String toString() { Iterator iter =
	 * iterator(); String result = "[  "; while (iter.hasNext()) { result =
	 * result + iter.next().toString() + "  "; } result = result + "]"; return
	 * result; }
	 */
	public String toString() {
		Iterator iter = iterator();
		String result = "[  ";
		while (iter.hasNext()) {
			result = result + iter.next().toString() + "  ";
		}
		result = result + "]";
		return result;
	}

	// Return an initialized iteration of the ring elements.
	// This list must not be modified while the iteration is in use, except
	// by using the Iterator.remove method
	public Iterator iterator() {
		return new SequenceIterator();
	}

	private class SequenceIterator implements Iterator {

		/*
		 * This class provides an iteration of the elements of the sequence.
		 */
		boolean returnedFirstItem;
		int index;
		DListNode iterHead;

		// Initialize an iterator object.
		public SequenceIterator() {
			// TODO Your code here
			returnedFirstItem = false;
			index = 0;
			iterHead = myHead;
		}

		// Return true when there are more ring elements to iterate over;
		// return false otherwise.
		public boolean hasNext() {
			returnedFirstItem = index > mySize - 1;
			return !returnedFirstItem;
		}

		// Return the next list element.
		// Precondition: hasNext
		public Object next() {
			index++;
			DListNode temp = iterHead;
			iterHead = iterHead.myNext;
			return temp.myItem;
		}

		// Removes the most recent item returned by next
		// Preconditon: next has been called at least once
		public void remove() {
			DListNode ToBeRemoved = iterHead.myPrev;
			ToBeRemoved.myPrev.myNext = iterHead;
			ToBeRemoved.myNext.myPrev = ToBeRemoved.myPrev;

		}

		// TODO Your code here
	}

	private class DListNode {

		/*
		 * DListNode is a class used internally by the Sequence class. Each node
		 * in a Sequence is represented as a DListNode (Doubly linked List),
		 * with an item and references to the previous and next node in the
		 * list. The list is circular, so the last and first nodes contain
		 * references to each other.
		 */

		public Object myItem;
		public DListNode myPrev;
		public DListNode myNext;

		// Initialize a DListNode with myItem obj and
		// myPrev and myNext both pointing to the new node.
		// (This produces a 1-element circular list.) */
		public DListNode(Object obj) {
			myItem = obj;
			myPrev = this;
			myNext = this;
		}

		// Initialize a DListNode with myItem obj
		// and the given values for myPrev and myNext.
		public DListNode(Object obj, DListNode prev, DListNode next) {
			myItem = obj;
			myPrev = prev;
			myNext = next;
		}
	}

	// Invariant:
	// (myHead == null && mySize == 0)
	// || (myHead != null
	// && the number of nodes in the list pointed to by myHead == mySize
	// && all the nodes are properly linked)

	private int mySize; // number of elements in the sequence
	private DListNode myHead; // pointer to the first element of the sequence

	public static void main(String[] args) {
		Sequence seq = new Sequence();
		seq.addToSequence("a");
		System.out.println(seq + "    " + seq.mySize);
		seq.addToSequence("b");
		System.out.println(seq + "    " + seq.mySize);
		seq.addToSequence("c");
		System.out.println(seq + "    " + seq.mySize);
		seq.addToSequence("d");
		System.out.println(seq + "    " + seq.mySize);
		seq.delete(3);
		System.out.println(seq + "; should be [a b c]" + "    " + seq.mySize);
		seq.delete(0);
		System.out.println(seq + "; should be [b c]" + "    " + seq.mySize);
		seq.delete(1);
		System.out.println(seq + "; should be [b]" + "    " + seq.mySize);
		seq.delete(0);
		System.out.println(seq + "; should be [ ]" + "    " + seq.mySize);

		// seq.addToSequence("a");
		// System.out.println(seq);
		// seq.addToSequence("b");
		// System.out.println(seq);
		// seq.addToSequence("c");
		// System.out.println(seq);
		// seq.addToSequence("d");
		// System.out.println(seq);
		// seq.addToSequence("e");
		// System.out.println(seq);
		// seq.addToSequence("f");
		// System.out.println(seq);
		// seq.addToSequence("g");
		// System.out.println(seq);
		// seq.addToSequence("h");
		// System.out.println(seq);
		// seq.delete(0);
		// System.out.println(seq);
		// seq.delete(6);
		// System.out.println(seq);
		// seq.delete(3);
		// System.out.println(seq);
		// seq.delete(0);
		// seq.delete(0);
		// seq.delete(0);
		// seq.delete(0);
		//
		// System.out.println(seq);

	}

}
