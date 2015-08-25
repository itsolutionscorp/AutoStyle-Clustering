
public class ResizableIntSequence extends IntSequence {
	public ResizableIntSequence(int capacity) {
		super(capacity);
		// TODO Auto-generated constructor stub
	}

	public void add(int toBeAdded) {
	    if (myCount == myValues.length) {
	    	resize(myValues.length*2);
	    }
	    super.add(toBeAdded);
	 }
	
	public void insert(int toInsert, int insertPos) {
		if (myCount == myValues.length) {
	    	resize(myValues.length*2);
	    }
	    super.insert(toInsert, insertPos);
    }
	
	private void resize(int i) {
		int[] newValues = new int[i];
		System.arraycopy(myValues, 0, newValues, 0, myCount);
		myValues = newValues;
	}
	
	public void remove(int pos) {
		super.remove(pos);
		if (myCount*4 <= myValues.length) {
			resize(myCount*2);
		}
	}
}
