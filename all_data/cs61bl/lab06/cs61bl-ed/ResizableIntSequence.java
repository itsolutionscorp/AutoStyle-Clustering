
public class ResizableIntSequence extends IntSequence {
	   // instance variables  // sequence elements              // number of array cells used by sequence
   // constructor
    // capacity: actual size of the array or the (temporary) maximum
    // number of elements it can hold
    public ResizableIntSequence(int capacity) {
        // YOUR CODE HERE
    	myValues = new int [capacity];
    	myCount = 0;
    }
    public void add(int toBeAdded) {
        // YOUR CODE HERE
    	int [] tmp_array = myValues;
    	if(myValues.length == myCount){    	
	    	myValues = new int [myValues.length+ 1];}
    	else{myValues = new int [myValues.length];
    	}
	    	myValues[0] = toBeAdded;
	    	myCount += 1;
	    	for (int k = 1; k< myValues.length; k+=1){
	    		myValues[k] = tmp_array[k-1]; }
	    	
    	
    	
    }
    
    public void insert(int toInsert, int insertPos) {
    	int tmp_myCount = myCount;
    	int [] tmp_array = myValues;
    	if(myValues.length == myCount){  
    		myValues = new int [myValues.length+ 1];
    	}
    	else{
    		myValues = new int [myValues.length];
    	}
        for (int k = 0; k <= tmp_myCount-insertPos-1; k+=1) {
            myValues[tmp_myCount-k] = tmp_array[tmp_myCount-k-1];
        }
        myValues[insertPos] = toInsert;
        for(int k = 0; k < insertPos; k+=1){
        	myValues[k] = tmp_array[k];
        }
        myCount++;
    }
    
   /*Threshold: when myCount is less than the capacity, then we delete the empty element.
   /This means myCount alway equal to capacity, on the other hand, the each spot in a array
   / is always occupied by one number.
    */
    public int remove(int removePos){
    	int result = myValues[removePos];
    	int [] tmp_array = myValues;
    	if(myCount <= myValues.length){
    		myValues = new int [myCount-1];    		
    	}
    	   	
    	for(int k = removePos;k < myCount-1; k += 1){
    		myValues[k] = tmp_array[k+1];
    	}
    	for(int k = 0; k < removePos; k+=1){
        	myValues[k] = tmp_array[k];
        }
    	
    	myCount -= 1;
    	return result;
    }
   
    
    
    
    public static void main(String[] args){
    	ResizableIntSequence test = new ResizableIntSequence(5);
		test.add(4);
		test.add(6);
		System.out.println(test);
    }
}