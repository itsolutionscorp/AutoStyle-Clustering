public class IntSequence {

    // instance variables
    protected int[] myValues;   // sequence elements
    int myCount;                // number of array cells used by sequence

    // constructor
    // capacity: actual size of the array or the (temporary) maximum
    // number of elements it can hold
    public IntSequence(int capacity) {
        // YOUR CODE HERE
    	myValues= new int[capacity];
    			myCount =0;
    }

    // Add the argument to the sequence by placing it in the first
    // unused spot in the array and incrementing the count.
    // Assume that the sequence isn't full.
    public void add(int toBeAdded) {
        // YOUR CODE HERE
    	if(myCount == myValues.length){
    		System.out.println("");
    		System.exit(1);
    		
    	}
    	else{
 
    	for (int i = myValues.length-1; i>0;i--){
    		myValues[i]= myValues[i-1];
    	}
    	myValues[0]= toBeAdded;
    	
    	myCount++;}
    }

    // Insert toInsert into the sequence at position insertPos,
    // shifting the later elements in the sequence over to make room
    // for the new element.
    // Assumptions: The array isn't full, i.e. myCount < myValues.length
    // Also, insertPos is between 0 and myCount, inclusive.
    public void insert(int toInsert, int insertPos) 
    {if (insertPos <myCount){
        for (int k = myValues.length-1; k > insertPos; k--) {
            myValues[k] = myValues[k-1];
        }
        myValues[insertPos] = toInsert;
        myCount++;}
    else{if (insertPos== myCount){
        	myValues[insertPos] = toInsert;
        	myCount++;}
        }
    }
    
    
    // other methods go here
    public boolean isEmpty(){
    	boolean result;
    	result = myCount==0;
    	return result;
    	
    }
  
    	
    	
    public int size(){
    	return myCount;
    }
    public int elementAt(int pos){
    	if(pos>=myCount){
    	System.err.println();
    	System.exit(1);
    	}
    	

        	return myValues[pos];
    	
    }
    public String toString(){
    	String result = new String();
    	result = Integer.toString(myValues[0]);
    	for( int i = 1;i<myCount;i++){
    		result +=" "+ myValues[i];
    	}
    	
    	
    	return result;
    }
    public void remove(int pos){
    	for (int i =pos;i < myCount;i++){if (i == myCount-1)
    {myValues[i]=0;}else{
		
		myValues[i]= myValues[i+1];} 
			
		}
		myCount--;
    	
    }
    public boolean contains(int k){
    	boolean result;
    	result = false;
    
    	for( int i =0;i<myCount;i++){
    		if(myValues[i]==k){
    			result = true;
    			break;}
    		
    	}
    	
    	
    	return result;
    }
    
  
}

