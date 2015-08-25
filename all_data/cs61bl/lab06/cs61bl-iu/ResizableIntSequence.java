public class ResizableIntSequence extends IntSequence {
	
	public ResizableIntSequence(int capacity){
		super(capacity);
		
	}

	public void add(int toBeAdded) {
    	if (myCount >= myValues.length) {
    		int[] NewArr = new int[myValues.length * 2];
    		for (int i = 0; i < myValues.length; i ++){
    			NewArr[i] = myValues[i];
    		}
    		myValues = NewArr;
    		
    	}
    	myValues[myCount] = toBeAdded;
        myCount++; 
	}

	public void insert(int newInt, int pos) {  
		if (myCount >= myValues.length) {
    		int[] NewArr = new int[myValues.length * 2];
    		for (int i = 0; i < myValues.length; i ++){
    			NewArr[i] = myValues[i];
    		}
    		myValues = NewArr;
    	}
		if (pos < 0) { 
			return;
		}
		int temp = myValues[0];
		for (int i = 0; i < myValues.length; i++) {
			if (i < pos) { 
				continue;
			}
			else if (i == pos) {
				temp = myValues[i];
				myValues[i] = newInt;
			}
			else if (i > pos) {
				int temp1 = myValues[i];
				myValues[i] = temp;
				temp = temp1;
			}
		}
		myCount++;
    }
	
    public int remove(int pos) {
    	int val = myValues[pos];
    	for (int i = 0; i < myValues.length; i++) {
			if (i < pos) { 
				continue;
			}
			else if (i >= pos && i < myCount) {
				myValues[i] = myValues[i+1];
			}
		}
    	myCount--;
        if (myValues.length - myCount >= 20){
    		int[] NewArr = new int[myValues.length - 10];
    		for (int i = 0; i < NewArr.length; i ++){
    			NewArr[i] = myValues[i];
    		}
    		myValues = NewArr;
    	}
        return val; 
    }
}



