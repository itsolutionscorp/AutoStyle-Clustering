public class ResizableIntSequence extends IntSequence {
	private int[] myValues;
	
	public ResizableIntSequence (int capacity) {
		super(capacity);
	}
	
	public void add (int toBeAdded) {
		ResizableIntSequence tempVal = new ResizableIntSequence(myValues.length*2);
		 if (myCount == myValues.length) 
		 {
			 for (int i = 0; i < myValues.length; i++) {
				 tempVal.myValues[i] = (myValues[i]);
			 }
			 this.myValues = tempVal.myValues;
			 tempVal.myCount = tempVal.myValues.length;
	        	tempVal.myValues[myCount] = toBeAdded;
	        	this.myValues = tempVal.myValues;
	        	myCount++;
		 } else {
			 myValues[myCount] = toBeAdded;
	        	myCount++;
		 }
	}
		
	
	public void insert(int toInsert, int insertPos) {
		this.add(myValues[myCount - 1]);
    	for (int k = myCount - 1; k > insertPos; k--) {
        	myValues[k] = myValues[k-1];
        }
        myValues[insertPos] = toInsert;
    }
	
	public int remove(int pos) {
			if (pos < 0 || pos >= myValues.length) {
				System.err.println("Index does not exist.");
				System.exit(1);
			}
			if (pos >= myCount) {
				return 0;
			}
			else {
				int returnValue = myValues[pos];
				for (int i = pos; i < myCount; i++) {
					if (pos == myCount - 1) {
						myValues[i] = 0;
						break;
					}
					myValues[i] = myValues[i + 1];
					}
				myCount--;
				int origmyCount = myCount;
				ResizableIntSequence tempVal = new ResizableIntSequence(myValues.length - 1);
	        	for (int i = 0; i < myCount; i++) {
	        		tempVal.myValues[i] = myValues[i];
	        	}	
	        	tempVal.myCount = origmyCount;
	        	this.myValues = tempVal.myValues;
				return returnValue;
	}
	}
}
