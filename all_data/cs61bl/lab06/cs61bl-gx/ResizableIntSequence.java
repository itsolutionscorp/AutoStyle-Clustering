
public class ResizableIntSequence extends IntSequence {
	public ResizableIntSequence(int capacity) {
		super(capacity);
	}
	
	private void resizing(int moreCap) {
		int[] temp = myValues;
		myValues = new int[moreCap];
		
		for (int i = 0; i < myCount; i++) {
			myValues[i] = temp[i];
		}
	}
	
	@Override
	public void add(int toBeAdded) {
    	if (myCount >= myValues.length) {
    		resizing(myValues.length + 1);
    	}
    	myValues[myCount] = toBeAdded;
    	myCount++;
    }
	
	@Override
    public void insert(int toInsert, int insertPos) {
    	if (myCount >= myValues.length) {
    		resizing(myValues.length + 1);
    	}
    	
    	if (insertPos >= myCount || insertPos < 0) {
    		System.err.println("Insert Method Error : Insert Position can't be larger than myCount or lesser than zero");
    		System.exit(1);
    	}
    	
        for (int i = myCount - 1; i > insertPos; i--) {
			myValues[i] = myValues[i - 1];
		}
        
        myValues[insertPos] = toInsert;
        myCount++;
    }
	
	@Override
    public void remove(int pos) {
    	if (pos < 0 || pos >= myCount) {
    		System.err.println("Remove Method Error : Argument is same or larger than myCount");
    		System.exit(1);
		}
		
    	if (myCount < myValues.length) {
    		resizing(myCount);
    	}
    	
		for (int i = pos; i < myCount - 1; i++) {
			myValues[i] = myValues[i+1];
		}
		
		myCount--;
    }
}
