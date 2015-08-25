
public class ResizableIntSequence extends IntSequence {

//	Override the add and insert methods so that whenever either of 
//	these methods is called on a full ResizableIntSequence, the 
//	ResizableIntSequence increases its capacity to accomodate for the new elements.
//	Remember to write tests before you start coding your methods!
	
	public ResizableIntSequence(int capacity) {
		super(capacity);
	}


	public void add(int toBeAdded) {
		if (size() == myValues.length) {
			int[] copy = myValues;
			myValues = new int[myValues.length * 2];
			for (int i = 0; i < copy.length; i++) {
				myValues[i] = copy[i];
			}
		}
		myValues[myCount] = toBeAdded;
    	myCount++;
	}
	
	public void insert(int toInsert, int insertPos) {
		if (size() == myValues.length) {
			int[] copy = myValues;
			myValues = new int[myValues.length * 2];
			for (int i = 0; i < myCount; i++) {
				myValues[i] = copy[i];
			}
		} super.insert(toInsert, insertPos);
	}
	
	public void remove(int pos) {
		super.remove(pos);
		if (myCount <= myValues.length / 4) {
			int[] copy = myValues;
			myValues = new int[myValues.length / 2];
			for (int i = 0; i < myCount; i++) {
				myValues[i] = copy[i];
			}
		}
	}
}
