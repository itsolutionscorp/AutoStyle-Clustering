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
		if (myCount == myValues.length) {
			System.err.println("The sequence is full. Cannot add new value.");
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

		if (insertPos < 0 || insertPos > myCount) {
			System.err.println("This index does not exist");
			System.exit(1);
		}

		for (int k = myCount; k > insertPos; k--) {
			myValues[k] = myValues[k - 1];
		}
		myValues[insertPos] = toInsert;
		myCount++;
	}

	// other methods go here

	public boolean isEmpty() {
		if (myCount == 0) {
			return true;
		}
		return false;
	}

	public int size() {
		return myCount;
	}

	public int elementAt(int pos) {
		if (pos < 0 || pos >= myCount) {
			System.err.println("This index does not exist");
			System.exit(1);
		}
		return myValues[pos];
	}

	public String toString() {
		String arrayString = "";
		for (int i = 0; i < myCount; i++) {
			if (i == myCount - 1) {
				arrayString = arrayString + myValues[i];
			} else {
				arrayString = arrayString + myValues[i] + " ";
			}
		}
		return arrayString;
	}
	
	public int remove(int pos) {
		int temp = myValues[pos];
		for (int i = pos; i < myCount - 1; i++) {
			myValues[i] = myValues[i+1];
		}
		myCount--;
		return temp;
	}
	
//	public static void main(String[] args){
//		IntSequence s = new IntSequence(10);
//		s.add(3);
//		s.add(4);
//		s.add(7);
//		s.add(3);
//		s.add(4);
//		s.add(7);
//		s.add(10);
//		s.insert(5, 5);
//		System.out.println(s.toString());
//	}

}
