package lab08;

import java.util.Iterator;

public class Sequence<T>implements Iterable<T> {
	
	protected T[] myValues;   // sequence elements
    int myCount;                // number of array cells used by sequence

    public Iterator<T> iterator(){
        //no point implementing a whole class for something only used once
        return new Iterator<T>() {
            //no need to have constructor which takes MyClass, (non-static) inner class has access to instance members
            public boolean hasNext(){
                //simplify
                return myCount < myValues.length;
            }
            public T next(){
                return myValues[myCount++]; //getting clever
            }

            public void remove(){
                throw new UnsupportedOperationException();
            }
        };
    }
    
    // constructor
    // capacity: actual size of the array or the (temporary) maximum
    // number of elements it can hold
    public Sequence(int capacity) {
        // YOUR CODE HERE
    	//int[] arr = new int[5];
    	 Object[] x = new Object[capacity];
    	 myValues = (T[])x;
    	
    }

    // Add the argument to the sequence by placing it in the first
    // unused spot in the array and incrementing the count.
    // Assume that the sequence isn't full.
    public void add(T i) {
        // YOUR CODE HERE
     	if (myCount < myValues.length) {
    		myValues[myCount] = i;
        	myCount++;
    	}
    	else {
    		System.err.println("myValues is full.");
    		System.exit(1);
    	}
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
	public T remove (int pos) {	
			T removed;
			if (pos < 0 || pos >= myCount) {
				System.err.println("Index out of bounds.");
				System.exit(1);
			}	
			removed = myValues[pos];
			for(int k = pos; k < myCount-1; k++) {
				myValues[k] = myValues[k+1];
			}		
			myCount--;
			return removed;
		}
    public boolean isEmpty() {
    	return (myCount == 0);
    }
    
    public int size() {
    	return myCount;
    }
    
    public T elementAt(int pos) {
    	if ((pos >= 0) && (pos < myCount)) {
    		return (myValues[pos]);
    	}
    	else {
    		System.err.println("Index is out of bounds.");
    		System.exit(1);
    		return null;
    	}
    }
    public boolean contains (T k) {
    	boolean doesContain = false;
    	for (int i = 0; i < myCount; i++) {
    		if (myValues[i] == k) {
    			doesContain = true;
    		}
    	}
    	return doesContain;
    	
    }
}