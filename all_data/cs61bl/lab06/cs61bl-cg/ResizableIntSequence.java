
public class ResizableIntSequence extends IntSequence {

	public ResizableIntSequence(int capacity){
		super(capacity);
	}
	@Override
	public void add(int toBeAdded){
		int[] NewValues;
		if (myCount + 1 > myValues.length) {
			myCount ++;
			NewValues = new int[myValues.length * 2];
			
			for (int k = 0; k < myValues.length; k ++) {
				NewValues[k] = myValues[k];
			}
			
			NewValues[myCount -1] = toBeAdded;
			myValues = NewValues;
		}
		else {
			super.add(toBeAdded);
		}
	}
	public void insert(int toInsert, int insertPos) {
		int[] NewValues;
		
		if (myCount + 1 > myValues.length) {
			myCount ++;
			NewValues = new int[myValues.length * 2];
			
			for (int k = 0; k < myValues.length; k ++) {
				NewValues[k] = myValues[k];
			}
			myValues = NewValues;
			super.insert(toInsert, insertPos);
		}
		else {
			super.insert(toInsert, insertPos);
		}
	}
	public void remove() {
		if ((myValues.length / 2) > myCount) {
			
			int[] NewValues = new int[(int) (myValues.length * 3/4)];
			
			for (int k = 0; k < NewValues.length; k ++) {
				NewValues[k] = myValues[k];
			}
			myValues = NewValues;
		}
	}
}

