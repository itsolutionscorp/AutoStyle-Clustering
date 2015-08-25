public class ResizableIntSequence extends IntSequence {
	
	public ResizableIntSequence (int c) {
		super(c);
	}
	
	public void add(int toBeAdded) {
		if (myCount >= myValues.length) {
			int[] store = new int[myValues.length + 10];
			for (int i = 0; i < myCount; i ++) {
				store[i] = myValues[i];
			}
			myValues = store;
		}
    		myValues[myCount] = toBeAdded;
    		myCount += 1;
		
	}
	
	public void insert(int toInsert, int insertPos) {
		if (myCount >= myValues.length) {
			int[] store = new int[myValues.length + 10];
			for (int i = 0; i < myCount; i ++) {
				store[i] = myValues[i];
			}
			myValues = store;
		}
		super.insert(toInsert, insertPos);
	}
	
	public void remove(int pos) {
		super.remove(pos);
		if (myValues.length > myCount + 100) {
			int[] store = new int[myValues.length - 50];
			for (int i = 0; i < myCount; i ++) {
				store[i] = myValues[i];
			}
			myValues = store;
		}
	}
}
