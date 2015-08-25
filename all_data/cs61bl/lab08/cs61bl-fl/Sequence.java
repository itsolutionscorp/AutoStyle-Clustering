import java.util.Iterator;

public class Sequence<T> implements Iterable<T> {

    // instance variables
    protected T[] myValues;     // sequence elements
    int myCount;                // number of array cells used by sequence
    

    // constructor
    // capacity: actual size of the array or the (temporary) maximum
    // number of elements it can hold
    public Sequence(int capacity) {
        myValues = (T[]) new Object[capacity];
        myCount = 0;
    }
    
    /*
    private Sequence (T[] myValues, int myCount, int index) {
    	this.myCount = myCount; this.index = index;
    	this.myValues = new T[myValues.length];
    	
    }*/

    // Add the argument to the sequence by placing it in the first
    // unused spot in the array and incrementing the count.
    // Assume that the sequence isn't full.
    public void add(T toBeAdded) {
        if (myCount < myValues.length) {
            myValues[myCount] = toBeAdded;
            myCount ++;
        }
        else {
            System.err.println("No memory to add new value.");
            System.exit(1);
        }
    }

    // Buggy Version: Insert toInsert into the sequence at position insertPos,
    // shifting the later elements in the sequence over to make room
    // for the new element.
    // Assumptions: The array isn't full, i.e. myCount < myValues.length
    // Also, insertPos is between 0 and myCount, inclusive. 
    /*
    public void insert(int toInsert, int insertPos) {
        for (int k = insertPos + 1; k <= myCount; k++) {
            myValues[k] = myValues[k-1];
        }
        myValues[insertPos] = toInsert;
        myCount++;
    }*/
    
    // Tells if the IntSequence has zero elements stored in it.
    public boolean isEmpty () {
        return myCount == 0;
    }
    
    // Returns the number of elements stored in the IntSequence.
    public int size () {
        return myCount;
    }
    
    // Returns the Element at the specified position.
    public T elementAt (int pos) {
        if (pos < 0 || pos >= myCount) {
            System.err.println("Requested element is outside of bounds.");
            System.exit(1);
        }
        return myValues[pos];
    }

    public String toString () {
        String toReturn = "";
        for (int i = 0; i < myCount; i ++) {
            toReturn += myValues[i] + " ";
        }
        if (!toReturn.equals("")) {
            toReturn = toReturn.substring(0,toReturn.length() - 1);
        }
        return toReturn;
    }
    
    public T remove (int pos) {
    	if (myCount == 0) {
    		System.err.println("Can't remove element from an empty set.");
    		System.exit(1);
    	}
    	if (pos < 0 || pos >= myCount) {
    		System.err.println("Argument out of Range:");
    		System.exit(1);
    	}
        T store = myValues[pos];
        for (int i = pos; i < myCount - 2; i++) {
            myValues[i] = myValues[i+1];
        }
        myCount --;
        return store;
    }
    
    public boolean equals (Object other) {
        if (!(other instanceof Sequence<?>)) { return false; }
        Sequence<T> cOther = (Sequence<T>)other;
        if (myCount != cOther.myCount) { return false; }
        for (int i = 0; i < myCount - 1; i ++) {
            if (!myValues[i].equals(cOther.myValues[i])) {return false;}
        }
        return true;
    }
    
 // Delete the value at the given position in the argument array,
 	// shifting all the subsequent elements down, and storing a 0
 	// as the last element of the array.
 	public static void remove (Sequence seq, int pos) {
 		if (seq == null || seq.myCount == 0) { return; }
        if (pos < 0 || pos >= seq.myCount) {
 			return;
 		}
        for (int i = pos; i < seq.myCount - 2; i ++) {
             seq.myValues[i] = seq.myValues[i+1];
        }
         seq.myValues[seq.myCount-1] = 0;
         seq.myCount --;
 	}
 	
 	/* New Version:
 	 * Inserts value newInt at position pos.
 	 */
	public void insert (T newInt, int pos) {
        if (pos < 0 || pos > myCount || pos >= myValues.length ||
        		myCount + 1 > myValues.length) {
			System.err.println("Position out of range.");
			System.exit(1);
		}
        myCount ++;
        for (int i = myCount - 1; i > pos; i --) {
            myValues[i] = myValues[i-1];
        }
        myValues[pos] = newInt;
	}
	
	public boolean contains (T value) {
		for (int i = 0; i < myCount; i ++) {
			if (myValues[i].equals(value)) {
				return true;
			}
		}
		return false;
	}

	public Iterator<T> iterator() {
		return new SequenceIterator();
	}

	private class SequenceIterator implements Iterator<T> {
		private int index = -1;
		
		public boolean hasNext() {
			return index + 1 < myCount;
		}

		public T next() {
			index ++;
			return myValues[index];
		}

		public void remove() {
			throw new UnsupportedOperationException("Not Implemented.");
		}
		
	}




    

}

