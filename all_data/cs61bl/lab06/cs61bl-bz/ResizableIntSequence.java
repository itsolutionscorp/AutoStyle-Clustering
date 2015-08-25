
public class ResizableIntSequence extends IntSequence {

	public ResizableIntSequence(int capacity) {
		super(capacity);
	}

	public void add(int toBeAdded) {
		if (myCount == myValues.length) {
			int[] newMyValues = new int[myValues.length + 10];
			for (int i = 0; i < myCount; i++) {
				newMyValues[i] = myValues[i];
			}
			myValues = newMyValues;
		}
		super.add(toBeAdded);
	}
	
	public void insert(int toInsert, int insertPos) {
		if (myCount == myValues.length){
			int[] newMyValues = new int[myValues.length + 10];
			for (int i = 0; i < myCount; i++) {
				newMyValues[i] = myValues[i];
			}
			myValues = newMyValues;
		}
		super.insert(toInsert, insertPos);
	}
	
	public int remove(int toBeRemoved) {
		if (myValues.length/2 >= myCount) {
			int[] newMyValues = new int[(myValues.length * 3) / 4];
			for (int i = 0; i < myCount; i++) {
				newMyValues[i] = myValues[i];
			}
			myValues = newMyValues;
		}
		return super.remove(toBeRemoved);
	}
}
