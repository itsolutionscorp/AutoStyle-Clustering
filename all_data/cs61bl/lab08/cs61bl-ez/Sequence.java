import java.util.Iterator;

public class Sequence<T> implements Iterable<T> {

	// instance variables
	int myCount; // number of array cells used by sequence

	protected T[] myValues; // sequence elements

	private T myObj;
	
	private int index;

	// constructor
	// capacity: actual size of the array or the (temporary) maximum
	// number of elements it can hold
	public Sequence(int capacity) {
		myCount = 0;
		myValues = (T[]) new Object[capacity];
		index = 0;
	}

	// Add the argument to the sequence by placing it in the first
	// unused spot in the array and incrementing the count.
	// Assume that the sequence isn't full.
	public void add(T Obj) {
		myObj = Obj;
		if (myCount == myValues.length) {
			System.err.println("Array if full");
			System.exit(1);
		}
		myValues[myCount] = myObj;
		myCount++;
	}

	// Insert toInsert into the sequence at position insertPos,
	// shifting the later elements in the sequence over to make room
	// for the new element.
	// Assumptions: The array isn't full, i.e. myCount < myValues.length
	// Also, insertPos is between 0 and myCount, inclusive.
	public void insert(T Obj, int insertPos) {
		for (int k = myCount; k > insertPos; k--) {
			myValues[k] = myValues[k - 1];
		}
		myValues[insertPos] = Obj;
		myCount++;
	}

	// other methods go here

	public int size() {
		return myCount;
	}

	public Object elementAt(int pos) {
		if (pos < 0 || pos > myValues.length) {
			System.err.println("That is not a valid position");
			System.exit(1);
		}
		return myValues[pos];
	}

	public boolean isEmpty() {
		return myCount == 0;
	}

	public String toString() {
		String myString = "";
		for (int i = 0; i < myCount; i++) {
			myString = myString + myValues[i] + " ";
		}
		return myString;
	}

	public Object remove(int pos) {
		Object deletedValue = myValues[pos];
		for (int k = pos; k < myCount; k++) {
			myValues[k] = myValues[k + 1];

		}
		myCount--;
		myValues[myCount] = null;
		return deletedValue;
	}

	public boolean contains(T obj) {
		for (int i = 0; i < myCount; i++) {
			if (myValues[i].equals(obj)) {
				return true;
			}
		}
		return false;
	}

	
	@Override
	public Iterator<T> iterator() {

		
		SeqIterator iter = new SeqIterator();
		return iter ;
	}
	
	public class SeqIterator implements Iterator<T> {
		private T returnVal;

		@Override
		public boolean hasNext() {
			if(myValues[index] != null) {
				return true;
			}
			return false;
		}

		@Override
		public T next() {
			returnVal = myValues[index];
			index++;
			return returnVal;
	}
}
}




	
