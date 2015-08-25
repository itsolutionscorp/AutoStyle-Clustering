import java.util.Iterator;
public class Sequence<T> implements Iterable<T> {
	
	private class SeqIter implements Iterator<T> {
		private int index = -1;
		
		public SeqIter() {
			
		}
		
		public void remove() {
			throw new UnsupportedOperationException();
		}

		public T next() {
		    index++;
		    return myValues[index];
		}

		public boolean hasNext() {
		    return (index + 1) < myCount;
		}
	}
	
	public SeqIter iterator() {
		SeqIter i = new SeqIter();
		return i;
	}

    // instance variables
    private T[] myValues;   // sequence elements
    private int myCount;   // number of array cells used by sequence

    // constructor
    // capacity: actual size of the array or the (temporary) maximum
    // number of elements it can hold
    public Sequence(int capacity) {
        // YOUR CODE HERE
    	myValues = (T[]) new Object[capacity];
    }
    
    public boolean isEmpty() {
		if (myCount == 0) {
			return true;
		} else {
			return false;
		}
	}
    
    public String toString() {
		String s = String.valueOf(myValues[0]);
		for (int i = 1; i < myCount; i++) {
			s = s + " " + String.valueOf(myValues[i]);
		}
		return s;
	}
    
    public boolean contains(T k) {
		for (int i = 0; i < myCount; i++) {
			if (myValues[i] == k) {
				return true;
			}
		}
		return false;
	}
    
    public int size() {
		return myCount;
	}

    // Add the argument to the sequence by placing it in the first
    // unused spot in the array and incrementing the count.
    // Assume that the sequence isn't full.
    public void add(T toBeAdded) {
        // YOUR CODE HERE
    	myValues[myCount] = toBeAdded;
    	myCount++;
    }
    
    public T elementAt(int pos) {
		return myValues[pos];
	}
    
    public void remove(int pos) {
		for (int i = pos; i < myCount; i++) {
			myValues[i] = myValues[i+1];
		}
		myCount--;
	}
    
    public void insert(int pos, T inserted) {
		if (pos < 0 || pos >= myValues.length) {
			return;
		}
		// YOUR CODE HERE
		myCount++;
		for (int i =  myCount; i > pos; i--) {
			myValues[i] = myValues[i-1];
		}
		myValues[pos] = inserted;
	}
    
}