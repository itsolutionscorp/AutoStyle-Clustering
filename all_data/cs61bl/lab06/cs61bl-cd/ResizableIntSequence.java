
public class ResizableIntSequence extends IntSequence {
	
	
	public ResizableIntSequence(int capacity) {
		super(capacity);
		// TODO Auto-generated constructor stub
	}
	
	int[] newValues;
	
	
	@Override
	public void add(int toBeAdded){
		if (super.size() < myValues.length){
			super.add(toBeAdded);
		}
		else if(super.size() == this.myValues.length){
			newValues = new int[this.myValues.length + 10];
			for (int k = 0; k < this.myValues.length; k ++) {
				newValues[k] = this.myValues[k];
			}
			this.myValues = newValues;
			super.add(toBeAdded);
		}
	}
	
	
	
	public void insert(int toInsert, int insertPos){
		if (super.size() < myValues.length){
			super.insert(toInsert, insertPos);
		}
		else if(super.size() == this.myValues.length){
			newValues = new int[this.myValues.length + 10];
			for (int k = 0; k < this.myValues.length; k ++) {
				newValues[k] = this.myValues[k];
			}
			this.myValues = newValues;
			super.insert(toInsert, insertPos);
		}
		
	}
		
	public void remove(int pos) {
    	for(int k = pos; k < myCount; k += 1) {
    		if (k == myCount - 1){
    			myValues[k] = 0;
    			myCount -= 1;
    		}	
    		else {
    			myValues[k] = myValues[k+1];
    		}
    		
    	}
    	if ((myCount/myValues.length) < 0.2) {
    		for(int k = myCount; k < myValues.length - 1; k += 2) {
    			super.remove(k);
    		}
    		
    	}
    }
	

}
