import java.util.*;

public class Sequence <T> implements Iterable<T> {

    // instance variables
    protected T[] myValues;   // sequence elements
    int myCount;                // number of array cells used by sequence

    // constructor
    // capacity: actual size of the array or the (temporary) maximum
    // number of elements it can hold
    public Sequence(int capacity) {
        myValues = (T[]) new Object[capacity];
        myCount = 0;
        
    }
    
    public Iterator<T> iterator() {
    	return new SequenceIterator();
    }

    // Add the argument to the sequence by placing it in the first
    // unused spot in the array and incrementing the count.
    // Assume that the sequence isn't full.
    public void add(T toBeAdded) {
    if (myValues.length == myCount) {
    	System.err.println("Sequence array is full");
		System.exit(1);
    } else {
        myValues[myCount] = toBeAdded;
        myCount++;
    }
    }
    
    public boolean isEmpty() {
    	return myCount == 0;
    }
    
    public int size() {
    	return myCount;
    }
    
    public String toString() {
    	String result = "";
    	for (int i = 0; i < myCount; i++) {
    		result += myValues[i];
    		if (i != (myCount - 1)) {
    			result += " ";
    		}
    	}
    	return result;
    }
    
    public T elementAt(int pos) {
    	if (pos < myCount && pos >= 0) {
    		return myValues[pos];
    	} else {
    		System.err.println("Value is either negative or greater than initialized values");
    		System.exit(1);
    		return null;
    	}
    }
    
    
    public void remove(int pos) {
		if (pos < 0 || pos >= myCount) {
			return;
		}
		for (int i = 0; i < myCount; i++) {
			if (i < pos) {
				continue;
			} else if (i+1 == myCount) {
				myValues[i] = null;
			} else {
				myValues[i] = myValues[i+1];
			}
		}
		myCount--;
    }

    // Insert toInsert into the sequence at position insertPos,
    // shifting the later elements in the sequence over to make room
    // for the new element.
    // Assumptions: The array isn't full, i.e. myCount < myValues.length
    // Also, insertPos is between 0 and myCount, inclusive.
    public void insert(T toInsert, int insertPos) {
		if (insertPos < 0 || insertPos > myCount) {
			return;
		}
		for (int i = myCount; i >= 0; i--) {
			if (i < insertPos) {
				break;
			} else if (i == insertPos) {
				myValues[i] = toInsert;
			} else {
				myValues[i] = myValues[i-1];
			}
		}
		myCount++;
    }

    public boolean contains(T k) {
    	for (int i = 0; i < myCount; i++) {
    		if (myValues[i].equals(k)) {
    			return true;
    		}
    	}
    	return false;
    }
    
    private class SequenceIterator implements Iterator<T> {
    	private int index;
    	
    	
    	public boolean hasNext() {
    		return index < myCount;
    	}
    	
    	public T next() {
    		T result = elementAt(index);
    		index++;
    		return result;
    	}
    }

}

