public class IntSequence {

	// instance variables
	protected int[] myValues; // sequence elements
	int myCount; // number of array cells used by sequence

	// constructor
	// capacity: actual size of the array or the (temporary) maximum
	// number of elements it can hold
	public IntSequence(int capacity) {
		myValues = new int[capacity];
	}

	// Add the argument to the sequence by placing it in the first
	// unused spot in the array and incrementing the count.
	// Assume that the sequence isn't full.
	public void add(int toBeAdded) {
		myValues[myCount] = toBeAdded;
		myCount++;
	}

	// Insert toInsert into the sequence at position insertPos,
	// shifting the later elements in the sequence over to make room
	// for the new element.
	// Assumptions: The array isn't full, i.e. myCount < myValues.length
	// Also, insertPos is between 0 and myCount, inclusive.
	public void insert(int toInsert, int insertPos) {
		if (insertPos < 0 || insertPos >= myValues.length) {
			return;
		}
		if (insertPos == (myValues.length - 1)){
			myValues[insertPos] = toInsert;
		} else {
			for(int i = (myValues.length - 1); i > insertPos ; i--){
				myValues[i] = myValues[insertPos]; 
			}
			myValues[insertPos] = toInsert;
		}
	}

	public boolean isEmpty() {

		if (myValues == null) {
			return true;
		}
		boolean condition = false; // true means not empty
		for (int i = 0; i < myCount; i++) {
			if (myValues[i] != 0) {
				condition = true;
				break;
			}
		}
		return !condition;
	}
	
	public int size() {
		return myCount;
	}
	
	public int elementAt(int pos) {
		
		if(pos < 0 || pos >= myValues.length){
			System.err.println("System Error, input wrong number");
			System.exit(1);
		}
		return myValues[pos];
	}

	public String toString() {
		String s = new String();
		for (int i = 0; i < myCount; i++) {
			s = s + myValues[i] + " ";
		}
		s = s.substring(0, s.length() - 1);
		return s;

	}

	public void remove(int pos) {
		if (pos < 0 || pos >= myValues.length) {
			return;
		}
		myCount--;
		if (pos == (myValues.length - 1)){
			myValues[myValues.length-1] = 0;
		} else {
			for(int i = pos; i < (myValues.length - 1); i++){
				myValues[i] = myValues[i+1];
			}
			myValues[myValues.length - 1] = 0;
		}
	}
	
	
	public boolean contains(int k){
		boolean result = false;
		for(int i = 0; i < (myValues.length - 1); i++){
			if(myValues[i] == k){
				result = true;
			}
		}
		return result;
	}

}
