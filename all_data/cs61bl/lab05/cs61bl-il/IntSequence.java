public class IntSequence {

    // instance variables
    protected int[] myValues;   // sequence elements
    int myCount;                // number of array cells used by sequence

    // constructor
    // capacity: actual size of the array or the (temporary) maximum
    // number of elements it can hold
    public IntSequence(int capacity) {
    if (capacity <= 0)
     {
        System.err.println("Too low Capacity");
        System.exit(0);
     }
     myValues = new int[capacity];
     myCount = 0;
    }

    // Add the argument to the sequence by placing it in the first
    // unused spot in the array and incrementing the count.
    // Assume that the sequence isn't full.
    public void add(int toBeAdded) {
    	if (myCount == myValues.length){
 		   System.err.println("No Space available");
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
    public void insert(int toInsert, int insertPos) {
    	
    	myValues[myCount-1] = myValues[myCount]; // shift the last element to the last+1
    	for (int i = myCount-1; i>insertPos;i--){
    		myValues[i] = myValues[i-1];			
		}
    	myValues[insertPos] = toInsert;        
        myCount++;
    }

    // other methods go here
    public boolean isEmpty(){
    	return myCount == 0;
    }
    
    public int size(){
    	return myCount;
    }
    
    public int elementAt(int pos){    	
    
    if (pos < 0 || pos > myCount)
    {
    	System.err.println("Out of Bound");
    	System.exit(1);
    }
    return myValues[pos];
   }
    
   @Override 
   public String toString(){
	   if (myCount == 0) return "";
	   
	   String temp = new String(String.valueOf(myValues[0]));
	   for (int i = 1; i < myCount; i++){
		   temp = temp+" " + myValues[i];
	   }	   
	   return temp;
   }
    
   public void remove(int pos){
   	  if (pos < 0 || pos > myCount)
	  {
	    System.err.println("Out of Bound");
	    System.exit(1);
	  }
	   
	   for (int i = pos; i < myCount-1; i++){
		   myValues[i] = myValues[i+1];
		}
	   myValues[myCount-1] = 0;	   
	   myCount--;
	   
   }
   
   public boolean contains(int k){
	   for (int i = 0; i<myCount;i++){
		   if (myValues[i] == k) return true;
	   }
	   return false;
   }
}

