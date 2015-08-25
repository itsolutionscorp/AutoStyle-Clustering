
public class ResizableIntSequence extends IntSequence{

	public ResizableIntSequence(int capacity) {
	    	super(capacity);
	}
	public void add(int toBeAdded) {
    	if (myCount < this.myValues.length) {
    		this.myValues[myCount] = toBeAdded;
    		myCount++;
    	} else {
    		ResizableIntSequence newSequence = new ResizableIntSequence(this.myValues.length+1);
    		for (int i = 0; i < this.myValues.length; i++) {
    			newSequence.add(this.myValues[i]);
    		}
    		newSequence.add(toBeAdded);
    		this.myValues = newSequence.myValues;
    		myCount++;
    	}
    }
	
	public void insert(int toInsert, int insertPos) {
        int prev = myValues[insertPos];
        int curr;
        if (myCount<this.myValues.length){
        	for (int k = insertPos + 1; k <= myCount; k++) {
        		curr = myValues[k];
        		myValues[k] = prev;
        		prev = curr;
        	}
        	myValues[insertPos] = toInsert;
        	myCount++;
        } else {
        	ResizableIntSequence newSequence = new ResizableIntSequence(this.myValues.length+1);
        	for (int i = 0;i<insertPos; i++) {
        		newSequence.add(this.myValues[i]);
        	}
        	newSequence.add(toInsert);
        	for (int i =insertPos; i<myValues.length;i++){
        		newSequence.add(this.myValues[i]);
        	}
        	this.myValues = newSequence.myValues;
    		myCount++;
        }
    }

	
	 public void remove(int pos) 
	    {
	    	for (int i = pos; i < myCount-1; i ++) {
	    		this.myValues[i] = this.myValues[i+1];
	    	}
	    	myCount--;
	    	if (this.myCount<this.myValues.length){
				 ResizableIntSequence newSequence = new ResizableIntSequence(myCount);
		    	for (int i = 0; i < myCount; i++) {
		    		newSequence.add(this.myValues[i]);
		    	}
		    	this.myValues = newSequence.myValues;
			 }
	    }

}
