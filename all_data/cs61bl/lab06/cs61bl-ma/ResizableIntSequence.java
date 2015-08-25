
public class ResizableIntSequence extends IntSequence{

	public ResizableIntSequence(int capacity) {
		super(capacity); 
	}
	
	@Override
	public void add(int toBeAdded) {
		if (myCount < myValues.length) {
    		myValues[myCount] = toBeAdded;
    		myCount ++; 
    	} else{
    		int[] newValues = new int[this.getCapacity()+1]; 
    		for (int i = 0; i < newValues.length-1; i++) {
    			newValues[i] = myValues[i]; 
    		}
    		newValues[newValues.length-1] = toBeAdded;
    		myCount ++; 
    		myValues = newValues; 
    	}
    }
	
	@Override
	public void insert(int toInsert, int insertPos) {
		if (insertPos < 0) {
			System.err.println("Cannot insert into negative index.");
			System.exit(1); 
		}
		if (myCount < myValues.length) {
			  for (int k = myCount; k > insertPos; k--) {
		            myValues[k] = myValues[k-1];
		        }
		        myValues[insertPos] = toInsert;
		        myCount++;
		    }
		else {
			if (insertPos > myCount - 1) {
				int[] newValues = new int[this.getCapacity()+1]; 
				for (int i = 0; i < newValues.length-1; i++) {
					newValues[i] = myValues[i]; 
				}
				newValues[newValues.length-1] = toInsert;
				myCount ++; 
				myValues = newValues; 
			}
			else {
				int[] newValues = new int[this.getCapacity()+1]; 
				for (int i = 0; i < insertPos; i++) {
					newValues[i] = myValues[i]; 
				}
				newValues[insertPos] = toInsert;
				for (int i = insertPos + 1; i < newValues.length; i++) {
					newValues[i] = myValues[i-1]; 
				}
				myCount++; 
				myValues = newValues; 
			}
		}
    }
	
	@Override
	public int remove(int pos) {
		if (pos < 0 || pos >= myCount) {
    		System.err.println("Index is not yet used.");
    		System.exit(1);
    		return -1; 
		}
		// YOUR CODE HERE
    	int removed = myValues[pos]; 
		for (int i = pos; i < myCount - 1; i++) {
			myValues[i] = myValues[i+1]; 
		}
		myCount --;
		if (myCount < myValues.length - 50) {
			int[] newValues = new int[this.getCapacity() - 40]; 
			for (int i = 0; i < myCount; i++) {
				newValues[i] = myValues[i]; 
			}
			myValues = newValues; 
		}
		return removed; 
	}
}
