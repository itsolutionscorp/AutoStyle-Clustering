
public class ResizableIntSequence extends IntSequence{
	
	public ResizableIntSequence (int capacity) {
		super(capacity);
	}
	
	@Override
	public void add(int toBeAdded) {
		// YOUR CODE HERE
		if (myCount == myValues.length) {	
			ResizableIntSequence myCopy = new ResizableIntSequence(myValues.length+1);
			for (int i = 0; i<myValues.length; i ++) {
				myCopy.myValues[i] = myValues[i];
			}
			myValues = myCopy.myValues;
			myValues[myCount] = toBeAdded;
			myCount++;
			
			
		}else {
			myValues[myCount] = toBeAdded;
			myCount++;
			
		}
	}

	// Insert toInsert into the sequence at position insertPos,
	// shifting the later elements in the sequence over to make room
	// for the new element.
	// Assumptions: The array isn't full, i.e. myCount < myValues.length
	// Also, insertPos is between 0 and myCount, inclusive.
	@Override
	public void insert(int toInsert, int insertPos) {
		if (insertPos<0) {
			System.err.println("No element exists at position");
			System.exit(1);
		}else if (myCount == myValues.length) {	
			ResizableIntSequence myCopy = new ResizableIntSequence(myValues.length+1);
			for (int i = 0; i<myValues.length; i ++) {
				myCopy.myValues[i] = myValues[i];
			}
			myValues = myCopy.myValues;	
		}	
		for (int k = insertPos + 1; k <= myCount; k++) {
			myValues[k] = myValues[k - 1];
		}
		myValues[insertPos] = toInsert;
		myCount++;
	}


@Override
public int remove(int pos) {
	if (myCount <= 0) {
		System.err.println("Position not in sequence");
		System.exit(1);
	}
	int result = myValues[pos];
	for (int i = 0; i < myValues.length; i++) {
		int next;
		if (i == pos) {
			for (int j = i; j < myValues.length; j++) {
				if (j == myValues.length-1){
					ResizableIntSequence myCopy = new ResizableIntSequence(myValues.length-1);
					for (int k = 0; k<myValues.length-1; k ++) {
						myCopy.myValues[k] = myValues[k];
					}
					myValues = myCopy.myValues;
					myCount--;
				} else {	
				next = myValues[j+1];
				myValues[j] = next;
				}
			}

		}
	}
	return result;
	}
}
