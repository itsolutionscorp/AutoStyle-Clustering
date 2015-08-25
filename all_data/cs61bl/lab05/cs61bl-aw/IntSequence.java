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
    	if (myCount == myValues.length) {
    		System.err.println("The sequence is full");
    		System.exit(1);
    	}
    	insert(toBeAdded, myCount);
    }

    // Insert toInsert into the sequence at position insertPos,
    // shifting the later elements in the sequence over to make room
    // for the new element.
    // Assumptions: The array isn't full, i.e. myCount < myValues.length
    // Also, insertPos is between 0 and myCount, inclusive.
  
    /*
    public void insert(int toInsert, int insertPos) {
        for (int k = insertPos + 1; k <= myCount; k++) {
            myValues[k] = myValues[k-1];
        }
        myValues[insertPos] = toInsert;
        myCount++;
    }
*/
    public boolean contains (int k) {
    	for (int i = 0; i < myCount; i++) {
    		if (myValues[i] == k) {
    			return true; 
    		}
    	}
    	return false; 
    }
	public void insert  (int newInt, int pos) {
		int a = 0; 
		int b = 0;
		int c = 0; 
		myCount++;
		for (int i = 0; i < myCount; i++) {
			if (i <= pos){
				myValues[i] = myValues[i];
			} else if(i > pos){		
				if (a == 0) {
					b = myValues[i];
					myValues[i] = myValues[i - 1];
					a = 1;
				}else if (a == 1) { 
					c = myValues[i]; 
					myValues[i] = b;
					a = 2;
				}
				else if (a == 2) { 
					b = myValues[i];
					myValues[i] = c; 
					a = 1;
				}
			}
		}
			
			myValues[pos] = newInt;	

		// YOUR CODE HERE
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
    public int elementAt(int pos) {//it seems I need to add a return integer at the end of the if b/c I get the x mark on the left 
    	//saying so. But this is odd since I was given the system.exit(1) to exit the method. Ask Amit
    	if ((pos < 0) || (pos > (myValues.length - 1))) { 
    		System.err.print("We can not retrive an element because there is no element at the index  ");
    		System.exit(1);
    		return pos; 
    	} else {
    	return myValues[pos]; 
    	}
    }
    public String toString() {
    	String sentence = "";
    	for (int i = 0; i < myCount; i++) {
    		if (i == (myCount - 1)) {
    			sentence += myValues[i];
    		} else { 
    			sentence += myValues[i] + " ";
    		}
    	}
    	return sentence; 
    }
    public int remove(int pos) { 
    	if ((pos < 0) || (pos >= myCount)) {
    		System.err.print("There is nothing to remove because there is no element at the index  ");
        		System.exit(1); //not working, need to ask Amit
        		return pos;
    	} else {
    	int specificElement = myValues[pos];
    	int i = 0; 
    	while (i < (myCount - 1)) { 
    		if (i < pos) { 
    			myValues[i] = myValues[i];
    			
    		} else {
    			myValues[i] =myValues[i + 1];
    		}
    		i++;
    }
    	myCount--; 

    	return specificElement; 
    }
    }
}
    /*
Supply a toString method for the IntSequence class. It will return a String that contains the elements of the 
sequence separated by blanks. Please make sure that there is just one space between each element, and no trailing spaces on 
either side of the string.
}
     */



