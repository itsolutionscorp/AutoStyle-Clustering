import java.util.Iterator;

public class Sequence<T> implements Iterable<T>{

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

	// Add the argument to the sequence by placing it in the first
    // unused spot in the array and incrementing the count.
    // Assume that the sequence isn't full.
    public void add(T toBeAdded) {
    	if (myCount == myValues.length) {
    		System.err.println("Error: Sequence full.");
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
		if (insertPos < 0 || insertPos >= myValues.length) {
			return;
		}
		T temp = toInsert;
		for (int i = insertPos; i < myValues.length; i++) {
			T oldTemp = temp;
			temp = myValues[i];
			myValues[i] = oldTemp; 
		}
		myCount++;
    }

    public boolean isEmpty() {
    	return myCount == 0;
    }
    
    public int size() {
    	return myCount;
    }
    
    public T elementAt(int pos) {
    	if (pos < 0 || pos >= myCount) {
    		System.err.println("Error: Index out of bounds.");
    		System.exit(1);
    	}
    	return myValues[pos];
    }
    
    public T remove(int pos) {
		if (pos < 0 || pos >= myValues.length) {
    		System.err.println("Error: Index out of bounds.");
    		System.exit(1);
		}
		T val = myValues[pos];
		for (int i = pos; i < myValues.length - 1; i++) {
			myValues[i] = myValues[i + 1];
		}
		myValues[myValues.length - 1] = null;
		myCount--;
		return val;
    }
    
    public boolean contains(T val) {
    	for (int i = 0; i < myCount; i++)
    		if (myValues[i].equals(val)) return true;
    	return false;
    }
    
    public String toString() {
    	String result = "";
    	for (int i = 0; i < myCount; i++) {
    		if (i != 0)
    			result += " ";
    		result += myValues[i];
    	}
    	return result;
    }
    
    public Iterator<T> iterator() {
    	return new SequenceIterator<T>();
    }
    
    private class SequenceIterator<T> implements Iterator<T> {
    	int pos;
    	public SequenceIterator() {
    		pos = -1;
    	}
    	public T next() {
    		pos++;
    		return (T) myValues[pos];
    	}
    	public boolean hasNext() {
    		return pos < myCount - 1;
    	}
    	public void remove() {
    		throw new UnsupportedOperationException();
    	}
    }
    
    public static void main(String[] args) {
    	Sequence<String> words = new Sequence<String>(10);
    	words.add("Hello!");
    	words.add("How");
    	words.add("are");
    	words.add("you?");
    	for (String s : words) System.out.println(s);
    }
}
