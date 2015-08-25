public class IntSequence {

    // instance variables
    protected int[] myValues;   // sequence elements
    int myCount;                // number of array cells used by sequence

    // constructor
    // capacity: actual size of the array or the (temporary) maximum
    // number of elements it can hold
    public IntSequence(int capacity) {
        // YOUR CODE HERE
    	myValues = new int[capacity];
    	myCount = 0;
    }

    // Add the argument to the sequence by placing it in the first
    // unused spot in the array and incrementing the count.
    // Assume that the sequence isn't full.
    public void add(int toBeAdded) {
        // YOUR CODE HERE
    	if (myCount == myValues.length) {
    		System.err.println("Sequence full.");
    		System.exit(1);
    	}
    	myValues[myCount] = toBeAdded;
    	myCount ++;
    }

    // Insert toInsert into the sequence at position insertPos,
    // shifting the later elements in the sequence over to make room
    // for the new element.
    // Assumptions: The array isn't full, i.e. myCount < myValues.length
    // Also, insertPos is between 0 and myCount, inclusive.
    public void insert(int toInsert,int insertPos){
    	for(int q=myCount+1;q>insertPos;q--){
    		myValues[q]=myValues[q-1];
    		}
    	myValues[insertPos]=toInsert;
    	myCount++;
    	}

    // other methods go here
    public boolean isEmpty() {
    	if (myCount == 0) return true;
    	else return false;
    }
    public int size() {return myCount;}
    
    public int elementAt(int pos) {
    	if (pos>=myCount) {
    		System.err.println("Index out of range.");
    		System.exit(1);
    	}
    	return myValues[pos];
    }
    public String toString(){
    	String res="";
    	for(int q = 0;q<myCount;q++){
    		res+=myValues[q]+" ";
    	}
    	return res.substring(0, res.length()-1);
    }

	public int remove(int pos){ 
		int q = myValues[pos];
		for(int x=pos; x < myCount; x++){
				myValues[x]=myValues[x+1];
		}
		myCount--;
		return q;
	}
	public boolean contains(int val) {
		for (int i = 0; i < myCount; i++) {
			if (myValues[i] == val) {
				return true;
			}
		}
		return false;
	}
}

