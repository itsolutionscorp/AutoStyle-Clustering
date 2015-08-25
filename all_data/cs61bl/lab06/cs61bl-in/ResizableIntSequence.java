
public class ResizableIntSequence extends IntSequence {
	
	public int myCapacity;
	
	public ResizableIntSequence(int capacity) {
		super(capacity);
		myCapacity = capacity;
	}
	
	@Override
	public void add(int toBeAdded) {
		if (size() == myCapacity) {
			int [] tempArray = myValues;
			myCapacity += 1;
			myValues = new int [myCapacity];
			for (int i = 0; i < myCapacity - 1; i += 1) {
				myValues[i] = tempArray[i];
			}
		} myValues[myCount] = toBeAdded;
	    myCount += 1;
	}
	
	@Override
	public void insert(int toInsert, int insertPos) {
		if (myCount == myCapacity) {
			int [] tempArray = myValues;
			myCapacity += 1;
			myValues = new int [myCapacity];
			for (int i = 0; i < myCapacity - 1; i += 1) {
				myValues[i] = tempArray[i];
			}
		}
		myCount += 1;
		for (int k = myCount - 1; k > insertPos; k -= 1) {
            myValues[k] = myValues[k-1];
        }
        myValues[insertPos] = toInsert;
	}
	
	@Override
	public int remove(int pos) {
		if (myCount <= (int) (myCapacity * 0.25)); {
			myCapacity = (int) (myCapacity * 0.5);
		} 
		int returnVal = myValues[pos];
		for (int i = pos; i < myCount - 1; i += 1) {
			myValues[i] = myValues[i + 1];
		} myValues[myCount - 1] = 0;
		myCount -= 1;
		return returnVal;
	}
	
}