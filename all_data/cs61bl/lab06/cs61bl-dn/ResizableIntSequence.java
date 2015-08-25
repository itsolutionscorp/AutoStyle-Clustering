
public class ResizableIntSequence extends IntSequence {
	
    public ResizableIntSequence(int capacity) {
    	super(capacity);
    }
	
    @Override
    public void add(int toBeAdded) {
    	while (myCount >= myValues.length){
    		int[] updated = new int[myValues.length+1];
    		for (int i = 0; i < myValues.length; i ++ ){
    			updated[i]= myValues[i];
    		}
    		myValues = updated;
    	}
        myValues[myCount] = toBeAdded;
        myCount++;

   }

    public void insert(int toInsert, int insertPos) {
		while (insertPos < 0 || insertPos >= myValues.length) {
    		return;
		}
		
		while (myCount >= myValues.length){
    		int[] updated = new int[myValues.length+1];
    		for (int i = 0; i < myValues.length; i ++ ){
    			updated[i]= myValues[i];
    		}
    		myValues = updated;
    	}
		
		
		int k;
		for (k = myValues.length-1; k >= insertPos; k--){
			if (k != insertPos){
				myValues[k] = myValues[k-1];
			}
			if (k == insertPos){
				myValues[k] = toInsert;
			}
		}
		myCount++;
    }
    
    public void remove(int pos) {
		if (pos < 0 || pos >= myValues.length) {
			return;
		}
		int k;
		for (k = 0; k < myValues.length; k++){
			if (k >= pos && k != myValues.length - 1){
				myValues[k] = myValues[k+1];
			}
			if (k == myValues.length-1){
				myValues[k] = 0;
			}
		}
		myCount--;
		if (myValues.length < myCount ){
			int[] updated = new int[myValues.length/2];
			for (int i = 0; i < myValues.length/2; i ++ ){
    			updated[i]= myValues[i];
    		}
    		myValues = updated;
    	}
    }
}
