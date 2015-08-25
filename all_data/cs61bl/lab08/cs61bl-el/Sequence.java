import java.util.Iterator;

public class Sequence<T> implements Iterable<T> {

	public Iterator<T> iterator() {
		return new SeqIterator();
	}

	private class SeqIterator implements Iterator<T> {
		int curPos;

		public SeqIterator() {
			curPos = 0;
		}

		public boolean hasNext() {
			if (curPos < myValues.length) {
				return true;
			}
			return false;
		}

		public T next() {

			T valToReturn = myValues[curPos];
			curPos++;
			return valToReturn;
		}

		public void remove() {
			throw new UnsupportedOperationException();
		}

	}

	protected T[] myValues;
	int myCount;

	public Sequence (int capacity) {
		// YOUR CODE HERE
		myValues = (T[]) new Object[capacity];
		myCount = 0;
	}

	// Add the argument to the sequence by placing it in the first
	// unused spot in the array and incrementing the count.
	// Assume that the sequence isn't full.
	public void add(T toBeAdded) {
		myValues[myCount] = toBeAdded;
		myCount++;
	}

	// Insert toInsert into the sequence at position insertPos,
	// shifting the later elements in the sequence over to make room
	// for the new element.
	// Assumptions: The array isn't full, i.e. myCount < myValues.length
	// Also, insertPos is between 0 and myCount, inclusive.
	public void insert(T toInsert, int insertPos) {
		T[] result = (T[]) new Object[myCount];

		for (int i = 0; i < myCount; i++) {
			result[i] = myValues[i];
		}

		for (int k = insertPos + 1; k <= myCount; k++) {
			myValues[k] = result[k - 1];
		}
		myValues[insertPos] = toInsert;
		myCount++;
	}

	public boolean isEmpty() {
		if (myCount == 0) {
			return true;
		}
		return false;
	}

	public int size() {
		return myCount;
	}

	public T elementAt(int pos) {
		if (pos > myCount || pos < 0) {
			System.err.println("Position invalid");
			System.exit(1);
		}
		return myValues[pos];
	}

	public void remove(int pos) {
		throw new UnsupportedOperationException();
	}

	public boolean contains(T k) {
		for (int i = 0; i < myCount; i++) {
			if (myValues[i] == k) {
				return true;
			}
		}
		return false;
	}

	public String toString() {
		String string1 = new String();
		for (int i = 0; i < myCount; i++) {
			string1 += i;
			string1 += " ";
		}
		return string1;
	}
}
