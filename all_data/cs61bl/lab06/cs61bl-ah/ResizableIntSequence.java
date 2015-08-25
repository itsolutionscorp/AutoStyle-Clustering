
public class ResizableIntSequence extends IntSequence {

	public ResizableIntSequence(int capacity) {
		super(capacity);
	}
	
	@Override
	public void add(int toBeAdded) {
        // YOUR CODE HERE
    	if (myCount == myValues.length) {
    		ResizableIntSequence blah = new ResizableIntSequence(myCount * 2);
    		for (int i = 0; i < myCount; i++) {
    			blah.myValues[i] = myValues[i];
    			blah.myCount++;
    		}
    		blah.myValues[blah.myCount] = toBeAdded;
    		blah.myCount++;
    		myValues = blah.myValues;
    		myCount = blah.myCount;
    	}
    	else {
    		myValues[myCount] = toBeAdded;
        	myCount ++;
    	}
    }
	
	@Override
	public void insert(int toInsert,int insertPos){
		if (myCount == myValues.length) {
    		ResizableIntSequence blah = new ResizableIntSequence(myCount * 2);
    		for (int i = 0; i < myCount; i++) {
    			blah.myValues[i] = myValues[i];
    			blah.myCount++;
    		}
    		for(int q = blah.myCount + 1; q > insertPos; q--){
        		blah.myValues[q] = blah.myValues[q-1];
        		}
    		blah.myValues[insertPos]=toInsert;
        	blah.myCount++;
        	myValues = blah.myValues;
        	myCount = blah.myCount;
    	}
		else {
			for(int q = myCount + 1; q > insertPos; q--){
	    		myValues[q] = myValues[q-1];
	    		}
	    	myValues[insertPos]=toInsert;
	    	myCount++;
	    	}
		}
	
	@Override
	public int remove(int pos){ 
		int q = myValues[pos];
		for(int x=pos; x < myCount; x++){
			myValues[x] = myValues[x+1];
		}
		myCount--;
		if (myCount <= (myValues.length / 2)) {
			ResizableIntSequence blah = new ResizableIntSequence(myValues.length / 2);
    		for (int i = 0; i < myCount; i++) {
    			blah.myValues[i] = myValues[i];
    			blah.myCount++;
    		}
    		myValues = blah.myValues;
    		myCount = blah.myCount;
		}	
		return q;
	}
}
