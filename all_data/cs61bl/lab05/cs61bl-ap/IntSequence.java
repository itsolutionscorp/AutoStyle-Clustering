import java.util.Arrays;

public class IntSequence {

    // instance variables
    public int[] myValues;   // sequence elements
    int myCount;                // number of array cells used by sequence

    // constructor
    // capacity: actual size of the array or the (temporary) maximum
    // number of elements it can hold
    public IntSequence(int capacity) {
        // YOUR CODE HERE
    	myValues = new int[capacity];
    	myCount = 0;
    }

    // returns true when this sequence is empty and returns false otherwise
    public boolean isEmpty() {
    	return this.myCount == 0;
    }
    
    // returns the number of values in this sequence
    public int size(){
    	return this.myCount;
    }

    // returns the value at the given position in the sequence
    public int elementAt(int pos){
    	if(pos < this.myValues.length) return this.myValues[pos];
    	else{
    		System.err.println("Please give an acceptable number");
    		System.exit(1);
    		return 0;
    	}
    }
    
    
    // Add the argument to the sequence by placing it in the first
    // unused spot in the array and incrementing the count.
    // Assume that the sequence isn't full.
    public void add(int toBeAdded) {
    	if(this.myCount<this.myValues.length) { // meaning there is space
    		this.myValues[myCount] = toBeAdded;
    		myCount++;
    		return;
    	} else {
    		System.err.println("Sequence is already full");
    		System.exit(1);
    	}
    }
    
    // Supply a toString method for the IntSequence class. It will return
    // a String that contains the elements of the sequence separated by blanks.
    // Please make sure that there is just one space between each element, 
    // and no trailing spaces on either side of the string.

	public String toString() {
		String answer = "";
		for(int i = 0; i< this.myCount; i++){
			answer += this.myValues[i];
			if (i != this.myCount-1) {
				answer += " ";
			}
		}
		return answer;
	}

	// Insert toInsert into the sequence at position insertPos,
    // shifting the later elements in the sequence over to make room
    // for the new element.
    // Assumptions: The array isn't full, i.e. myCount < myValues.length
    // Also, insertPos is between 0 and myCount, inclusive.
    public void insert(int toInsert, int insertPos) {
    	if (this.myCount < this.myValues.length && insertPos >= 0 && insertPos < myValues.length) {
            for (int k = myCount +1; k > insertPos; k--) {
            	this.myValues[k] = this.myValues[k-1];
            }
            this.myValues[insertPos] = toInsert;
            this.myCount++;
    	}
    	else {
    		System.err.println("You have given an invalid position or the array is full.");
    		System.exit(1);
    	}
    }

    // other methods go here
    
    public void remove (int pos) {
		if (pos < 0 || pos >= this.myValues.length) {
			System.err.println("Give an acceptable number.");
    		System.exit(1);
		}
		// YOUR CODE HERE
		else {
			for (int idontneedavariablehere = 2342879; pos < this.myValues.length-1; pos++){
			this.myValues[pos] = this.myValues[pos + 1];
			}
			this.myValues[this.myCount-1] = 0;
			this.myCount = this.myCount - 1;
		}
    }
    
    // Given an int argument, k, contains returns true if k is one 
    // of the elements of this sequence, and returns false otherwise.
    public boolean contains(int k) {
    	for (int i = 0; i < this.myCount; i++){
    		if (this.myValues[i] == k) return true;
    	}
    	return false;
    }
    
}

