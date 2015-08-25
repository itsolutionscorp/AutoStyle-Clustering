public class ResizableIntSequence {

	// instance variables
	private int[] myValues; // sequence elements
	private int myCount; // number of array cells used by sequence

	// constructor

	// capacity: actual size of the array or the (temporary) maximum
	// number of elements it can hold
	public ResizableIntSequence(int capacity) {
		myValues = new int[capacity];
	}

	// Add the argument to the sequence by placing it in the first
	// unused spot in the array and incrementing the count.
	// Assume that the sequence isn't full.
	public void add(int toBeAdded) {
		if (myCount==myValues.length){
			resize(2);
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
		if (myCount==myValues.length){
			resize(2);
		}
		for (int k = myCount; k>insertPos; k--) {
			myValues[k] = myValues[k - 1];
		}
		myValues[insertPos] = toInsert;
		myCount++;
	}
	
	public void resize(double factor) {
		int[] newArray = new int[(int) (myValues.length * factor)];
		for(int i = 0; i < myCount; i++) {
			newArray[i] = myValues[i];
		}
		myValues = newArray;
	}

	

	public boolean isEmpty() {
		return myCount == 0;
	}

	public int size() {
		return myCount;
	}

	public int elementAt(int pos) {
		if (pos < 0 || pos > myCount) {
			System.err.println("position does not exist");
			System.exit(1);
		}
		return myValues[pos];
	}

	@Override
	public String toString() {
		String result = "";
		for (int i = 0; i < myCount; i++) {
			if (i == myCount - 1) {
				result += myValues[i];
			} else {
				result += myValues[i] + " ";
			}
		}
		return result;
	}
	
	public int remove(int pos) {

		int removed = myValues[pos];
		for (int i = pos; i < myCount-1; i++) {
			myValues[i] =myValues[i + 1];
		}
		myCount--;
		//System.out.println(myCount + "              " + .5 * myValues.length);
		if (myCount <=0.5*myValues.length){
			resize(0.75);
		}
		return removed;
	}


	public boolean contains(int k) {
		for(int i = 0; i < myCount; i++) {
			if(myValues[i] == k) {
				return true;
			}
		}
		return false;
	}
	
	public int getCapacity() {
		return myValues.length;
	}
}
