
public class ResizableIntSequence extends IntSequence {

	public ResizableIntSequence(int capacity) {
		super(capacity);
	}
	
	@Override
    public void add(int toBeAdded) {
        // YOUR CODE HERE
    	if (myCount == myValues.length) {
    		int[] temp = new int[myCount * 2];
    		for (int i = 0; i < myCount; i++) {
    			temp[i] = myValues[i];
    		}
    		temp[myCount] = toBeAdded;
    		myCount++;
    		myValues = temp;
    	} else {
	        this.myValues[myCount] = toBeAdded;
	        myCount++;
    	}
    }
	
	@Override
    public void insert(int toInsert, int insertPos) {
		if (myCount == myValues.length) {
			int[] temp = new int[myCount * 2];
			for (int i = 0; i < myCount; i++) {
    			temp[i] = myValues[i];
    		}
			myValues = temp;
		}
        for (int k = myCount; k > insertPos; k--) {
            myValues[k] = myValues[k-1];
        }
        myValues[insertPos] = toInsert;
        myCount++;
    }
	
	@Override
	public int remove (int pos) {
		if (pos < 0 || pos >= myCount - 1) {
    		System.err.println ("Index does not exist.");
    		System.exit(1);
		}
		// YOUR CODE HERE
		int retVal = myValues[pos];
		for (; pos < myCount - 1; pos++) {
			myValues[pos] = myValues [pos + 1];
		}
		myCount--;
		if (myCount < myValues.length / 2) {
			int[] temp = new int[myValues.length / 2];
			for (int i = 0; i < myCount; i++) {
    			temp[i] = myValues[i];
    		}
			myValues = temp;
		}
		return retVal;
	}

}
