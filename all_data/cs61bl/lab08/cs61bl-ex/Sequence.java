import java.util.Iterator;

public class Sequence<T> implements Iterable<T> {

	// instance variables
	protected Object[] myValues; // sequence elements
	int myCount = 0; // number of array cells used by the sequence

	// constructor
	// capacity: actual size of the array or the (temporary) maximum
	// number of elements it can hold
	public Sequence(int capacity) {
		this.myValues = new Object[capacity];
	}

	public boolean isEmpty() {
		if (this.myCount == 0)
			return true;
		return false;
	}

	public int size() {
		return this.myCount;
	}

	public T elementAt(int pos) {
		if (pos > this.myCount - 1) {
			System.err.println("Invalid position");
			System.exit(1);
		}
		return (T) this.myValues[pos];
	}

	public void add(T toBeAdded) {
		if (this.myCount >= this.myValues.length) {
			System.err.println("No more space");
			System.exit(1);
		}
		this.myValues[myCount] = toBeAdded;
		myCount++;
	}

	public String toString() {
		String s = "";
		for (int i = 0; i < myCount; i++) {
			s += myValues[i];
			s += " ";
		}
		return s;
	}

	public T remove(int pos) {
		if (pos > this.myCount - 1) {
			System.err.println("Sorry this is not a valid position");
			System.exit(1);
		}
		Object temp = myValues[pos];
		for (int i = pos; i < myValues.length - 1; i++) {
			myValues[i] = myValues[i + 1];
		}
		myCount--;
		return (T) temp;
	}

	public void insert(T toInsert, int insertPos) {
		if (insertPos > this.myCount - 1) {
			System.err.println("Sorry this is not a valid position");
			System.exit(1);
		}
		if (this.myCount >= this.myValues.length) {
			System.err.println("No more space");
			System.exit(1);
		}
		for (int k = myValues.length - 1; k > insertPos; k--) {
			myValues[k] = myValues[k - 1];
		}
		myValues[insertPos] = toInsert;
		myCount++;
	}

	public boolean contains(T k) {
		for (int i = 0; i < myValues.length; i++) {
			if (myValues[i] == k) {
				return true;
			}
		}
		return false;
	}

	@Override
	public Iterator<T> iterator() {
		return new myIt();
	}
	
	private class myIt implements Iterator<T>{
		private int iteratorIndex = 0;

		@Override
		public boolean hasNext() {
			if (iteratorIndex < myCount)
				return true;
			return false;
		}

		@Override
		public T next() {
			T rval = (T) myValues[iteratorIndex];
			iteratorIndex++;
			return rval;
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}
	}

}