import java.util.Iterator;


public class Sequence<T> implements Iterable<T> {
	
	private class SequenceIterator implements Iterator<T> {
		
		public SequenceIterator() {
			myIndex = 0;
		}
		
		public boolean hasNext() {
			return myIndex < myCount;
		}
		
		public T next() {
			T rtn = myValues[myIndex];
			myIndex += 1;
			return rtn;
		}
		
		public void remove() throws UnsupportedOperationException {
			throw new UnsupportedOperationException();
		}
	}
	
	public Iterator<T> iterator() {
		return new SequenceIterator();
	}

    // instance variables
    protected T[] myValues;   // sequence elements
    int myCount; // number of array cells used by sequence
    int myIndex;

    // constructor
    // capacity: actual size of the array or the (temporary) maximum
    // number of elements it can hold
    public Sequence(int capacity) {
        myValues = (T[]) new Object[capacity];
    }

    // Add the argument to the sequence by placing it in the first
    // unused spot in the array and incrementing the count.
    // Assume that the sequence isn't full.
    public void add(T toBeAdded) {
        if (myCount == myValues.length) {
        	System.err.println("Sequence is full");
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
    	if (insertPos > myCount) {
    		return;
    	}
    	T n;
    	for (int k = insertPos; k <= myCount; k++) {
			n = myValues[k];
			myValues[k] = toInsert;
			toInsert = n;
		}
    	myCount += 1;
    	
    }

    // other methods go here
    public boolean isEmpty() {
    	return myCount == 0;
    }
    
    public int size() {
    	return myCount;
    }
    
    public Object elementAt(int pos) {
    	if (pos > myCount-1 || pos < 0) {
    		System.err.println("invalid position");
    		System.exit(1);
    	}
    	return myValues[pos];
    }
    
    public String toString() {
    	String result = "" + this.elementAt(0);
    	for (int k = 1; k < myCount; k++) {
    		result += " " + this.elementAt(k);
    	}
    	return result;
    }
    
    public Object remove(int pos) {
    	int k = pos;
    	T temp = myValues[pos];
		T n;
		while (k+1 < myCount) {
			n = myValues[k+1];
			myValues[k] = n;
			k++;
		}
		myCount -= 1;
		return temp;
    }
    
    public boolean contains(T n) {
    	boolean check = false;
    	for (int k = 0; k < myCount; k++) {
    		if (myValues[k] == n) {
    			check = true;
    		}
    	}
    	return check;
    }

}
