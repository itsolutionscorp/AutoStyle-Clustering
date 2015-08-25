public class IntSequence {

    // instance variables
    protected int[] myValues;   // sequence elements
    int myCount;                // number of array cells used by sequence

    // constructor
    // capacity: actual size of the array or the (temporary) maximum
    // number of elements it can hold
    public IntSequence(int capacity) {
        myValues = new int [capacity];
        myCount = 0;
    }

    // Add the argument to the sequence by placing it in the first
    // unused spot in the array and incrementing the count.
    // Assume that the sequence isn't full.
    public void add(int toBeAdded) {
        if (myCount >= myValues.length){
        	System.err.println("There is no more room to store the integer");
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
    
    public void insert (int toInsert, int insertPos) {
    	if (insertPos < 0 || insertPos > myCount) {
			return;
		}
		int x = myValues[insertPos];
		myValues[insertPos] = toInsert;
		for (int i = insertPos; i < myValues.length; i++){
			if (i+1 == myValues.length){
				break;
			}
			int temp = myValues[i+1];
			myValues [i+1] = x;
			x = temp;
		}
		myCount++;
    }

    
    // Given the position, removes the specified element and returns it
    public int remove(int pos){
    	if (pos < 0 || pos >= myValues.length) {
    		System.err.println("There is no element at the position given");
    		System.exit(1);
    	}
    	int temp = myValues[pos];
    	int x;
		for (int i = pos; i < myCount; i++){
			if (i+1 == myCount){
				myValues[i] = 0;
				break;
			}
			x = myValues [i+1];
			myValues[i] = x;
		}
    	myCount--;
    	return temp;
    }

    // returns true when the sequence is empty and returns false otherwise
    public boolean isEmpty(){
    	return (myCount == 0);
    }

    // returns the number of values in the sequence
    public int size(){
    	return myCount;
    }
    
    // returns the value at the given position in the sequence
    public int elementAt(int pos){
    	if (pos >= myValues.length){
    		System.err.println("The index is over the given capacity");
    		System.exit(1);
    	} else if (pos < 0) {
    		System.err.println("The index does not exist");
    		System.exit(1);
    	}
    	return myValues[pos];
    }
    
    public boolean contains(int k){
    	boolean contained = false;
    	for (int i = 0; i < myCount; i++){
    		if (myValues[i] == k){
    			contained = true;
    		}
    	}
    	return contained;
    }
    
    public String toString() {
		String s = "";
    	for (int i = 0; i < myCount; i++){
    		if (i == 0){
    			s += myValues[i];
    		} else {
    			s += " " + myValues[i];
    		}
    	}
    	System.out.println(s);
    	return s;
	}
    
}

