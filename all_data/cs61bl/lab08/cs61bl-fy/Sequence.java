import java.util.Iterator;

public class Sequence <T> implements Iterable<T>{

    // instance variables
    protected Object[] myValues;   // sequence elements
    int myCount;                // number of array cells used by sequence

    // constructor
    // capacity: actual size of the array or the (temporary) maximum
    // number of elements it can hold
    public Sequence(int capacity) {
        myValues = new Object[capacity];
    }

    // Add the argument to the sequence by placing it in the first
    // unused spot in the array and incrementing the count.
    // Assume that the sequence isn't full.
    public void add(T toBeAdded) {
    	if (myCount > myValues.length - 1) {
    		System.err.println("Cannot add, array full");
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
        for (int k = insertPos + 1; k <= myCount; k++) {
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
    	if (pos > size() - 1) {
    		System.err.println("Index doesn't exist");
    		System.exit(1);
    	}
    	return (T) myValues[pos];
    }
    public String toString() {
    	String stringHold = "";
    	for (int k = 0; k < size(); k += 1) {
    		stringHold += myValues[k] + " ";
    	}
    	return stringHold.substring(0 , stringHold.length() - 1);
    }
    public void remove(int pos) {
    	if (pos < 0 || pos >= myValues.length) {
			return;
		}
		for (int k = pos; k < myValues.length; k++) {
			if (k == myValues.length - 1) {
				myValues[k] = 0; 
			}
			else {
				myValues[k] = myValues [k+1];
			}
		}
    }
    public boolean contains(T k) {
    	for (int q = 0; q < size(); q += 1) {
    		if (myValues[q] == k) {
    			return true;
    		}
    	}
    	return false;
    }
    public Iterator<T> iterator() {
    	return new SeqIter();
    }
  
    private class SeqIter implements Iterator<T> {
    	private int nextIndex = 0;
    	
    	public boolean hasNext() {
    		if (Sequence.this.isEmpty()) {
    			return false;
    		} else if (nextIndex > Sequence.this.myCount - 1) {
    			return false;
    		} else {
    			return true;
    		}
    	}
    	public T next() {
			T n = Sequence.this.elementAt(nextIndex);
			nextIndex++;
			return n;
			
			
		} 
    }
}


