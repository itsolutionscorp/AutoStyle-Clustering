import java.util.ArrayList;
import java.util.Iterator;

public class Sequence <T>{

    // instance variables
    protected ArrayList<T> myValues;   // sequence elements
    int myCount = 0;
    Iterator<T> iter;
    // constructor
    // capacity: actual size of the array or the (temporary) maximum
    // number of elements it can hold
    public Sequence(int capacity) {
    	if (capacity<0) {
    		System.err.println("Capacity must be positive");
    		System.exit(1);
    	}
    	
    }
    
    public Iterator<T> iterator() {
    	iter = myValues.iterator();
    	return iter;
	}
		
    public boolean hasNext() {
    	return iter.hasNext();
    }
       
    public T next() {
    	return iter.next();
    }
	

    // Add the argument to the sequence by placing it in the first
    // unused spot in the array and incrementing the count.
    // Assume that the sequence isn't full.
    public void add(T toBeAdded) {
        
    	if (myCount + 1 >= myValues.size()) {
    		System.err.println("Not enough space");
    		System.exit(1);
    	} else {
    	myValues.add(toBeAdded);
        myCount++;
    	}
    }

    // Insert toInsert into the sequence at position insertPos,
    // shifting the later elements in the sequence over to make room
    // for the new element.
    // Assumptions: The array isn't full, i.e. myCount < myValues.length
    // Also, insertPos is between 0 and myCount, inclusive.
    public void insert(T toInsert, int insertPos) {
    	if (insertPos > myCount) {
    		System.err.println("Invalid Position");
    		System.exit(1);
    	} else {
        for (int k = insertPos + 1; k <= myCount; k++) {
            myValues.set(k,myValues.get(k-1));
        }
        myValues.add(insertPos, toInsert);  	
        myCount++;
    	}
    }

    // other methods go here
    public boolean isEmpty() {
    	return myCount == 0;    	
    }
    public int size() {
    	return myCount;
    }
    public T elementAt(int pos){
    	if (pos > myCount || pos < 0) {
    		System.err.println("Invalid Position!");
    		System.exit(1);
    	}
    	
    	return myValues.get(pos);
    }
    
    public void remove (int pos) {
//		myValues.remove(pos);

		for (int i = pos; i < myValues.size()-1; i++) {
			myValues.set(i, myValues.get(i+1));
		}
		myValues.set(myCount-1, null);
		myCount--;
	}
    
    public boolean contains(T k) {
    	boolean contain = false;
    	for (int i = 0; i < myValues.size() - 1; i++) {
    		if (myValues.get(i) == k) {
    			contain = true;
    		}
    	}
    	return contain;
    }
	
    public String toString() {
    	String returnString= new String();
    	for (int i = 0; i <= myCount - 1; i++) {
    		returnString = returnString + " " + myValues.get(i);
    	}
    	return returnString;
    	
    }
}

