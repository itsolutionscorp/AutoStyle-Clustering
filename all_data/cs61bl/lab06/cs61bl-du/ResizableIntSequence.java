
public class ResizableIntSequence extends IntSequence {
	
    public ResizableIntSequence(int capacity) {
        super(capacity);
    }
    
	@Override
    public void add(int toBeAdded) {
    	if (myCount == myValues.length) {
    		int[] newValues = new int[myValues.length + 10];
    		for (int i = 0; i < myCount; i++) {
    			newValues[i] = myValues[i];
    		}
    		myValues = newValues;
    	}
    	super.add(toBeAdded);
    }
	
	@Override
    public void insert(int toInsert, int insertPos) {
    	if (myCount == myValues.length) {
    		int[] newValues = new int[myValues.length + 10];
    		for (int i = 0; i < myCount; i++) {
    			newValues[i] = myValues[i];
    		}
    		myValues = newValues;
    	}
    	
        super.insert(toInsert, insertPos);
    }
}
