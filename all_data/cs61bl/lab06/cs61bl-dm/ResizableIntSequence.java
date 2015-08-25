
public class ResizableIntSequence extends IntSequence {
	
	public ResizableIntSequence(int capacity) {
		super(capacity);
	}

	@Override
	public void add(int toBeAdded) {
   		if(size() == myValues.length) {
   			myValues = createArray(size()*2);
   		}
   		super.add(toBeAdded);
   	}
   	
	
	@Override
	public void insert(int toInsert, int insertPos) {
		if(size() == myValues.length) {
   			myValues = createArray(1 + size() * 2);
   		}
		super.insert(toInsert, insertPos);		
    }
	
	@Override
	public int remove(int pos) {
		if((float) size() / myValues.length < 0.25) {
   			myValues = createArray(myValues.length / 2);
   		}
		super.remove(pos);
		return 0;
    }
	
	// helper method
   	private int[] createArray(int n) {
   		int[] tmp = new int[n];
   		for(int i = 0; i < size(); i++) {
   			tmp[i] = elementAt(i);
   		}
		return tmp;
   	}
	
}
