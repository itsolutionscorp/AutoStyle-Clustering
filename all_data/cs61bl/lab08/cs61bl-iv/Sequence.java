import java.util.Iterator;

public class Sequence <T> implements Iterable<T> {

	public static void main (String[] args) {
		Sequence<Integer> intSeq = new Sequence<Integer>(5);
		intSeq.add(1);
		intSeq.add(2);
		intSeq.add(3);
		System.out.println(intSeq.toString());
		intSeq.remove(1);
		System.out.println(intSeq.toString());
		System.out.println(intSeq.size());
		
		Sequence<String> stringSeq = new Sequence<String>(5);
		stringSeq.add("a");
		stringSeq.add("b");
		stringSeq.add("c");
		System.out.println(stringSeq.toString());
		stringSeq.remove(1);
		System.out.println(stringSeq.toString());
		System.out.println(stringSeq.size());
		
		Sequence<Integer> intSeq2 = new Sequence<Integer>(5);
		intSeq2.add(1);
		intSeq2.add(2);
		intSeq2.add(3);
		
		for (int k: intSeq2) {
			System.out.print(k);
		}
		
	}
	
	private class SequenceIterator implements Iterator<T> {
		
		private int index;
		
		public SequenceIterator() {
			index = -1;
		}
		
		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return ((index + 1) < myCount);
		}

		@Override
		public T next() {
			// TODO Auto-generated method stub
			index++;
			return myValues[index];
		}
		
	}
	
    // instance variables
    protected T[] myValues;   // sequence elements
    int myCount;                // number of array cells used by sequence

    // constructor
    // capacity: actual size of the array or the (temporary) maximum
    // number of elements it can hold
    public Sequence(int capacity) {
        // YOUR CODE HERE
    	myValues = (T[]) new Object[capacity];
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

    // Add the argument to the sequence by placing it in the first
    // unused spot in the array and incrementing the count.
    // Assume that the sequence isn't full.
    public void add(T toBeAdded) {
        // YOUR CODE HERE
    	if (myCount < myValues.length) {
    		myValues[myCount] = toBeAdded;
        	myCount++;
    	}
    	else {
    		System.err.println("myValues is full.");
    		System.exit(1);
    	}
    	
    }
    
    public String toString() {
    	String myString = "";
    	if (myCount > 0) {
    		for (int k = 0; k < myCount - 1; k++) {
        		myString = myString + myValues[k] + " ";
        	}
        	myString = myString + myValues[myCount-1];
    	}
    	else {
    		myString = null;
    	}
    	
    	return myString;
    }
    
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

    // Insert toInsert into the sequence at position insertPos,
    // shifting the later elements in the sequence over to make room
    // for the new element.
    // Assumptions: The array isn't full, i.e. myCount < myValues.length
    // Also, insertPos is between 0 and myCount, inclusive.
    public void insert(T toInsert, int insertPos) {
    	T[] temp = (T[]) new Object[myCount];
    	
    	for (int k = 0; k < myCount; k++) {
			temp[k] = myValues[k];
		}
    	
        for (int k = insertPos + 1; k <= myCount; k++) {
            myValues[k] = temp[k-1];
        }
        myValues[insertPos] = toInsert;
        myCount++;
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
    // other methods go here

	@Override
	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		return new SequenceIterator();
	}

}

