
public class ResizableIntSequence extends IntSequence {

	public ResizableIntSequence(int capacity) {
		super(capacity);
	}
	
	public void add(int toBeAdded) {
		if (myValues.length == myCount) {
			ResizableIntSequence biggerSequence = new ResizableIntSequence(myValues.length * 2);
			for (int k = 0; k < myCount; k++) {
				biggerSequence.add(myValues[k]);
			}
			myValues = biggerSequence.myValues;
			this.add(toBeAdded);
		} else {
			myValues[myCount] = toBeAdded;
			myCount++;
		}
	}

	
	// Insert toInsert into the sequence at position pos,
	// shifting the later elements in the sequence over to make room
	// for the new element.
	// Assumptions: The array isn't full, i.e. myCount < myValues.length
	// Also, pos is between 0 and myCount, inclusive.
	public void insert(int toInsert, int pos) {
		if (myValues.length == myCount) {
			ResizableIntSequence biggerSequence = new ResizableIntSequence(myValues.length * 2);
			for (int k = 0; k < myCount; k++) {
				biggerSequence.add(myValues[k]);
			}
			biggerSequence.insert(toInsert, pos);
			myValues = biggerSequence.myValues;
		}
		if (pos < 0 || pos > myCount) {
			return;
		}
		if (pos == myCount ) {
			myCount++;
			myValues[pos] = toInsert;
			return;
		}
		int currentValue = 0;
		for (int k = 0; k < myCount; k++) {
			if (k == pos) {
				currentValue = myValues[k];
				myValues[k] = toInsert;
				break;
			}
		}
		myCount++;
		for (int k = pos; k < myCount; k++) {
			if (k > pos) {
				int nextValue = myValues[k];
				myValues[k] = currentValue;
				currentValue = nextValue;
			}
		}
	}
	
	public int remove(int pos) {
		if (pos < 0 || pos >= myCount) {
			return 0;
		}
		if (myValues.length > (3 * myCount)) {
			ResizableIntSequence smallerSequence = new ResizableIntSequence(myValues.length / 2);
			for (int k = 0; k < myCount; k++) {
				smallerSequence.add(myValues[k]);
			}
			myValues = smallerSequence.myValues;
		}
		int removedPiece = myValues[pos];
		for (int k = 0; k < myCount; k++) {
			if (k >= pos) {
				if (k == myCount - 1) {
					myCount--;
				} else {
					myValues[k] = myValues[k + 1];
				}
			}
		}
		return removedPiece;
	}
}
