public class ResizableIntSequence extends IntSequence {
	   public ResizableIntSequence(int capacity) {
		super(capacity);
	}

	public void add(int toBeAdded) {
		   int[] newArray;
    	if (myCount > myValues.length - 1) {
    		newArray = new int[myCount * 2];
    		for (int i = 0; i < myCount; i++) {
    			newArray[i] = myValues[i];
    		}
    		myValues = newArray;
    	}
        myValues[myCount] = toBeAdded;
        myCount += 1;
    }

    public void insert(int toInsert, int insertPos) {
		   int[] newArray;
 	if (myCount > myValues.length - 1) {
 		newArray = new int[myCount * 2];
 		for (int i = 0; i < myCount; i++) {
 			newArray[i] = myValues[i];
 		}
 		myValues = newArray;
 	}
    	for (int k = insertPos + 1; k <= myCount; k++) {
            myValues[k] = myValues[k-1];
        }
        myValues[insertPos] = toInsert;
        myCount++;
    }
    
    public void remove(int pos) {
    	int[] newArray2;
    	
    	if (myCount < myValues.length/4) {
    		newArray2 = new int[myValues.length/4];
    		int j = 0;
    		for (int i = 0; i < myValues.length; i++) {
    			if (myValues[i] != 0){
    				newArray2[j] = myValues[i];
    				j++;
    			}
    		myValues = newArray2;
    	if (pos < 0 || pos >= myValues.length) {
			return;
		}
		for (int k = pos; k < myValues.length; k++) {
			if (k == myValues.length - 1) {
				myValues[k] = 0; 
			}
			else {
				myValues[k] = myValues [k+1];
			}
		}
    }
    }
    }
    
    
    
    
}
