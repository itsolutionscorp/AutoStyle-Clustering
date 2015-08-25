import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;
public class Sequence <T> implements Iterable<T>{
	// instance variables
    protected T[] myValues;   // sequence elements
    int myCount;                // number of array cells used by sequence

    // constructor
    // capacity: actual size of the array or the (temporary) maximum
    // number of elements it can hold
    public Sequence(int capacity) {
        myValues = (T[]) new Object[capacity];
    }
    
    public Iterator iterator() {
    	return new seqIterator();
    }
    
    private class seqIterator implements Iterator{
    	
    	private int index;
    	
    	public boolean hasNext() {
    		if (index == myCount) {
				return false;
			}
    		return true;
    	}

		public T next() {
			T rtn = myValues[index];
			index++;
			return rtn;
		}
    	
    }

    // Add the argument to the sequence by placing it in the first
    // unused spot in the array and incrementing the count.
    // Assume that the sequence isn't full.
    public void add(T toBeAdded) {
    	if (myCount == myValues.length) {
    		System.err.println("Sequence full");
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
    public void insert(T toInsert, int pos) {
    	if (pos < 0 || pos >= myCount) {
			return;
		}
    	T j = null;
    	T k;
		for (int i = 0; i <= myCount; i++) {
			if (i == pos) {
				j = myValues[i];
				myValues[i] = toInsert;
			} else if (i > pos) {
				k = myValues[i];
				myValues[i] = j;
				j = k;
			}
		}
		myCount++;
    }

    // other methods go here
    public boolean isEmpty(){
    	return myCount == 0;
    }
    public int size(){
    	return myCount;
    }
    public T elementAt(int pos){
    	if (pos >= myCount) {
    		System.err.println("Index out of range");
    		System.exit(1);
    	}
    	return myValues[pos];
    } 
    public String toString() {
    	String result = new String();
    	if (isEmpty()) {
    		return result;
    	}
    	result = myValues[0].toString();
    	for (int i = 1; i < myCount; i++) {
    		result = result + " " + myValues[i].toString();
    	}
    	return result;
    }
    public void remove(int pos) {
    	
		if (pos < 0 || pos >= myCount) {
			return;
		}
		for (int i = 0; i < myCount-1; i++) {
			if (i >= pos){
				myValues[i] = myValues[i+1];
			}
		}
		myValues[myCount-1]= null; 
		myCount--;
	}
    public boolean contains(T k) {
    	boolean result = false;
    	for (int i = 0; i < myCount; i ++) {
    		if (myValues[i] == k) {
    			result = true;
    		}
    	}
    	return result;
    }
}
