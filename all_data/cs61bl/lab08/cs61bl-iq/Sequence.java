import java.util.Iterator;

public class Sequence <T> {
	
	// instance variables
    protected T[] myValues;   // sequence elements
    int myCount;                // number of array cells used by sequence

    // constructor
    // capacity: actual size of the array or the (temporary) maximum
    // number of elements it can hold
    public Sequence(int capacity) {
        myValues = (T[]) new Object[capacity];
    }

    // Add the argument to the sequence by placing it in the first
    // unused spot in the array and incrementing the count.
    // Assume that the sequence isn't full.
    
    private class Iterable<T> implements Iterator<T> {
    	private int nextElement = 0;
    	public boolean hasNext() {
    		if (myCount > nextElement) {
    			return true;
    		}
    		return false;
    	}
    	
    	public T next() {
    		T rtnValue = (T) myValues[nextElement];
    		nextElement++;
    		return rtnValue;
    	}
    }
    
    public Iterator<T> iterator() {
    	return new Iterable<T>();
    }
    
    public void add(T toBeAdded) {
    	if (myCount == myValues.length) {
    		System.err.println("There's no more room in this array!");
    		System.exit(1);
    	}
        myValues[myCount] = toBeAdded;
        myCount++;
    }
    
    public String toString () {
    	String theString = "";
    	for (int counter = 0; counter < myCount; counter++) {
    		if (counter == myCount - 1) {
    			theString = theString + myValues[counter];
    		} else {
    			theString = theString + myValues[counter] + " ";
    		}
    	}
    	return theString;
    }

    // Insert toInsert into the sequence at position insertPos,
    // shifting the later elements in the sequence over to make room
    // for the new element.
    // Assumptions: The array isn't full, i.e. myCount < myValues.length
    // Also, insertPos is between 0 and myCount, inclusive.
    public void insert(T toInsert, int insertPos) {
    	int counter = myValues.length - 1;
		while (counter > insertPos) {
			myValues[counter] = myValues[counter - 1];
			counter--;
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
    	if (pos >= myCount) {
    		System.err.println("This element does not exist!");
    		System.exit(1);
    	} 
    	return myValues[pos];
    }
    
    public T remove (int pos) {
    	if (myCount == 0) {
    		System.err.println("No values in sequence");
    		return null;
    	}
    	
    	if (pos >= myCount) {
    		System.err.println("This element does not exist");
    		return null;
    	}
    	
    	int counter = pos;
    	T deletedValue = myValues[pos];
		while (counter < myValues.length - 1) {
			myValues[counter] = myValues[counter + 1];
			counter++;
		}
		myCount--;
		return deletedValue;
    }
    
    public boolean contains (T k) {
    	int pos = 0;
    	while (pos < myCount) {
    		if (myValues[pos] == k) {
    			return true;
    		} else {
    			pos++;
    		}
    	}
    	return false;
    }
}
