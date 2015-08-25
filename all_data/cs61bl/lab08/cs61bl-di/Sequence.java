import java.util.NoSuchElementException;
import java.util.Iterator;

public class Sequence<T> implements Iterable<T> {

	// instance variables
	protected T[] myValues; // sequence elements
	int myCount; // number of array cells used by sequence

	// constructor
	// capacity: actual size of the array or the (temporary) maximum
	// number of elements it can hold

	public T[] getMyValues() {
		return myValues;
	}

	public int getMyCount() {
		return myCount;
	}

	public Sequence(int capacity) {
		// YOUR CODE HERE
		myValues = (T[]) new Object[capacity];

	}

	public Iterator<T> iterator() {
		return new IterSequence();
	}

	private class IterSequence implements Iterator<T> {
		int next;
		T rtn;

		public IterSequence() {
			next = 0;
		}

		public boolean hasNext() {
			if (isEmpty()) {
				return false;
			} else {
				return true;
			}
		}

		public T next() throws NoSuchElementException {
			rtn = getMyValues()[next];
			next++;
			return rtn;
		}
	}

	// Add the argument to the sequence by placing it in the first

	// unused spot in the array and incrementing the count.

	// Assume that the sequence isn't full.

	public void add(T toBeAdded) {

		// YOUR CODE HERE

		myValues[myCount] = toBeAdded;

		myCount++;

	}

	// Insert toInsert into the sequence at position insertPos,

	// shifting the later elements in the sequence over to make room

	// for the new element.

	// Assumptions: The array isn't full, i.e. myCount < myValues.length

	// Also, insertPos is between 0 and myCount, inclusive.

	public void insert(T toInsert, int insertPos) {

		if (insertPos > myValues.length || insertPos < 0) {

			System.err.println("No element exists at position");

			System.exit(1);

			for (int k = insertPos + 1; k <= myCount; k++) {

				myValues[k] = myValues[k - 1];

			}

			myValues[insertPos] = toInsert;

			myCount++;

		}

	}

	// other methods go here

	public boolean isEmpty() {

		if (myCount == 0) {

			return true;

		} else {

			return false;

		}

	}

	public int size() {

		return myCount;

	}

	public T elementAt(int pos) {

		if (pos > myValues.length || pos < 0) {

			System.err.println("No element exists at position");

			System.exit(1);

		}

		return myValues[pos];

	}

	public String toString() {

		String result = null;

		for (int i = 0; i < myCount; i++) {

			result += myValues[i] + " ";

		}

		return result.trim();

	}

	public T remove(int pos) {

		if (myCount <= 0) {

			System.err.println("Position not in sequence");

			System.exit(1);

		}

		T result = myValues[pos];

		for (int i = 0; i < myValues.length; i++) {

			T next;

			if (i == pos) {

				for (int j = i; j < myValues.length; j++) {

					if (j == myValues.length - 1) {

						myValues[j] = null;

						myCount--;

					} else {

						next = myValues[j + 1];

						myValues[j] = next;

					}

				}

			}

		}

		return result;

	}

}