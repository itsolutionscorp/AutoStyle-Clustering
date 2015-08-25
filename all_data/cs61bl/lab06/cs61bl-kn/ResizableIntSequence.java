public class ResizableIntSequence extends IntSequence {

    public ResizableIntSequence(int capacity) {
    	super(capacity);
    }
    
    public void add(int toBeAdded) {
    	if (this.myCount == this.myValues.length) {
    		int[] extendedArray = new int[this.myCount+1];
    		for (int k=0; k<this.myCount; k++) {
    			extendedArray[k] = this.myValues[k];
    		}
    		this.myValues =extendedArray;
    	} 
    	super.add(toBeAdded);
    }
    
	public void insert (int newInt, int pos) {
		if (pos < 0 || pos > this.myCount) {
    		System.err.println("Error Index does not exist");
    		System.exit(1);
		}
		for (int k = this.myCount; k > pos; k--) {
			if (k == this.myValues.length) {
				this.add(this.myValues[k-1]);
				this.myCount--;
			} else {
				this.myValues[k] = this.myValues[k-1];
			}
		}
		if (pos == this.myValues.length ) {
			this.add(newInt);
		} else {
			this.myValues[pos] = newInt;
			this. myCount++;
    } }
    
    public void remove(int pos) {
    	super.remove(pos);
    	if (this.myValues.length > this.myCount*2) {
    		int[] shortenedArray = new int[(int) Math.ceil((this.myCount+this.myValues.length)/2) + (this.myCount+this.myValues.length) % 2];
    		for (int k=0; k<this.myCount; k++) {
    			shortenedArray[k] = this.myValues[k];
    		}
    		this.myValues = shortenedArray;
    	}
    }
}
