import java.util.Iterator;

public class Sequence<T> implements Iterable<T> {

	// instance variables
	protected T[] myValues; // sequence elements
	int myCount; // number of array cells used by sequence

	// constructor
	// capacity: actual size of the array or the (temporary) maximum
	// number of elements it can hold
	public Sequence(int capacity) {
		// YOUR CODE HERE
		this.myValues = (T[]) new Object[capacity];
	}

	private class SequenceIterator<T> implements Iterator<T> {
		int nextIndex = -1;
		T nextValue;
		
		public boolean hasNext() {
			if (nextIndex+1 <= myCount - 1) {
				return true;
			} else {
				return false;
			}
		}
		
		public T next() {
			nextIndex++;
    		if (nextIndex <= myCount - 1) {
    			nextValue = (T) myValues[nextIndex];
    			return nextValue;
    		} else {
    			return null;
    		}
    	}
		
		public void remove() {
			throw new UnsupportedOperationException ("Unsupported");
		}
	}

	public Iterator iterator() {
		return new SequenceIterator();
	}

	// Add the argument to the sequence by placing it in the first
	// unused spot in the array and incrementing the count.
	// Assume that the sequence isn't full.
	public void add(T toBeAdded) {
		// YOUR CODE HERE
		if (myCount == myValues.length) {
			System.err.println("Sequence has reached full capacity");
			System.exit(1);
		}
		myValues[myCount] = toBeAdded;
		myCount += 1;
	}

	// Insert toInsert into the sequence at position insertPos,
	// shifting the later elements in the sequence over to make room
	// for the new element.
	// Assumptions: The array isn't full, i.e. myCount < myValues.length
	// Also, insertPos is between 0 and myCount, inclusive.
	public void insert(T toInsert, int insertPos) {
		T nextInsert;
		for (int k = insertPos; k <= myCount; k++) {
			nextInsert = myValues[k];
			myValues[k] = toInsert;
			toInsert = nextInsert;
		}
		myCount++;
	}

	// other methods go here
	public boolean isEmpty() {
		return (myCount == 0);
	}

	public int size() {
		return myCount;
	}

	public T elementAt(int pos) {
		return myValues[pos];
	}

	public void remove(int removePos) {
		myCount -= 1;
		for (int k = removePos; k < myCount; k++) {
			myValues[k] = myValues[k + 1];
		}
	}

	public String toString() {
		String myString = new String();
		myString = myString + myValues[0];
		for (int k = 1; k < myCount; k++) {
			myString = myString + " " + myValues[k];
		}
		return myString;
	}

	public boolean contains(T element) {
		for (int k = 0; k < myCount; k++) {
			if (myValues[k] == element) {
				return true;
			}
		}
		return false;
	}
}
