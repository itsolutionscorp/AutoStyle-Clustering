import java.awt.Point;
import java.util.Iterator;


public class Sequence <T> implements Iterable<T>{

	/**
	 * @param args
	 */
	private T[] myValues;    // sequence elements
    int myCount;                // number of array cells used by sequence

    // constructor
    // capacity: actual size of the array or the (temporary) maximum
    // number of elements it can hold
    public Sequence(int capacity) {
        myValues = (T[])(new Object[capacity]);
    }
    
    public Iterator<T> iterator() {
    	return new myIterator();
    }
    // Add the argument to the sequence by placing it in the first
    // unused spot in the array and incrementing the count.
    // Assume that the sequence isn't full.
    private class myIterator implements Iterator<T> {
    	int Index;

        public void initIterator() {
            Index = 0;
        }

        public T next() {
            T valToReturn = (T)myValues[Index];
            Index++;
            return valToReturn;
        }

        public boolean hasNext() {
            return Index < myCount;
        }
        public void remove() {}

    }
    
    public void add(T toBeAdded) {
        if (myCount == myValues.length) {
        	System.err.println("No more open spots in the array.");
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
        this.add(myValues[myCount - 1]);
    	for (int k = myCount - 1; k > insertPos; k--) {
        	myValues[k] = myValues[k-1];
        }
        myValues[insertPos] = toInsert;
    }

    public boolean isEmpty() {
    	if (myCount == 0) {
    		return true;
    	} return false;
    }
//returns true when this sequence is empty and returns false otherwise

    public int size() {
    	return myCount;
    }
//returns the number of values in this sequence
//Note: There is a distinction between size and capacity.

    public T elementAt(int pos) {
    	if (pos >= myValues.length || pos < 0) {
    		System.err.println("Index does not exist.");
    		System.exit(1);
    	}
    	return myValues[pos];
    }
//returns the value at the given position in the sequence
//e.g. If the sequence contains 3, 1, and 4, elementAt(0) returns 3.
//    Note: If someone asks for the elementAt an index that does not exist, you should call
//    System.err.println and include a description of
//    the error and call System.exit(1) to exit the method.
//    The same is true for any case where a method is called with incorrect input.\

	public T remove (int pos) {
		if (pos < 0 || pos >= myValues.length) {
			System.err.println("Index does not exist.");
			System.exit(1);
		}
		if (pos >= myCount) {
			return null;
		}
		else {
			T returnValue = myValues[pos];
			for (int i = pos; i < myCount; i++) {
				if (pos == myCount - 1) {
					myValues[i] = null;
					break;
				}
				myValues[i] = myValues[i + 1];
				}
			myCount--;
			return returnValue;
		}
	}
    
	public boolean contains (T k) {
		for (int i = 0; i < myCount; i++) {
			if (myValues[i] == k) {
				return true;
			}
		} return false;
	}
	
	public String toString() {
		String s = new String("");
		for (int i = 0; i < myCount; i++) {
			if (i == myCount - 1) {
				s = s + myValues[i];
				break;
			}
			s = s + myValues[i] + " ";
		}
		return s;
	}
	
	public static void main(String[] args) {
		Sequence<String> myStringSequence = new Sequence<String>(4);
		myStringSequence.add("0");
		myStringSequence.add("1");
		myStringSequence.add("2");
		myStringSequence.insert("3", 0);
		for (String string : myStringSequence) {
			System.out.println(string);
		}
		
	}

}
