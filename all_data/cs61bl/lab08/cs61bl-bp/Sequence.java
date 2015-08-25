import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

public class Sequence <T> implements Iterable<T>{

	// instance variables
	protected T[] myValues; // sequence elements
	int myCount; // number of array cells used by sequence

	// constructor
	// capacity: actual size of the array or the (temporary) maximum
	// number of elements it can hold
	@SuppressWarnings("unchecked")
	public Sequence(int capacity) {
		myValues = (T[]) new Object[capacity];
		myCount = 0;
	}

	// Add the argument to the sequence by placing it in the first
	// unused spot in the array and incrementing the count.
	// Assume that the sequence isn't full.
	public void add(T toBeAdded) {
		if(myCount == myValues.length) {
			System.err.println("Array is full.");
			System.exit(1);
		}
		myValues[myCount] = toBeAdded;
		myCount++;
	}

	// Insert toInsert into the sequence at position insertPos,
	// shifting the later elements in the sequence over to make room
	// for the new element.
	// Assumptions: The array isn't full, i.e. myCount < myValues.length
	// Also, insertPos is between 0 and myCount, inclusive.
	public void insert(T toInsert, int insertPos) {
		for(int i = myCount; i > insertPos; i--) {
			myValues[i] = myValues[i-1];
		}
		myValues[insertPos] = toInsert;
		myCount++;
	}

	public int size() {
		return myCount;
	}

	public T elementAt(int pos) {
		if (pos >= myCount || pos < 0) {
			System.err.println("Index position is out of bounds.");
			System.exit(1);
		}
		return myValues[pos];
	}
	
	public boolean isEmpty() {
		return myCount==0;
	}
	
	public boolean contains(T search) {
		for(int i = 0; i < myCount; i++) {
			if(myValues[i].equals(search)) {
				return true;
			}
		}
		return false;
	}
	
	public String toString() {
		String message = "";
		for(int i = 0; i < myCount; i++) {
			message = message + myValues[i] + " ";
		}
		return message.trim();
	}
	
	public void remove (int pos) {
		if (pos < 0 || pos >= myCount) {
			return;
		}
		for(int i = pos; i < myCount - 1; i++) {
			myValues[i] = myValues[i+1];
		}
		myCount--;
	}
	
	public Iterator<T> iterator() {
		return new SeqIterator();
	}
	
	private class SeqIterator implements Iterator<T> {
		int index;
		public SeqIterator() {
			index = -1;
		}
		public boolean hasNext() {
			return index + 1 < myCount;
		}

		public T next() {
			index++;
			return myValues[index];
		}
		
	}

}