
public class ResizableIntSequence extends IntSequence {
	
	public ResizableIntSequence (int capacity){
		super(capacity);
	}
	
	public void add(int toBeAdded) {
        if (myCount >= myValues.length){
        	int [] temp = new int [myValues.length +1];
        	for (int i = 0; i < myCount; i++){
        		temp[i] = myValues[i];
        	}
        	temp[myValues.length] = toBeAdded;
        	myValues = temp;
        	myCount++;
        } else {
    	myValues[myCount] = toBeAdded;
        myCount++;
        }
    }
	
	public void insert (int toInsert, int insertPos) {
		if (insertPos < 0){
    		return;
    	} else if (myCount >= myValues.length){
    		int [] temp = new int [myValues.length +2];
        	for (int i = 0; i < myCount; i++){
        		temp[i] = myValues[i];
        	}
        	myValues = temp;
		}
		int x = myValues[insertPos];
		myValues[insertPos] = toInsert;
		for (int i = insertPos; i < myValues.length; i++){
			if (i+1 == myValues.length){
				break;
			}
			int temp = myValues[i+1];
			myValues [i+1] = x;
			x = temp;
		}
		myCount++;
    }
	
	public int remove(int pos){
    	if (pos < 0 || pos >= myValues.length) {
    		System.err.println("There is no element at the position given");
    		System.exit(1);
    	}
    	int temp = myValues[pos];
    	int x;
		for (int i = pos; i < myCount; i++){
			if (i+1 == myCount){
				myValues[i] = 0;
				break;
			}
			x = myValues [i+1];
			myValues[i] = x;
		}
    	myCount--;
    	
    	if (myValues.length - myCount >= 5){
    		int [] temp_array = new int [myValues.length - 5];
        	for (int i = 0; i < myCount; i++){
        		temp_array[i] = myValues[i];
        	}
        	myValues = temp_array;
    	}
    	return temp;
	}
}
