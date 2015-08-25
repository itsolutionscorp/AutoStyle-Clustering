public class ResizableIntSequence extends IntSequence {

	public ResizableIntSequence(int capacity) {
		super(capacity);
	}

	private void expand() {
		int[] temp = myValues;
		myValues = new int[(myValues.length+1) * 2];
		for(int i = 0; i < myCount; i++) {
			myValues[i] = temp[i];
		}
	}

	private void shrink() {
		int[] temp = myValues;
		myValues = new int[myValues.length/2];
		for(int i = 0; i < myCount; i++) {
			myValues[i] = temp[i];
		}
	}
	
	@Override
	public void add(int toBeAdded) {
		if(myCount == myValues.length) {
			expand();
		}
		super.add(toBeAdded);
	}
	
	@Override
	public void insert(int toInsert, int index) {
		if(myCount == myValues.length){
			expand();
		}
		super.insert(toInsert,index);
	}

	@Override
	public void remove(int index) {
		super.remove(index);
		if(myCount <= myValues.length/4) {
			shrink();
		}
	}
	
	public int capacity() {
		return myValues.length;
	}
}
