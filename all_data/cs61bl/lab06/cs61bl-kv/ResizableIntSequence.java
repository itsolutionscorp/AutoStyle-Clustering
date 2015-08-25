
public class ResizableIntSequence extends IntSequence{

	public ResizableIntSequence(int capacity) {
		super(capacity);
	}
	
	public void add(int toBeAdded) {
		if (this.myCount >= this.myValues.length) {
    		int [] store = new int[this.myCount + 10];
    		for (int k = 0; k < myCount; k ++) {
    			store[k] = this.myValues[k];
    		}
    		this.myValues = store;
    	}
    	super.add(toBeAdded);
	}
	
	public void insert(int toInsert, int insertPos) {
		if (this.myCount >= this.myValues.length) {
    		int [] store = new int[this.myCount + 10];
    		for (int k = 0; k < myCount; k ++) {
    			store[k] = this.myValues[k];
    		}
    		this.myValues = store;
		}
		super.insert(toInsert, insertPos);	
	}
	
	public void remove (int pos) {
		super.remove(pos);
		if (this.myCount <= this.myValues.length / 5) {
			int [] store = new int[this.myValues.length * 4 / 5];
    		for (int k = 0; k < myCount; k ++) {
    			store[k] = this.myValues[k];
    		}
    		this.myValues = store;
		}
	}
}
