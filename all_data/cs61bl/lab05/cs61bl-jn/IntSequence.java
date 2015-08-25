public class IntSequence {

    // instance variables
    protected int[] myValues;   // sequence elements
    int myCount;                // number of array cells used by sequence

    // constructor
    // capacity: actual size of the array or the (temporary) maximum
    // number of elements it can hold
    public IntSequence(int capacity) {
        myCount = capacity;
        myValues = new int[50];
    }

    // Add the argument to the sequence by placing it in the first
    // unused spot in the array and incrementing the count.
    // Assume that the sequence isn't full.
    public void add(int toBeAdded) {
        if (myCount >= myValues.length) {
        		System.err.println("no more space to add");
        		System.exit(1);
        } else {
        		myValues[myCount] = toBeAdded;
        		myCount += 1;
        }
    		
    }

    // Insert toInsert into the sequence at position insertPos,
    // shifting the later elements in the sequence over to make room
    // for the new element.
    // Assumptions: The array isn't full, i.e. myCount < myValues.length
    // Also, insertPos is between 0 and myCount, inclusive.
    public void insert(int toInsert, int insertPos) {
    		if (insertPos > myCount) {
			System.err.println("position out of bounds");
			System.exit(1);}
    		else{
    			ArrayOperations.insert(myValues, insertPos, toInsert);
    			myCount++;
    		}
    }

    public boolean isEmpty() {
    		for (int i = 0; i < myCount; i++) {
    			if (myValues[i] != 0) {
    				return false;
    			}
    		}
    		return true;
    }
    
    public int size() {
    		return myCount;
    }
    
    public int elementAt(int pos) {
    		if (pos >= myCount) {
    			System.err.println("position out of bounds");
    			System.exit(1);
    			return 0;
    		} else {
    			return myValues[pos];
    		}
    }
    
    public String toString() {
    		String start = "";
    		if (myCount > 0) {
    			start = start + myValues[0];
    			for (int i = 1; i < myCount; i++) {
    				start = start + " " + myValues[i];
    			}
    		}
    		return start;
    }
    
    public void remove(int pos) {
    		if (pos >= myCount) {
    			System.err.println("position out of bounds");
    			System.exit(1);
    		} else {
    			ArrayOperations.remove(myValues, pos);
    			myCount--;
    		}
    }
    
    public boolean contains(int k) {
    		for (int i = 0; i < myValues.length; i++) {
    			if (myValues[i] == k) {
    				return true;
    			}
    		}
    		return false;
    }
}

