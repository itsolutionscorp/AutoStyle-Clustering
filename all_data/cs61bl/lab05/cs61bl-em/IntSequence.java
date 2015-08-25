public class IntSequence {

    // instance variables
    protected int[] myValues;   // sequence elements
    int myCount;                // number of array cells used by sequence

    // constructor
    // capacity: actual size of the array or the (temporary) maximum
    // number of elements it can hold
    public IntSequence(int capacity) {
        this.myValues = new int[capacity];
    }

    public boolean contains(int k) {
    	for (int i = 0; i < myCount; i ++) {
    		if (this.myValues[i] == k) {
    			return true;
    		}
    	}
    	return false;
    }
        
    public void removezeroes () {
    	for (int i = 0; i < myCount; i ++) {
    		if (myValues[i] == 0) {
    			remove(i); //eek! i put this.myValues.remove(i); on the quiz!
    			i --;
    		}
    	}
    }
    
    // Add the argument to the sequence by placing it in the first
    // unused spot in the array and incrementing the count.
    // Assume that the sequence isn't full.
    public void add(int toBeAdded) {
    	if (myCount < this.myValues.length) { 
    		this.myValues[myCount] = toBeAdded;
    		myCount ++;
    	}
    	else {
    		System.err.println("sequence full");
    		System.exit(1);
    	}
    }

    public int remove(int toRemove) {
    	int removed = this.myValues[toRemove];
    	for (int i = 0; i <= myCount; i++) {
    		if (i < toRemove) {
    			continue;
    		}
    		else {
    			this.myValues[i] = this.myValues[i+1];
    		}    		
    	}
    	myCount --;
    	return removed;
    }   
    
    // Insert toInsert into the sequence at position insertPos,
    // shifting the later elements in the sequence over to make room
    // for the new element.
    // Assumptions: The array isn't full, i.e. myCount < myValues.length
    // Also, insertPos is between 0 and myCount, inclusive.
    public void insert(int toInsert, int insertPos) {
        /*for (int k = insertPos + 1; k <= myCount; k++) {
            myValues[k] = myValues[k-1];
        }
        myValues[insertPos] = toInsert;
        myCount++;
        */    	
    	
        if (insertPos < 0 || insertPos > myCount) {
			return;
		}
        
		int temp = this.myValues[insertPos];
		this.myValues[insertPos] = toInsert;
		int temp2 = 0;
		
		for (int i = 0; i <= myCount; i ++) {
			if (i <= insertPos) {
				continue;
			}
			else {
				temp2 = this.myValues[i];
				this.myValues[i] = temp;
				temp = temp2;
			}
		}
		myCount++;        
    }

    public boolean isEmpty() {
    	return (myCount == 0);
    }
    
    public int size() {
    	return myCount;
    }
    
    public int elementAt (int pos) {
    	if (pos >= myCount) {
    		System.err.println("no element in requested position");
    		System.exit(1);
    		return 0; 
    	}
    	else {    		    		
    		return this.myValues[pos];
    	}
    }
    
    public String toString() {
    	String s = new String("");
    	if (this.isEmpty()) {
    		return "";
    	}
    	for (int i = 0; i < myCount - 1; i ++) {
    		s = s + this.myValues[i] + " ";
    	}
    	s = s + this.myValues[myCount-1];
    	return s;
    }

}

