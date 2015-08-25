
public class ResizableIntSequence extends IntSequence {
	
	private int myCapacity;
	protected int[] resizablearray;
	
	public ResizableIntSequence(int capacity) {
		super(capacity);
		myCapacity = capacity;
	}
	
	public void add(int toBeAdded) {
		if (this.size() == myCapacity) {
			int[] newValues = new int[myCapacity+5];
			for (int i = 0; i < myCapacity; i++){
				newValues[i] = myValues[i];
			}
			myValues = newValues;
		}
		super.add(toBeAdded);
		myCount++;
	}
	
	public void insert(int toInsert, int insertPos) {
		if (this.size() == myCapacity){
			int[] newValues = new int[myCapacity+5];
			for (int i = 0; i < myCapacity; i++){
			newValues[i] = myValues[i];
		}
		myValues = newValues;
    	for (int n, k = insertPos; k <= myCount; k++) {
			n = myValues[k];
			myValues[k] = toInsert;
			toInsert = n;
		}
    	myCount += 1;
	}
}
}
