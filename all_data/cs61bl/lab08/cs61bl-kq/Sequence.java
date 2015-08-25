import java.util.Iterator;

public class Sequence <T> {

    // instance variables
    protected T[] myValues;   // sequence elements
    int myCount;                // number of array cells used by sequence

    
    @SuppressWarnings("unchecked")
	public Sequence(int capacity) {
     myValues = (T[]) new Object[capacity]; 
    }
    
    public Iterator Iterator() {
    	return new Iterator();
    }
    
    class Iterator {
    	
    	private int index = 0;
    	public void initIterator() {
    		index = 0;
    	}
    	public boolean hasNext() {
    		return index < myCount;
    	}
    	
    	public T next() {
    		return !hasNext() ? null: myValues[index++]; //boolean statement ? true result : false result;
    	}
		
	}
    // Add the argument to the sequence by placing it in the first
    // unused spot in the array and incrementing the count.
    // Assume that the sequence isn't full.
    public void add(T toBeAdded) {
    	if (this.myCount >= myValues.length) {
    		System.err.println("Unable to access.");
    		System.exit(1);
    	}
    	else {
    		this.myValues[myCount] = toBeAdded;
    		this.myCount++;
        // YOUR CODE HERE
    	}
    }

    // Insert toInsert into the sequence at position insertPos,
    // shifting the later elements in the sequence over to make room
    // for the new element.
    // Assumptions: The array isn't full, i.e. myCount < myValues.length
    // Also, insertPos is between 0 and myCount, inclusive.
    public void insert(T toInsert, int insertPos) {
    	int x = 0;
        for (int k = insertPos + 1; k <= myCount; k++) {
            myValues[myCount-x] = myValues[myCount-x-1];
            x++;
        }
        myValues[insertPos] = toInsert;
        myCount++;
    }
    public void remove(int pos) {
    	int x = 0;
    	for (int y = pos + 1; y <= myCount; y++)
    		myValues[pos+x] = myValues[pos+x+1];
    	x++;
    	myCount--;
    }
    public boolean isEmpty() {
    	if (this.myCount == 0) {
    		return true;
    	}
    	else {
    		return false;
    	}
    }
    public int size() {
    	return myCount;
    }
    	
    public T elementAt(int pos){
    	if (pos >= myCount) {
    		System.err.println("Unable to access.");
    		System.exit(1);
    		return null;
    	}
    	else 
    		return myValues[pos];
		
    }
    
    public String toString() {
    	String s = String.valueOf(myValues[0]);
    	for (int x = 1; x < myCount; x++) {
    		s = s+ " " + String.valueOf(myValues[x]);
    	}
    	return s;
    }
    // other methods go here
    public boolean contains(T k) {
    	for (int x = 0; x < myCount; x++) {
    		if (myValues[x] == k) {
    			return true;
    		}
    	}
    	return false;
    	
    }

}





