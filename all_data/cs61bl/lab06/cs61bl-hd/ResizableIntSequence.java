
public class ResizableIntSequence extends IntSequence {
	public ResizableIntSequence(int capacity) {
		super(capacity);
	}

	public void add(int toBeAdded) {
		// YOUR CODE HERE
		if (this.myCount == this.myValues.length) {
			ResizableIntSequence larger = new ResizableIntSequence(myValues.length * 2);
			for (int i = 0; i < this.myCount; i++) {
				larger.myValues[i] = myValues[i];
			}
			larger.myValues[this.myCount] = toBeAdded;
			this.myValues = larger.myValues;
		} else {
			this.myValues[myCount] = toBeAdded;
		}
		this.myCount++;
	}

	// Insert toInsert into the sequence at position insertPos,
	// shifting the later elements in the sequence over to make room
	// for the new element.
	// Assumptions: The array isn't full, i.e. myCount < myValues.length
	// Also, insertPos is between 0 and myCount, inclusive.
	public void insert(int toInsert, int insertPos) {
		if (insertPos > this.myCount || insertPos < 0) {
			System.err.println("Error executing insert : Out of Boundary with insertPos " + insertPos);
		} else if (myCount == this.myValues.length) {
			ResizableIntSequence larger = new ResizableIntSequence(myValues.length * 2);
			for (int i = 0; i < myCount; i++) {
				larger.myValues[i] = myValues[i];
			}
			this.myValues = larger.myValues;
		}
		for (int k = myCount; k > insertPos; k--) {
			this.myValues[k] = this.myValues[k - 1];
		}
		this.myValues[insertPos] = toInsert;
		this.myCount++;
	}

	public int remove(int pos) {
		if (pos < 0 || pos >= this.myCount) {
			System.err.println("Error executing remove : Out of Boundary pos " + pos);
			System.exit(1);
		}
		int returnVal = this.myValues[pos];
		for (int i = pos; i < this.myCount - 1; i++) {
			this.myValues[i] = this.myValues[i + 1];
		}
		if (myCount - 1 <= myValues.length / 2) {
			ResizableIntSequence smaller = new ResizableIntSequence(myValues.length / 2);
			for (int i = 0; i < myCount - 1; i++) {
				smaller.myValues[i] = this.myValues[i];
			}
			this.myValues = smaller.myValues;
		}
		
		this.myCount--;
		return returnVal;
	}
}
