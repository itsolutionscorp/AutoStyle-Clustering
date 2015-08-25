public class ResizableIntSequence extends IntSequence {
	public ResizableIntSequence(int capacity) {
		super(capacity);
	}
	
	public void add(int toBeAdded) {
		if (myCount == myValues.length) {
    		int[] keep = myValues;
    		myValues = new int[myValues.length + 5];
    		for (int count = 0; count < keep.length; count++) {
    			myValues[count] = keep[count];
    		}
    		myValues[myValues.length - 1] = toBeAdded;
    	}
		myValues[myCount] = toBeAdded;
    	myCount++;
    }
	
	public void insert(int toInsert, int insertPos) {
		if (myCount == myValues.length) {	
			int[] keep = myValues;
			myValues = new int[myValues.length + 5];
			for (int count = 0; count < keep.length; count++) {
				myValues[count] = keep[count];
			}
		}
		for (int k = myCount; k > insertPos; k--) {
            myValues[k] = myValues[k-1];
        }
        myValues[insertPos] = toInsert;
        myCount++;
	}
	
	public void remove(int pos) {
		for (int count = pos; count <= this.size() - 2; count++) {
			myValues[count] = myValues[count + 1];
		}
		myValues[this.size() - 1] = 0;
		myCount--;
		if (myCount <= myValues.length / 4) {
			int[] keep = myValues;
			myValues = new int[myValues.length - (myValues.length / 4)];
			for (int count = 0; count < myCount; count++) {
				myValues[count] = keep[count];
			}
		}
	}
}
