public class IntSequence {

	// instance variables
	protected int[] myValues; // sequence elements
	int myCount; // number of array cells used by sequence

	// constructor
	// capacity: actual size of the array or the (temporary) maximum
	// number of elements it can hold
	public IntSequence(int capacity) {
		myCount = 0;
		myValues = new int[capacity];
	}

	// Add the argument to the sequence by placing it in the first
	// unused spot in the array and incrementing the count.
	// Assume that the sequence isn't full.
	public void add(int toBeAdded) {
		if (myCount == myValues.length) {
			System.err.println("Array if full");
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
		for (int k = myCount; k > insertPos; k--) {
			myValues[k] = myValues[k - 1];
		}
		myValues[insertPos] = toInsert;
		myCount++;
	}

	// other methods go here

	public int size() {
		return myCount;
	}

	public int elementAt(int pos) {
		if (pos < 0 || pos > myValues.length) {
			System.err.println("That is not a valid position");
			System.exit(1);
		}
		return myValues[pos];
	}

	public boolean isEmpty() {
		return myCount == 0;
	}

	public String toString() {
		String myString = "";
		for (int i = 0; i < myCount; i++) {
			myString = myString + myValues[i] + " ";
		}
		return myString;
	}

	public int remove(int pos) {
		int deletedValue = myValues[pos];
		for (int k = pos; k < myCount; k++) {
				myValues[k] = myValues[k + 1];

		}
		myCount--;
		myValues[myCount] = 0;
		return deletedValue;
	}
	public boolean contains(int k) {
		for(int i = 0; i < myCount; i++){
			if(myValues[i] == k) {
				return true;
			}
		}
		return false;
	}
}
