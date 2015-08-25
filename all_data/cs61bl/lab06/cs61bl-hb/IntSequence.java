public class IntSequence {

    // instance variables
    protected int[] myValues;   // sequence elements
    int myCount;                // number of array cells used by sequence

    // constructor
    // capacity: actual size of the array or the (temporary) maximum
    // number of elements it can hold
    public IntSequence(int capacity) {
        // YOUR CODE HERE
    	int [] myValues = new int [capacity];
    }

    // Add the argument to the sequence by placing it in the first
    // unused spot in the array and incrementing the count.
    // Assume that the sequence isn't full.
    public void add(int toBeAdded) {
        // YOUR CODE HERE
    	myValues[myCount] = toBeAdded ;
    	myCount ++ ;
    	
    }

    // Insert toInsert into the sequence at position insertPos,
    // shifting the later elements in the sequence over to make room
    // for the new element.
    // Assumptions: The array isn't full, i.e. myCount < myValues.length
    // Also, insertPos is between 0 and myCount, inclusive.
    public void insert(int toInsert, int insertPos) {
        for (int k = myCount - 1; k >= insertPos ; k--) {
            myValues[k+1] = myValues[k];
        }  
        myValues[insertPos] = toInsert;    
        myCount++;
    }

    // other methods go here
    public boolean isEmpty(){
    	if( myCount == 0 ){
    		return true;
    	}
    	return false;
    }
    
    public int size (){
    	return myCount;
    }
    
    public int elementAt( int pos ){
    	if (pos >= myCount )
    	{
    		System.err.println();
    		System.exit(1);
    	}
    	return myValues [pos] ;
    }
    public String toString(){
        String string1 = new String() ;
        for ( int count = 0 ; count < myCount ; count++){
        string1+=(myValues[count]+" ");
        		}
    	return string1;
    }
    public void remove(int pos){
    	for ( int count = pos + 1 ; count < myCount ; count++){
    		myValues[ count - 1 ] = myValues [ count ];
    	}
    	myCount--;
  
    	
    }
    public boolean contains (int k){
    	for ( int count = 0 ; count < myCount ; count++){
    		if(myValues[count] == k){
    			return true ;
    		}
    		
    	}
    	return false;	
    }

}

