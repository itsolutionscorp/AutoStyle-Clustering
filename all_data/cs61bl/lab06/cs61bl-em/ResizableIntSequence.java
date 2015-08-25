
public class ResizableIntSequence extends IntSequence {

	public ResizableIntSequence (int capacity) {
		super(capacity);
	}
	
	@Override
	public void add (int toBeAdded) {
		if (myCount < this.myValues.length) { 
    		super.add(toBeAdded);
    	}
    	else {
    		int[] larger = new int[myCount*2];
    		for (int i = 0; i < myCount; i ++) {
    			larger[i] = myValues[i];
    		}
    		myValues = larger;
    		super.add(toBeAdded);
    	}
	}
	
	@Override
	public void insert (int toInsert, int insertPos) {
		if (insertPos < 0) {
			return;
		}
		else if (insertPos <= myCount) {
			super.insert(toInsert, insertPos);
		}
		else {
			int[] larger = new int[myCount*2];
    		for (int i = 0; i < myCount; i ++) {
    			larger[i] = myValues[i];
    		}
    		myValues = larger;
    		super.insert(toInsert, insertPos);
		}
	}
	
	@Override
	public int remove (int toRemove) {
		if (myCount < (int)(myValues.length/10)) {
			int[] smaller = new int[(int)(myCount/2)];
    		for (int i = 0; i < myCount; i ++) {
    			smaller[i] = myValues[i];
    		}
    		myValues = smaller;
    		super.remove(toRemove);
		}
		else {
			super.remove(toRemove);
		}
		return 0;
	}
}
