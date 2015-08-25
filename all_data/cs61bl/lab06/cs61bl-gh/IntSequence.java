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
    }

    // other methods go here
    public boolean isEmpty(){
	    if (myCount == 0){
	    	return true;
	    }
	    return false;
    }


    public int size(){
    return myCount;
    }

    
    public int elementAt(int pos){
	    if (pos > this.myValues.length){
		    System.err.println("Out of Index");
		    System.exit(1);
	    }
	    return this.myValues[pos];
    }
    
    // Add the argument to the sequence by placing it in the first
    // unused spot in the array and incrementing the count.
    // Assume that the sequence isn't full.

    public void add(int toBeAdded) {
        // YOUR CODE HERE
	    if (myCount-1 >= myValues.length){
		    System.err.println("Out of Index");
		    System.exit(1);
	    }
	    else{
	    	myValues[myCount] = toBeAdded;
	    	myCount++;
	    }
    }

    public String toString(){
    	String result = new String(""+ myValues[0]);
    	for(int i=1; i <= myCount-1; i++){
    		String adding = new String(" "+ myValues[i]);
    		result = result + adding;
    	}
    	return result;
    }

    public void remove (int pos) {
    	if (pos < 0 || pos >= myValues.length) {
			return;
		}
		// YOUR CODE HERE
    	for (int i=pos; i<myCount-1; i++){
    		myValues[i] = myValues[i+1];
    	}
		myCount--;
    }
    
    public void insert(int toInsert, int insertPos) {
        for (int k = insertPos+1, temp = myValues[k-1]; k <= myCount; k++) {
        	int temp2 = myValues[k];
        	myValues[k] = temp;
        	temp = temp2;
        }
        myValues[insertPos] = toInsert;
        myCount++;
    }


    
    

}
