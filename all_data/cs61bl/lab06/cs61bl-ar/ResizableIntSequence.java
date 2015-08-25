
public class ResizableIntSequence extends IntSequence {

	public ResizableIntSequence(int capacity) {
		super(capacity);

	}
	
	@Override
	public void add(int toBeAdded){
		if(this.myCount >= this.myValues.length){
    		int[] temp;
    		temp = this.myValues;
			this.myValues = new int[this.myCount + 1];
			for (int k = 0; k < this.myCount; k ++){
				this.myValues[k] = temp[k];
			}

    	}
    	this.myValues[this.myCount] = toBeAdded;
    	this.myCount ++;
	}
	
	@Override
	public void insert(int toBeInserted, int insertPos){
		if(this.myCount >= this.myValues.length){
    		int[] temp;
    		temp = this.myValues;
			this.myValues = new int[this.myCount + 1];
			for (int k = 0; k < this.myCount; k ++){
				this.myValues[k] = temp[k];
			}

    	}
		super.insert(toBeInserted, insertPos);
	}
	
	@Override
	public void remove(int pos){
		super.remove(pos);
		if( this.myValues.length - this.myCount > 20){
			int[] temp;
			temp = this.myValues;
			this.myValues = new int[this.myCount + 20];
			for (int k = 0; k < this.myCount; k ++){
				this.myValues[k] = temp[k];
			}
		}
	}

}
