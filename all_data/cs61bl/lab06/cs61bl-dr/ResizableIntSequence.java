
public class ResizableIntSequence extends IntSequence {
	
	public ResizableIntSequence(int capacity){
		super(capacity);
	}
	
	private int expansion(){ // calculate a new size for the array
		if (myValues.length * 2 > 100){
			return myValues.length + 100;
		}
		return myValues.length * 2;
	}
	private void expand(){ // expand the array
		int[] nextArray = new int[expansion()];
		for (int i = 0; i < myValues.length; i++){
			nextArray[i] = myValues[i];
		}
		myValues = nextArray;
	}
	private void decrease(){
		int[] nextArray = new int[size() * 2];
		for (int i = 0; i < myValues.length; i++){
			nextArray[i] = myValues[i];
		}
		myValues = nextArray;
	}

	
	public void add(int toBeAdded) {
    	if (size() == myValues.length){
    		expand();
    	}
    	super.add(toBeAdded);
    }
	public void insert (int toInsert, int insertPos) {
		if (size() == myValues.length){
			expand();
		}
		super.insert(toInsert, insertPos);
	}
	public int remove (int pos) {		
		if(size() < myValues.length / 4){
			decrease();
		}
		return super.remove(pos);
	}
}
