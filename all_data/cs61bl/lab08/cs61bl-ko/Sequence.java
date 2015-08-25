import java.util.Iterator;

public class Sequence<T> implements Iterable<T> {

	
	protected T[] myValues;
	int myCount;
	
	public Sequence(int capacity) {
		if (capacity > 0) {
			myValues = (T[]) new Object[capacity];
		}
	}
	public Iterator iterator() {
		return new SequenceIterable();
	}
	
	private class SequenceIterable implements Iterator<T>{
		int nextIndexToReturn;
		
		public boolean hasNext() {
			return nextIndexToReturn < myCount;
		}
		public T next() {
			T valToReturn = myValues[nextIndexToReturn];
			nextIndexToReturn++;
			return valToReturn;
		}
	}

    // Add the argument to the sequence by placing it in the first
    // unused spot in the array and incrementing the count.
    // Assume that the sequence isn't full.
    public void add(T toBeAdded) {
        if (myCount < myValues.length) { 
        	myValues[myCount] = toBeAdded;
        	myCount++;
      } else {
      System.err.println("cannot add to a full IntSequence");
      System.exit(1);
      }
    }

    // Insert toInsert into the sequence at position insertPos,
    // shifting the later elements in the sequence over to make room
    // for the new element.
    // Assumptions: The array isn't full, i.e. myCount < myValues.length
    // Also, insertPos is between 0 and myCount, inclusive.
	public void insert (T toInsert, int pos) {
		if (pos < 0 || pos > myCount) {
			return;
		}
		for (int i = 0; i <= myCount; i++) {
			if (i >= pos) {
				T temp = myValues[i];
				myValues[i] = toInsert;
				toInsert = temp;
			}
		}
		myCount++;
	}
    // other methods go here
    public boolean isEmpty() {
    	if (myCount == 0) {
    		return true;
    	}
    	return false;
    }
    
    public int size() {
    	return myCount;
    }
    
    public T elementAt(int pos) {
    	if (0 <= pos & pos < myCount) {
    		return myValues[pos];
    	}
    	System.err.println("index doesn't exist, cannot call element");
//    	System.exit(1);
    	return null;
    }
    
    public String toString() {
    	String result = new String();
    	result = "" + myValues[0];
    	for (int i = 1; i < myCount; i++) {
    		result = result + " " + myValues[i];
    	}
    	return result;
    }
    
	public T remove(int pos) {
		if (pos < 0 || pos >= myCount) {
			System.err.println("index doesn't exist, cannot remove element");
			System.exit(1);
	    	return null;
		}
			T result = myValues[pos];
			for (int i = 0; i < myCount; i++) {
				if (i >= pos) {
					myValues[i] = myValues[i+1];
				}
			}
			myCount -= 1;
			return result;
	}
	public boolean contains(T k) {
		for (int i = 0; i < myCount; i++) {
			if (myValues[i] == k) {
				return true;
			}
		}
		return false;
	}
}

