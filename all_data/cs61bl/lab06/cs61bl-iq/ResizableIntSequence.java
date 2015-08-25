
public class ResizableIntSequence extends IntSequence {
	
	public ResizableIntSequence(int capacity) {
		super(capacity);
	}
	
	@Override
	public void add(int toBeAdded) {
    	if (myCount == myValues.length) {
    		int [] copyArray = new int[myValues.length + 1];
    		int counter = 0;
    		for (int item: myValues) {
    			copyArray[counter] = item;
    			counter ++;
    		}
    		copyArray[counter] = toBeAdded;
    		myValues = copyArray;
    		myCount = copyArray.length;
    	} else {
    		myValues[myCount] = toBeAdded;
            myCount++;
    	}
    }
	
	@Override
	public void insert(int toInsert, int insertPos) {
		if (myCount == myValues.length) {
			int [] copyArray = new int[myCount + 1];
    		int counter = 0;
    		for (int item: myValues){
    			copyArray[counter] = item;
    			counter ++;
    		}
    		myValues = copyArray;
		} 
		super.insert(toInsert, insertPos);
    }
	
	@Override
	public int remove (int pos) {
    	if (myCount == 0) {
    		System.err.println("No values in sequence");
    		return 0;
    	}
    	
    	if (pos >= myCount) {
    		System.err.println("This element does not exist");
    		return 0;
    	}
    	
    	if (myCount == myValues.length) {
    		int counter = pos;
        	int deletedValue = myValues[pos];
    		while (counter < myValues.length - 1) {
    			myValues[counter] = myValues[counter + 1];
    			counter++;
    		}
    		myCount--;
    		int [] copyArray = new int[myCount];
    		for (int secondCounter = 0; secondCounter < myValues.length-2; secondCounter++){
    			copyArray[secondCounter] = myValues[secondCounter];
    		}
    		myValues = copyArray;
    		return deletedValue;
    	} else {
    		int counter = pos;
        	int deletedValue = myValues[pos];
    		while (counter < myValues.length - 1) {
    			myValues[counter] = myValues[counter + 1];
    			counter++;
    		}
    		myCount--;
    		return deletedValue;
    	}
    	
    }

}

