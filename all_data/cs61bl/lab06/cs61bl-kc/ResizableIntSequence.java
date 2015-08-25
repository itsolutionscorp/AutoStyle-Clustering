public class ResizableIntSequence extends IntSequence {

//	private int myCount2;
	//private int[] myValues2;
	
	public ResizableIntSequence(int capacity) {
		super(capacity);
	}
	
	@Override
    public void add(int toBeAdded) {
    	if (myCount == myValues.length) {
    		int[] myValues2 = new int[myCount * 2];
    		for (int k = 0; k < myValues.length; k++) {
    			myValues2[k] = myValues[k];    			
    		}

    		this.myValues = myValues2;
    		add(toBeAdded);	
    	}
    	else {
    	
        myValues[myCount] = toBeAdded;
        myCount++;
    	}
	}
	
	@Override
	public void insert(int toInsert, int insertPos) {
		if (myCount == myValues.length) {
    		int[] myValues2 = new int[myCount * 2];
    		for (int k = 0; k < myValues.length; k++) {
    			myValues2[k] = myValues[k];    			
    		}

    		this.myValues = myValues2;
    		insert(toInsert, insertPos);	
    	}
    	else {
    		super.insert(toInsert, insertPos);
    	}
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
		if (myCount > 0) {
		myCount--;
		}
		if (myCount < myValues.length / 2) {
			int[] myValues2 = new int[myCount + 1];
			for (int i = 0; i < myCount; i++) {
				myValues2[i] = myValues[i];
			}
			myValues = myValues2;
		}
		return retVal;
	}
}
