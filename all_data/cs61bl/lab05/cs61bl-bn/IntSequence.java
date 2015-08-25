import java.util.Arrays;
public class IntSequence {

    // instance variables
    protected int[] myValues;   // sequence elements
    int myCount;                // number of array cells used by sequence

    // constructor
    // capacity: actual size of the array or the (temporary) maximum
    // number of elements it can hold
    public IntSequence(int capacity) {
      myValues = new int[capacity];
      myCount = 0;
    }

    // Add the argument to the sequence by placing it in the first
    // unused spot in the array and incrementing the count.
    // Assume that the sequence isn't full.
    public void add(int toBeAdded) {
    	if (myCount < myValues.length){
    		myValues[myCount] = toBeAdded;
    		myCount ++;
    	}else{
    		return;
    	}
    }
//    public void remove(int pos) {
//    	if (pos <= myCount){
//    		for ( int posi=pos; posi < myCount; posi++) {
//    			myValues[posi]=myValues[posi+1];
//    		}
//    		myCount--;
//    	}else{
//    		return;
//    	}
//    }
	public void remove(int[] values, int pos) {
		if (pos < 0 || pos >= values.length) {
			return;
		}
		// YOUR CODE HERE
		
		else {
			int[] ref = new int[values.length];
			for (int j = 0; j < values.length; j++) {
				ref[j] = values[j];
			}
			
			for (int k = 0; k < values.length; k++) {
				if (k < pos) {
					values[k] = ref[k];
				}else if (k == values.length-1){
					values[k] = 0;
				}
				else {
					values[k] = ref[k+1];
				} 
			}
		}
	}
    

    // Insert toInsert into the sequence at position insertPos,
    // shifting the later elements in the sequence over to make room
    // for the new element.
    // Assumptions: The array isn't full, i.e. myCount < myValues.length
    // Also, insertPos is between 0 and myCount, inclusive.
//    public void insert(int toInsert, int insertPos) {
//        for (int k = insertPos + 1; k <= myCount; k++) {
//            myValues[k] = myValues[k-1];
//        }
//        myValues[insertPos] = toInsert;
//        myCount++;
//    }
	public void insert(int[] values, int pos, int newInt) {
		if (pos < 0 || pos >= values.length) {
			return;
		}
		// YOUR CODE HERE
		else {
			int[] ref = new int[values.length];
			for (int j = 0; j < values.length; j++) {
				ref[j] = values[j];
			}
			
			for (int k = 0; k < values.length; k++) {
				if (k > pos) {
					values[k] = ref[k - 1];
				} else if (k == pos) {
					values[k] = newInt;
				} else {
					values[k] = values[k];
				}
			}
		}
	}
    
    public boolean isEmpty(){
    	if (myCount == 0){
    		return true;
    	}return false;
    }
    
    public int size(){
    	return myCount;
    }
    
    public int elementAt(int pos){
    	if (myValues[pos] == 0) {
    		System.err.println("NO element at this position");
    		System.exit(1);
    	}
    	return myValues[pos];
    }

    public String toString(int[] array) {
    	String string = new String();
    	for (int k = 0; k < array.length;k++){
    		string = string +  array[k]  + " " ;
    	}
    	return string;
    }
    public boolean contains(int i){
    	for ( int k = 0; k < myCount;k ++){
    		if (myValues[k] == i){
    			return true;
    		}
    	}
    	return false;
    }
    
    // other methods go here
    public static void main(String[]args){
    	IntSequence i = new IntSequence(4);
    	i.add(1);
    	i.add(2);
    	System.out.println(Arrays.toString(i.myValues));
    	System.out.println(i.isEmpty());
    	System.out.println(i.elementAt(0));
    	i.add(3);
       	System.out.println(Arrays.toString(i.myValues));
       	System.out.println(i.toString(i.myValues));
       	
       	i.remove(i.myValues,0);
    	System.out.println(i.toString(i.myValues));
    }

}

