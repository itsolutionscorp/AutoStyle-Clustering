
public class ResizableIntSequence extends IntSequence{
   
	public ResizableIntSequence(int capacity){
		super(capacity);
	}
	@Override
	public void add(int toBeAdded) {
        
    	    if (myCount >myValues.length-1) {
    		int[] copy = new int[myValues.length+1];
    	    for (int i=0; i<=copy.length-1;i++){
    	    	copy[i] = 0;
    	    }
    		for (int i=0; i<=myValues.length-1;i++){
    			copy[i] = myValues[i];
    		}
    		this.myValues = copy;
    	    }

    	myValues[myCount] = toBeAdded;
        myCount++;
    }
	@Override
	public void insert(int toInsert, int insertPos) {
		
		if (myCount > myValues.length-1) {
    	int[] copy = new int[myValues.length+1];
    	for (int i=0; i<=copy.length-1;i++){
	    	copy[i]=0;
	    }
    	for (int i=0; i<=myValues.length-1;i++){
    		copy[i] = myValues[i];
    	}this.myValues = copy;
		}
    	
		for (int k = myCount-1; k >= 0; k--) {
        
        	myValues[k+1] = myValues[k];
        }
        myValues[insertPos] = toInsert;
        myCount++;
		
    }
	@Override
	public void remove (int pos) {
		if (myValues.length > 5){
		for (int i = pos; i < myValues.length-1; i++) {
			myValues[i] = myValues[i+1];
		}
		
		int[] copy = new int[myValues.length-1];
		for (int i= 0; i< myValues.length-1;i++){
			copy[i] = myValues[i];
			
		}
		this.myValues = copy;
		myCount--;
		} else{
			super.remove(pos);
		}
	}
}
