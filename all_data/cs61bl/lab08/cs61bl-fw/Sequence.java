import java.util.Iterator;

public class Sequence<T> implements Iterable<T> {

	protected T[] myValues;
	int myCount;
	
	public Sequence(int capacity) {
		// TODO Auto-generated constructor stub
		this.myCount = 0;
		this.myValues = (T[]) new Object[capacity];
	}

	// Add the argument to the sequence by placing it in the first
	// unused spot in the array and incrementing the count.
	// Assume that the sequence isn't full.
	public void add(T toBeAdded) {
		// YOUR CODE HERE
		if (this.myCount == this.myValues.length) {
			System.err.println("The sequence is full already!");
			System.exit(1);
		}
		this.myValues[this.myCount] = toBeAdded;
		this.myCount++;
//		this.size = 0;
	}

	// Insert toInsert into the sequence at position insertPos,
	// shifting the later elements in the sequence over to make room
	// for the new element.
	// Assumptions: The array isn't full, i.e. myCount < myValues.length
	// Also, insertPos is between 0 and myCount, inclusive.
	//	    public void insert(int toInsert, int insertPos) {
	//	        for (int k = insertPos + 1; k <= myCount; k++) {
	//	            myValues[k] = myValues[k-1];
	//	        }
	//	        myValues[insertPos] = toInsert;
	//	        myCount++;
	//	    }

	public void insert (T n, int pos) {
		if (pos < 0 || pos >= myValues.length) {
			System.err.println("Invalid Index for Inserting!");
			System.exit(1);
		}
		// YOUR CODE HERE
		boolean found = false;
		T temp = null;
		for (int i = 0; i < myValues.length; i++) {
			if (found) {
				T currentValue = myValues[i];
				myValues[i] = temp;
				temp = currentValue;
			}
			if (i == pos) {
				found = true;
				temp = myValues[i];
				myValues[pos] = n;	
			}
		}
		this.myCount++;
	}

	// other methods go here
	public boolean isEmpty() {
		return this.myCount == 0;
	}

	public int size() {
		return this.myCount;
	}

	public T elementAt(int pos) {
		if (pos >= this.myCount) {
			System.err.println("No value exists at that index.");
			System.exit(1);
		} 
		return this.myValues[pos];
	}

	public String toString() {
		String result = "";

		for (int i = 0; i < this.myCount; i++) {
			if (i == this.myCount-1) {
				result += this.myValues[i];
			} else {
				result += this.myValues[i] + " ";
			}	
		}
		return result;
	}

	public T remove (int pos) {
		if (pos < 0 || pos >= myValues.length) {
			System.err.println("Invalid Index!");
			System.exit(1);
		}
		// YOUR CODE HERE
		T result = null;
		boolean found = false;
		for (int i = 0; i < myValues.length; i++) {
			if (found) {
				if (i < myValues.length-1)
					myValues[i] = myValues[i+1];
				else 
					myValues[i] = null;
				continue;
			}
			if (i == pos) {
				found = true;
				result = myValues[i];
				if (i == myValues.length-1)
					myValues[i] = null;
				else
					myValues[i] = myValues[i+1];
			} 	
		}
		this.myCount--;
		return result;
	}

	public boolean contains(T val) {
		for (int i = 0; i < this.myCount; i++) {
			if (this.myValues[i] == val) {
				return true;
			}
		}
		return false;
	}

	@Override
	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		return new seqIterator();
	}
	
	private class seqIterator implements Iterator<T> {
		
		private int i;
		private int size;
		public seqIterator() {
			this.size = size();
			i = 0;
		}
	
		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return size != 0;
		}

		@Override
		public T next() {
			// TODO Auto-generated method stub
			T result = elementAt(i);
			i+=1;
			size-=1;
			return result;
		}
	}
}
