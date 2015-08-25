import java.util.Iterator;
import java.util.NoSuchElementException;

public class Sequence<T> implements Iterable<T>{

	// instance variables
	private T[] myValues; // sequence elements
	private int myCount; // number of array cells used by sequence

	// constructor
	// capacity: actual size of the array or the (temporary) maximum
	// number of elements it can hold
	public Sequence(int capacity) {
		// YOUR CODE HERE
		this.myValues = (T[]) new Object[capacity];
		this.myCount = 0;
	}

	private class SeqIterator implements Iterator<T> {

		private int index;

		public SeqIterator() {
			index = 0;
		}

		@Override
		public boolean hasNext() {
			return this.index < Sequence.this.myValues.length;
		}

		@Override
		public T next() {
			if (this.hasNext()) {
				T current = Sequence.this.myValues[index];
				index++;
				return current;
			}
			throw new NoSuchElementException();
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();

		}
	}

	public Iterator<T> iterator() {
		return new SeqIterator();
	}

	// Add the argument to the sequence by placing it in the first
	// unused spot in the array and incrementing the count.
	// Assume that the sequence isn't full.
	public void add(T toBeAdded) {
		// YOUR CODE HERE
		if (this.myCount >= this.myValues.length) {
			System.err.println("cannot add more");
			System.exit(1);
		} else {
			this.myCount++;
			this.myValues[myCount - 1] = toBeAdded;
		}
	}

	// Insert toInsert into the sequence at position insertPos,
	// shifting the later elements in the sequence over to make room
	// for the new element.
	// Assumptions: The array isn't full, i.e. myCount < myValues.length
	// Also, insertPos is between 0 and myCount, inclusive.
	public void insert(T toInsert, int insertPos) {
		for (int k = insertPos + 1; k <= myCount; k++) {
			myValues[k] = myValues[k - 1];
		}
		myValues[insertPos] = toInsert;
		myCount++;
	}

	public String toString() {
		String s = new String();
		for (int i = 0; i < this.myCount; i++) {
			s += this.myValues[i];
			if (i != (this.myCount - 1)) {
				s += ' ';
			}
		}
		// s = s.substring(0, s.length()-1);
		return s;
	}

	public void remove(int pos) {
		if (pos < 0 || pos >= this.size()) {
			System.err.println("incorrect index");
			System.exit(1);
		} else {
			for (int i = pos; i < this.size() - 1; i++) {
				this.myValues[i] = this.myValues[i + 1];
			}
			this.myCount--;
		}
	}

	// other methods go here

	public boolean isEmpty() {
		return (this.myCount == 0);
	}

	public int size() {
		return this.myCount;
	}

	public T elementAt(int pos) {
		if (pos < 0 || pos >= this.size()) {
			System.err.println("incorrect index");
			System.exit(1);
		}
		return this.myValues[pos];
	}
//
//	public static void main(String[] args) {
//		Sequence<Integer> s = new Sequence<Integer>(8);
//		s.insert(0, 0);
//		s.insert(1, 1);
//		s.insert(2, 2);
//		s.insert(3, 3);
//		s.insert(4, 6);
//		s.insert(9, 5);
//		// Long way
//		Iterator<Integer> it = s.iterator();
//		while (it.hasNext()) {
//			System.out.println(it.next());
//		}

//		for (Integer cur : s) {
//			System.out.println(cur);
//		}

		// @Override
		// public Iterator<T> iterator() {
		// return new SeqIterator();
		// }
	}
//}
