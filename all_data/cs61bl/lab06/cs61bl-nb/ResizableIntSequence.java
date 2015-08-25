
public class ResizableIntSequence extends IntSequence{
	private int myRemoved = 0;
	
	public ResizableIntSequence(int capacity) {
		super(capacity);
		myRemoved = 0;
	}

	public void add(int toBeAdded){
		if(myCount == myValues.length) {
			ResizableIntSequence r = new ResizableIntSequence(myCount + 1);
			for (int i = 0; i < this.myValues.length; i++) {
				r.myValues[i] = this.myValues[i];
			}
			r.myValues[this.myCount] = toBeAdded;
			r.myCount = this.myCount + 1;
			this.myValues = r.myValues;
			this.myCount = r.myCount;
			
		}
		else {
			myValues[myCount] = toBeAdded;
			myCount++;
		}
	}
	
    public void insert(int toInsert, int insertPos) {
    	if (myCount == myValues.length) {
    		ResizableIntSequence r = new ResizableIntSequence(myCount + 1);
    		for (int i = 0; i < this.myValues.length; i++) {
				r.myValues[i] = this.myValues[i];
			}
    		for(int j = r.myValues.length - 1; j > insertPos; j--) {
				r.myValues[j] = r.myValues[j-1];
    		}
    		r.myValues[insertPos] = toInsert;
    		r.myCount = this.myCount + 1;
    		this.myValues = r.myValues;
    		this.myCount = r.myCount;
        }
    	else {
	        for (int k = myCount; k > insertPos; k--) {
	            myValues[k] = myValues[k-1];
	        }
	        myValues[insertPos] = toInsert;
	        myCount++;
    	}
    }
    
    public void remove (int pos) {
		if (pos < 0 || pos >= this.myCount) {
			return;
		}
		for (int i = pos; i < this.myCount - 1; i++) {
			this.myValues[i] = this.myValues[i + 1];
		}
		this.myValues[this.myCount-1] = 0;
		this.myCount--;	
		this.myRemoved++;
		if(this.myRemoved == this.myValues.length/2){
			ResizableIntSequence r = new ResizableIntSequence(this.myValues.length/2 + 1);
			for(int i = 0; i < this.myCount; i++) {
				r.myValues[i] = this.myValues[i];
			}
			r.myCount = this.myCount;
			this.myValues = r.myValues;
		}
	}
		
}
