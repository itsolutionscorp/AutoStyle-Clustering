import java.util.*;

/**
 * OVERVIEW: This Sequence class implements a circular sequence in which the
 * item after the last is the first.
 */

public class Sequence implements Iterable {

	// Invariant:
	// (myHead == null && mySize == 0)
	// || (myHead != null
	// && the number of nodes in the list pointed to by myHead == mySize
	// && all the nodes are properly linked)

	private int mySize; // number of elements in the sequence
	private DListNode myHead; // pointer to the first element of the sequence

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
	public String toString() {
		if (isEmpty()) {
			return "[  ]";
		}
		String result = "[ " + myHead.myItem.toString() + " ";
		for (DListNode p = myHead.myNext; p != myHead; p = p.myNext) {
			result += p.myItem.toString() + " ";
		}
		return result + "]";
	}

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
		if (deletePos >= 0 && deletePos < mySize) {
			DListNode temp = myHead;
			for (int i = 1; i <= deletePos; i++) {
				temp = temp.myNext;
			}
			if (temp.equals(myHead)) {
				myHead = temp.myNext;
			}
			temp.myPrev.myNext = temp.myNext;
			temp.myNext.myPrev = temp.myPrev;
			mySize--;
		}
	}

	/*
	 * TODO You substitute this for the existing version after supplying code
	 * for the Iterator methods. public String toString() { Iterator iter =
	 * iterator(); String result = "[  "; while (iter.hasNext()) { result =
	 * result + iter.next().toString() + "  "; } result = result + "]"; return
	 * result; }
	 */

	////////////////////////////////////////////////////////////////////////////////////////////////////
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

		// Initialize an iterator object.
		private int index;
		private boolean turn = true;

		public SequenceIterator() {
			index = 0;
		}

		// Return true when there are more ring elements to iterate over;
		// return false otherwise.
		public boolean hasNext() {
			if ((isEmpty()) || (index == size()) || turn == false) {
				return false;
			} else {
				return true;
			}
		}

		// Return the next list element.
		// Precondition: hasNext
		public Object next() {
			DListNode temp = myHead;
			for (int i = 1; i <= index; i++) {
				temp = temp.myNext;
			}
			index++;
			if (index == size()+1) {
				turn = false;
			}
			return temp.myItem;
		}

		// Removes the most recent item returned by next
		// Preconditon: next has been called at least once
		public void remove() {
			if (index != 0) {
				delete(index);
			}
		}

	}

	////////////////////////////////////////////////////////////////////////////////////////////////////
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
	/////////////////////////////////////////////////////////////////////////////////////////////////

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
		seq2.addToSequence("a");
		seq2.addToSequence("b");
		seq2.addToSequence("c");
		seq2.addToSequence("d");
		Iterator i = seq2.iterator(); //0
		System.out.println(i.hasNext());
		System.out.println(i.next());//1
		System.out.println(i.hasNext());
		System.out.println(i.next());//2
		System.out.println(i.hasNext());
		System.out.println(i.next());//3
		System.out.println(i.hasNext());
		System.out.println(i.next());//4
		System.out.println(i.hasNext());//1
		System.out.println(i.next());
		System.out.println(i.hasNext());
		System.out.println(i.next());
	}

}
