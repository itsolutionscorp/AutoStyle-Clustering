public class ResizableIntSequence extends IntSequence {
	
	//instance variables
	int[] tempArray;
	
	public ResizableIntSequence(int capacity){
		super (capacity);
	}

	@Override
	public void add(int toBeAdded){
    	if (myCount >= this.size()) {
    		tempArray = new int[this.size() + 1];
//    		IntSequence tempArray = new ResizableIntSequence(this.size() * 2);
    		for (int k = 0; k < myCount; k++) {
    			tempArray[k] = myValues[k];
    			System.out.println(tempArray);
    		}
    		tempArray[myCount] = toBeAdded;
    		myCount++;
    		myValues = tempArray; 
    	} else {
    	super.add(toBeAdded);
    	}
    }
		
	@Override
	public void insert(int toInsert, int insertPos) {
		// insertPos out of bounds; 
		// means don't need to shift values on right of insertPos
		if (insertPos > this.size()) {
			tempArray = new int[ insertPos + 1 ];
    		for (int k = 0; k < myCount; k++) {
    			tempArray[k] = myValues[k]; // copy myValues into tempArray
    		}
    		tempArray[insertPos] = toInsert;
    		myCount++;
    		myValues = tempArray;
		}
		// standard
		super.insert(toInsert, insertPos);
	}

    public void remove (int pos) {
    	if (myCount < this.size() - 8) {
    		tempArray = new int[myCount + 1];
    		for (int k = 0; k < myCount; k++) {
    			tempArray[k] = myValues[k];
    		}
    		myValues = tempArray;
    	}
    	for (int i = pos; i < this.size(); i++) {
    		myValues[i] = myValues[i+1];
    	}
    	myCount--;
    	// OR:     	super.remove(pos);
    }
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}