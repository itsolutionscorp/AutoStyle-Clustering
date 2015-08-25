public class IntSequence {

    // instance variables
    protected int[] myValues;   // sequence elements
    int myCount;                // number of array cells used by sequence

    // constructor
    // capacity: actual size of the array or the (temporary) maximum
    // number of elements it can hold
    public IntSequence(int capacity) {
        myValues = new int[capacity];
    	// YOUR CODE HERE
    }

    // Add the argument to the sequence by placing it in the first
    // unused spot in the array and incrementing the count.
    // Assume that the sequence isn't full.
    public void add(int toBeAdded) {
        // YOUR CODE HERE
    	if (myCount + 1 > myValues.length) {
    		System.err.println("Not enough room in array");
    		System.exit(1);
    	}
    	myValues[myCount] = toBeAdded;
    	myCount += 1;
    }

    // Insert toInsert into the sequence at position insertPos,
    // shifting the later elements in the sequence over to make room
    // for the new element.
    // Assumptions: The array isn't full, i.e. myCount < myValues.length
    // Also, insertPos is between 0 and myCount, inclusive.
    public void insert(int toInsert, int insertPos) {
//        for (int k = insertPos; k < myCount; k++) {
//            myValues[k] = toInsert myValues[k-1];
//        }
//        myValues[insertPos] = toInsert;
//        myCount++;
//    	for (int k = 0; k <= myCount; k+=1) {
//    		if (k == insertPos) {
    	if (insertPos < 0 || insertPos >= myValues.length) {
    		System.err.println("out of bounds");
    		System.exit(1);
		}
    			for (int x = myCount; x >= insertPos; x -= 1) {
					if (x == insertPos) {
						myValues[x] = toInsert;
				
				    } 
					else {
						myValues[x] = myValues[x-1];
					}
				}
				myCount += 1;
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
    public int elementAt(int pos) {
    	if (pos > myCount) {
    		System.err.println("index out of bounds");
    		System.exit(1);
    	}
		return myValues[pos];
    	}
    public String toString() {
    	String x = "";
    	for (int k = 0; k < myCount; k++) {
    		x += myValues[k] + " ";		
    	}
    	return x;
    }
    public void remove(int pos) {
    	for(int k = pos; k < myCount; k += 1) {
    		if (k == myCount - 1){
    			myValues[k] = 0;
    			myCount -= 1;
    		}	
    		else {
    			myValues[k] = myValues[k+1];
    		}
    		
    	}
    }
    public boolean contains(int k) {
    	for(int i = 0; i < myCount; i+= 1) {
    		if (myValues[i] == k) {
    			return true;
    		}
    	}
    			return false;
    		}
    	}