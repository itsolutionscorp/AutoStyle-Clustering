public class Sequence<T> {

    // instance variables
    protected T[] myValues;   // sequence elements
    int myCount;                // number of array cells used by sequence
    private int iteratorindex;
    // constructor
    // capacity: actual size of the array or the (temporary) maximum
    // number of elements it can hold
    public Sequence(int capacity) {
        // YOUR CODE HERE
        myValues = (T[]) new Object [capacity];
    }
    // Add the argument to the sequence by placing it in the first
    // unused spot in the array and incrementing the count.
    // Assume that the sequence isn't full.
    public void add(T toBeAdded) {
        // YOUR CODE HERE
    	myValues[myCount] = toBeAdded ;
    	myCount ++ ;
    	
    }
    private class Iterator<t>{
   	 private void initIterator(){
   		 iteratorindex=0;
   	 }
        private boolean hasNext(){
       	 return iteratorindex!=myCount;
        }
        private T next(){
       	 T temp;
       	 temp=myValues[iteratorindex];
       	 iteratorindex++;
       	 return temp;      	 
        }
   }
    // Insert toInsert into the sequence at position insertPos,
    // shifting the later elements in the sequence over to make room
    // for the new element.
    // Assumptions: The array isn't full, i.e. myCount < myValues.length
    // Also, insertPos is between 0 and myCount, inclusive.
    public void insert(T toInsert, int insertPos) {
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
    
    public T elementAt( int pos ){
    	if (pos >= myCount )
    	{
    		System.err.println();
    		System.exit(1);
    	}
    	return myValues [pos] ;
    }
	public void initIterator(){
		iteratorindex=0;
	}
    public String toString(){
        String string1 = new String() ;
        string1+=(myValues[0]);
        for ( int count = 1 ; count < myValues.length ; count++){
        string1+=(" "+myValues[count]);
        		}
    	return string1;
    }
    public void remove(int pos){
    	for ( int count = pos + 1 ; count < myCount ; count++){
    		myValues[ count - 1 ] = myValues [ count ];
    	}
  
    	
    }
    public boolean contains (T k){
    	for ( int count = 0 ; count < myCount ; count++){
    		if(myValues[count] == k){
    			return true ;
    		}
    		
    	}
    	return false;	
    }


}

