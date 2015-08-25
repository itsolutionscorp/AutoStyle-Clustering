
public class ResizableIntSequence extends IntSequence {

	
    public ResizableIntSequence() {
        super(0);
    }
    
    public ResizableIntSequence(int capacity) {
    	super(capacity);
    }
    
	public void add(int toBeAdded) {
		if (size() == myValues.length) {
			newslot();
		}
		super.add(toBeAdded);
	}
	
	public void insert(int toInsert, int insertPos) {
		if (size() == myValues.length) {
			newslot();
		}
		super.insert(toInsert, insertPos);
	}
	
	public void remove(int pos) {
		super.remove(pos);
		removeSlot();
	}
	
	private void newslot() {
		int[] newArr = new int[myValues.length + 1];
		for (int i=0;i<myValues.length;i++) {
			newArr[i] = myValues[i];
		}
		myValues = newArr;
	}
	public void removeSlot() {
		int[] newArr = new int[myValues.length - 1];
		for (int i=0;i<myValues.length - 1;i++) {
			newArr[i] = myValues[i];
		}
		myValues = newArr;
	}
}
