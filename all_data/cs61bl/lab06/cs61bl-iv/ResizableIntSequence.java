
public class ResizableIntSequence extends IntSequence {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public ResizableIntSequence(int capacity) {
		super(capacity);
	}
	
    public void add(int toBeAdded) {
        // YOUR CODE HERE
    	if (myCount < myValues.length) {
    		myValues[myCount] = toBeAdded;
        	myCount++;
    	}
    	else {
    		int[] temp = new int[myValues.length + 1];
    		for (int k = 0; k < myValues.length; k++) {
    			temp[k] = myValues[k];
    		}
    		temp[myValues.length] = toBeAdded;
    		myCount++;
    		this.myValues = temp;
    	}
    	
    }
    
    public void insert(int toInsert, int insertPos) {
    	if (myCount < myValues.length) {
    		int[] temp = new int[myCount];
        	
        	for (int k = 0; k < myCount; k++) {
    			temp[k] = myValues[k];
    		}
        	
            for (int k = insertPos + 1; k <= myCount; k++) {
                myValues[k] = temp[k-1];
            }
            myValues[insertPos] = toInsert;
            myCount++;
    	}
    	else {
    		int[] temp = new int[myValues.length + 1];
    		
    		for (int k = 0; k < insertPos; k++) {
    			temp[k] = myValues[k];
    		}
    		
    		temp[insertPos] = toInsert;
    		
    		for(int k = insertPos + 1; k < myValues.length + 1; k++) {
    			temp[k] = myValues[k-1];
    		}
    		myCount++;
        	this.myValues = temp;
    	}
    	
    }
    
	public int remove (int pos) {
		
		int removed;
		if (pos < 0 || pos >= myCount) {
			System.err.println("Index out of bounds.");
			System.exit(1);
		}
		
		removed = myValues[pos];
		for(int k = pos; k < myCount-1; k++) {
			myValues[k] = myValues[k+1];
		}
		myCount--;
		
		if (myCount <= myValues.length/2) {
			int[] temp = new int[(int) Math.floor(myValues.length*0.75)];
			
			for (int k = 0; k < myCount; k++) {
				temp[k] = myValues[k];
			}
			
			myValues = temp;
		}
		
		return removed;
		
	}
    
	
}
