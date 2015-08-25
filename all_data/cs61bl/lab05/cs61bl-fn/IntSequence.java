public class IntSequence {

    // instance variables
    protected int[] myValues;   // sequence elements
    int myCount;                // number of array cells used by sequence

    // constructor
    // capacity: actual size of the array or the (temporary) maximum
    // number of elements it can hold
    public IntSequence(int capacity) {
        myValues=new int[capacity];
    	// YOUR CODE HERE
    }

    // Add the argument to the sequence by placing it in the first
    // unused spot in the array and incrementing the count.
    // Assume that the sequence isn't full.
    public void add(int toBeAdded) {
    	myValues[myCount]=toBeAdded;
        myCount++;
    }

    // Insert toInsert into the sequence at position insertPos,
    // shifting the later elements in the sequence over to make room
    // for the new element.
    // Assumptions: The array isn't full, i.e. myCount < myValues.length
    // Also, insertPos is between 0 and myCount, inclusive.
    public void insert(int toInsert, int insertPos) {
    	if ( insertPos< 0 || insertPos >= myCount) {
    		System.err.println("Index does not exist");
    		System.exit(1);
		}
		for (int k=myCount;k>insertPos;k--) {
			myValues[k]=myValues[k-1];
		}
		myValues[insertPos]=toInsert;
        myCount++;
    }

    // other methods go here
    public boolean isEmpty() {
    	return myCount==0;
    }
    public int size() {
    	return myCount;
    }
    public int elementAt(int pos) {
    	if (pos<0 || pos>=myCount) {
    		System.err.println("Index does not exist");
    		System.exit(1);
    	}
    	return myValues[pos];
    }
    public String toString() {
    	String myString=new String();
    	for (int k=0;k<myCount;k++) {
    		if (k==0) {
    		myString+=myValues[k];
    		} else {
    		myString+=" "+myValues[k];
    		}
    	}
    	return myString;
    }
    public void remove(int pos) {
    	if (pos < 0 || pos >= myCount) {
    		System.err.println("Index does not exist");
    		System.exit(1);
		}
		for (int k=pos;k<myValues.length-1;k++) {
			myValues[k]=myValues[k+1];
		}
		myCount--;
    }
    public boolean contains(int toTest) {
    	boolean bool=false;
    	for (int k = 0; k<myCount;k++) {
    		if (myValues[k]==toTest) {
    			bool=true;
    			break;
    		}
    	}
    	return bool;
    }
}
