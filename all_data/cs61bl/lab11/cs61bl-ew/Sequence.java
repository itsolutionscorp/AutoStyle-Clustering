import java.util.*;

//import List.ListNode;

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
	// return "[ ]";
	// }
	// String result = "[ " + myHead.myItem.toString() + " ";
	// for (DListNode current = myHead.myNext; current != myHead; current =
	// current.myNext) {
	// result = result + current.myItem.toString() + " ";
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
		DListNode prev = myHead;
		int current = 0;
		for (int c = 0; c < deletePos; c++) {
			current++;
		}
		if (mySize == 1) {
			prev.myNext = null;
			prev.myPrev = null;

			mySize--;
			return;
		}
		prev.myPrev.myNext = prev.myNext;
		prev.myNext.myPrev = prev.myPrev;
		if (deletePos == 0)
			myHead = prev.myNext;
		mySize--;
	}

	/*
	 * TODO You substitute this for the existing version after supplying code
	 * for the Iterator methods.
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
		DListNode p;
		DListNode prev;
		int counter;
		/*
		 * This class provides an iteration of the elements of the sequence.
		 */

		// Initialize an iterator object.
		public SequenceIterator() {
			p = myHead;
			counter = 0;
			// TODO Your code here
		}

		// Return true when there are more ring elements to iterate over;
		// return f otherwise.
		public boolean hasNext() {
			return (counter < mySize);
		}

		// Return the next list element.
		// Precondition: hasNext
		public Object next() {
			Object nxt = p.myItem;
			prev = p;
			p = p.myNext;
			counter++;
			// TODO Your code here
			return nxt;
		}

		// Removes the most recent item returned by next
		// Preconditon: next has been called at least once
		public void remove() {

			if (mySize == 1) {
				mySize--;
				counter--;
				myHead = null;
				prev.myNext = null;
				prev.myPrev = null;
				return;
			} else {
				mySize--;
				counter--;
				myHead = prev.myNext;
				prev.myPrev.myNext = prev.myNext;
				prev.myNext.myPrev = prev.myPrev;
			}
		}

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


		public DListNode(Object obj, DListNode prev, DListNode next) {
			myItem = obj;
			myPrev = prev;
			myNext = next;
		}
	}


	private int mySize; // number of elements in the sequence
	private DListNode myHead; // pointer to the first element of the sequence

	public static void main(String[] args) {
		Sequence seq = new Sequence();
		seq.addToSequence("a");
		System.out.println(seq);
		seq.addToSequence("b");
		System.out.println(seq);
		seq.addToSequence("c");
		System.out.println(seq);
		seq.addToSequence("d");
		System.out.println(seq);
		seq.delete(3);
		System.out.println(seq + "; should be [a b c]");
		seq.delete(0);
		System.out.println(seq + "; should be [b c]");
		seq.delete(1);
		System.out.println(seq + "; should be [b]");
		seq.delete(0);
		System.out.println(seq + "; should be [ ]");
		Sequence seq2 = new Sequence();
		seq2.addToSequence("1");
		seq2.addToSequence("2");
		Iterator itr = seq2.iterator();
		while (itr.hasNext()) {
			System.out.println(itr.next());
			itr.remove();
		}
		System.out.println(seq2);

	}
}
