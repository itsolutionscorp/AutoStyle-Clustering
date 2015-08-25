public class IntSequence {

    // instance variables
    protected int[] myValues;   // sequence elements
    int myCount;                // number of array cells used by sequence

    // constructor
    // capacity: actual size of the array or the (temporary) maximum
    // number of elements it can hold
    public IntSequence(int capacity) {
       int[] myValues=new int[capacity];
       
    }

    // Add the argument to the sequence by placing it in the first
    // unused spot in the array and incrementing the count.
    // Assume that the sequence isn't full.
    public void add(int toBeAdded) {
        // YOUR CODE HERE
    	if(myCount==myValues.length){
    		System.err.println("there is no spot to add");
    		System.exit(1);
    	}
        int i; 	
    	
    	myValues[myCount]=toBeAdded;		
    	
    	myCount++;
    }

    // Insert toInsert into the sequence at position insertPos,
    // shifting the later elements in the sequence over to make room
    // for the new element.
    // Assumptions: The array isn't full, i.e. myCount < myValues.length
    // Also, insertPos is between 0 and myCount, inclusive.
    public void insert(int toInsert, int insertPos) {
        for (int k = insertPos + 1; k <= myCount; k++) {
            myValues[k] = myValues[k-1];
        }
        myValues[insertPos] = toInsert;
        myCount++;
    }

    public boolean isEmpty(){
    int i;
    if(myCount==0) return true;     	
    else return false; 	
    	
    }
    public int size(){
    return myCount;	
    	
    }
    public int elementAt(int pos){
if(pos>(myCount-1)){
	System.err.println("there is nothing there");
	System.exit(1);	
}
    	return myValues[pos];
}
    public String toString(){
    	String a="";
    	int i;
    	int temp;
    	for(i=0;i<(myCount-1);i++){
    	
    		temp=myValues[i];
    		if(i<(myCount-2))
    		a=a+"temp"+"";
    		else
    		a=a+"temp";
    		
    	}
    	
    	
    return 	a;
    }
	public static void remove (int[] values, int pos) {
		if (pos < 0 || pos >= values.length) {
			return;
		}
		int i;
		for(i=pos;i<values.length-pos+1;i++){
		if(i<values.length-1)values[i]=values[i+1];
	

		}
		values[values.length-1]=0;// YOUR CODE HERE
	}
	public boolean contains(int k){
		int i;
		
		for(i=0;i<myValues.length;i++){
			if(myValues[i]==k){
				return true;
			}
			
		}
			return false;
	}
	
 // Add the argument to the sequence by placing it in the first
 // unused spot in the array and incrementing the count.
 // Assume that the sequence isn't full.

}
