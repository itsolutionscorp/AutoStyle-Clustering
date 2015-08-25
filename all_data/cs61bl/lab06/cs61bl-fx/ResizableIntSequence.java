
public class ResizableIntSequence extends IntSequence {

	/**
	 * @param args
	 */
	public ResizableIntSequence(int capacity) {
		super (capacity);
	}
	
    public void add(int toBeAdded) {
        if (myCount == myValues.length) {
        	ResizableIntSequence newarray = new ResizableIntSequence(myValues.length + 1);
        	for (int i = 0; i < myValues.length; i++) {
        		newarray.myValues[i] = myValues[i];
        	}	
        	newarray.myCount = newarray.myValues.length;
        	newarray.myValues[myCount] = toBeAdded;
        	this.myValues = newarray.myValues;
        	myCount++;
        } else {
        	myValues[myCount] = toBeAdded;
        	myCount++;
        }
    }
    
    public int remove (int pos) {
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
			int originalmyCount = myCount;
			ResizableIntSequence newarray = new ResizableIntSequence(myValues.length - 1);
        	for (int i = 0; i < myCount; i++) {
        		newarray.myValues[i] = myValues[i];
        	}	
        	newarray.myCount = originalmyCount;
        	this.myValues = newarray.myValues;
			return returnValue;
		}
	}
    public void insert(int toInsert, int insertPos) {
        this.add(myValues[myCount - 1]);
    	for (int k = myCount - 1; k > insertPos; k--) {
        	myValues[k] = myValues[k-1];
        }
        myValues[insertPos] = toInsert;
    }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
