
public class ResizableIntSequence extends IntSequence {
	
    public ResizableIntSequence(int capacity) {
		super(capacity);
		// TODO Auto-generated constructor stub
	}

	public void add(int toBeAdded) {
    	if(this.myCount<this.myValues.length) { // meaning there is space
    		this.myValues[myCount] = toBeAdded;
    		this.myCount++;
    		return;
    	} else { // meaning there is no space
    		int[] tempArray = new int[myCount+1];
    		for(int i = 0; i < this.myCount; i++) {
    			tempArray[i] = this.myValues[i];
    		}
    		tempArray[this.myCount] = toBeAdded;
    		this.myValues = tempArray;
    		this.myCount += 1;
    	}
    }
    
    public void insert(int toInsert, int insertPos) {
    	if (this.myCount < this.myValues.length && insertPos >= 0 && insertPos < myValues.length) {
            for (int k = myCount +1; k > insertPos; k--) {
            	this.myValues[k] = this.myValues[k-1];
            }
            this.myValues[insertPos] = toInsert;
            this.myCount++;
    	} else if (this.myCount == this.myValues.length) {
    		int[] tempArray = new int[myCount+1];
    		for(int i = 0; i < insertPos; i++) {
    			tempArray[i] = this.myValues[i];
    		}
    		tempArray[insertPos] = toInsert;
    		for(int i = insertPos+1; i < myCount+1; i++) {
    			tempArray[i] = this.myValues[i-1];
    		}
    		this.myValues = tempArray;
    		this.myCount++;
    	} else {
    		System.err.println("You have given an invalid position.");
    		System.exit(1);
    	}
    }
    
    public void remove (int pos) {
		if (pos < 0 || pos>=this.myCount) {
			System.err.println("Give an acceptable number.");
    		System.exit(1);
		}
		// YOUR CODE HERE
		else {
    		int[] tempArray = new int[this.myValues.length-1];
    		for(int i = 0; i < pos; i++) {
    			tempArray[i] = this.myValues[i];
    		}
    		for(int i = pos; i < tempArray.length; i++) {
    			tempArray[i] = this.myValues[i+1];
    		}
			this.myValues = tempArray;
			this.myCount = this.myCount - 1;
		}
    }

}
