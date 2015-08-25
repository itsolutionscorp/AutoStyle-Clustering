import java.util.Iterator;

public class Sequence <T> {

    // instance variables
    protected T[] myValues;   // sequence elements
    int myCount;                // number of array cells used by sequence
    // constructor
    // capacity: actual size of the array or the (temporary) maximum
    // number of elements it can hold
    public Sequence(int capacity) {
        // YOUR CODE HERE
    	myValues = (T[]) new Object[capacity]; 
    	int myCount = 0; 
    }

    // Add the argument to the sequence by placing it in the first
    // unused spot in the array and incrementing the count.
    // Assume that the sequence isn't full.
    public void add(T toBeAdded) {
    	if (myCount < myValues.length) {
    		myValues[myCount] = toBeAdded;
    		myCount ++; 
    	} else{
    		System.err.println("Array is full.");
    		System.exit(1);
    	}
    }

    // Insert toInsert into the sequence at position insertPos,
    // shifting the later elements in the sequence over to make room
    // for the new element.
    // Assumptions: The array isn't full, i.e. myCount < myValues.length
    // Also, insertPos is between 0 and myCount, inclusive.
    public void insert(T toInsert, int insertPos) {
    	if (insertPos < 0 || insertPos > myCount) {
    		System.err.println("Index is not yet used.");
    		System.exit(1);
		}
        for (int k = myCount; k > insertPos; k--) {
            myValues[k] = myValues[k-1];
        }
        myValues[insertPos] = toInsert;
        myCount++;
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
    	if (pos < myCount) {
    		return myValues[pos];
    	}
    	else {
    		System.err.println("Index is not yet used.");
    		System.exit(1);
    		//should never get here
    		return (T) new Object(); 
    	}
    }

    public String toString() {
    	String s = "";
    	if (this.isEmpty() == false) {
	    	for (int i = 0; i < myCount -1; i++) {
	    		s = s + myValues[i].toString() + " "; 
	    	}
	        s = s + myValues[myCount-1].toString(); 
    	} 
    	return s;
    }
    
    public int getCapacity() {
    	return myValues.length; 
    }
    
    public T remove(int pos) {
    	if (pos < 0 || pos >= myCount) {
    		System.err.println("Index is not yet used.");
    		System.exit(1);
    		//should never get here
    		return (T) new Object(); 
		}
    	T removed = myValues[pos]; 
		for (int i = pos; i < myCount - 1; i++) {
			myValues[i] = myValues[i+1]; 
		}
		myCount --;
		return removed; 
    }
    
    public boolean contains(T k) {
    	for (int i = 0; i < myCount; i++) {
    		if (myValues[i].equals(k)) {
    			return true;
    		}	
    	}
    	return false; 
    }
    
    public Iterator iterator() {
    	return new SequenceIterator(); 
    	
    }
    private class SequenceIterator implements Iterator<T> {
        private int nextIndex; 
        
        public SequenceIterator() {
        	nextIndex = 0; 
        }
        
		@Override
		public boolean hasNext() {
			return nextIndex < myCount; 
		}

		@Override
		public T next() {
			T value = myValues[nextIndex]; 
			nextIndex++; 
			return value; 
		}

		@Override
		public void remove() throws UnsupportedOperationException {
			throw new UnsupportedOperationException();
		}
    	
    }
}

