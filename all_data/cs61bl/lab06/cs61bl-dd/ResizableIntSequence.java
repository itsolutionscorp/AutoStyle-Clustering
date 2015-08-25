public class ResizableIntSequence extends IntSequence {

	public ResizableIntSequence(int capacity) {
		super(capacity);
	}
	
	@Override
	public void insert(int toInsert, int insertPos) {
    	if (insertPos < 0) {
			System.out.println("invalid index");
    		return;
		}
		if (myCount < myValues.length){
			super.insert(toInsert, insertPos);
		} else {
			int[] newValues = new int[myValues.length + 1];
			for (int i=0; i< myValues.length; i++) {
				newValues[i] = myValues[i];
			}
			myValues = newValues;
			super.insert(toInsert, insertPos);
		}
    }
	
	@Override
	public void add(int toBeAdded) {
		if (myCount < myValues.length){
			super.add(toBeAdded);
		} else {
			int[] newValues = new int[myValues.length + 1];
			for (int i=0; i< myValues.length; i++) {
				newValues[i] = myValues[i];
			}
			myValues = newValues;
			super.add(toBeAdded);
		}
    }
	
	public int remove(int pos) {
    	if (pos < 0 || pos >= myCount) {
    		System.err.println("invalid index");
			System.exit(1);
		}
    	if (myCount > myValues.length*0.8) {
    		return super.remove(pos);
    	} else {
			int[] newValues = new int[(int) (myValues.length * 0.85)];
			for (int i=0; i< newValues.length; i++) {
				newValues[i] = myValues[i];
			}
			myValues = newValues;
			return super.remove(pos);
    	}
    }
}
