
public class ResizableIntSequence extends IntSequence {
	private int capacity;
	
	public ResizableIntSequence(int capacity) {
		super(capacity);
		this.capacity = capacity;
	}
	public void add(int toBeAdded) {
		super.add(toBeAdded);
		if (myCount == capacity-1) {
			capacity += 1;
			int[] myCopy = new int[capacity];
			for (int i=0; i<myValues.length; i++) {
				myCopy[i] = myValues[i];
			}
			myValues = myCopy;
		}
	}
	public void insert(int toInsert, int pos) {
		super.insert(toInsert, pos);
		if (myCount == capacity-1) {
			capacity += 1;
			int[] myCopy = new int[capacity];
			for (int i=0; i<myValues.length; i++) {
				myCopy[i] = myValues[i];
			}
			myValues = myCopy;
		}
	}
	public void remove(int pos) {
		super.remove(pos);
		if (capacity > myCount) {
			capacity--;
			int[] myCopy = new int[capacity];
			for (int i=0; i<myValues.length-1; i++) {
				myCopy[i] = myValues[i];
			}
			myValues = myCopy;
		}
	}
	public int cap() {
		return capacity;
	}
}
