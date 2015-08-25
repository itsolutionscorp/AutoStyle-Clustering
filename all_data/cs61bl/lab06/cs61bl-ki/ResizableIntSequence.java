
public class ResizableIntSequence extends IntSequence {

	public ResizableIntSequence(int capacity){
		super(capacity);
	}
	
    public void add(int toBeAdded) {
    	if (this.myCount == this.myValues.length){
    		int[] newValues = new int [this.myValues.length+5];
    		for (int i = 0; i<this.myValues.length; i++){
    			newValues[i]=this.myValues[i];
    		}
    		this.myValues = newValues;
    	}
    		// make a new myValues with increased length
    		// copy over old values into new myValues
    		// add the new value in
    	this.myValues[this.myCount] = toBeAdded;
    	this.myCount++;    		
    }    
    
    public void insert(int toInsert, int pos) {
    	if (this.myCount == this.myValues.length){
    		int[] newValues = new int [this.myValues.length+5];
    		for (int i = 0; i<this.myValues.length; i++){
    			newValues[i]=this.myValues[i];
    		}
    		this.myValues = newValues;
    	}
   		for (int i = pos; i < this.myCount+1; i++ ){
   			int temp = this.myValues[i];
   			this.myValues[i]=toInsert;
   			toInsert = temp;
   			pos++;
   		}
   		myCount++;
    }
    
    public void remove(int pos){
    	super.remove(pos);
    	if (this.myValues.length-this.myCount >= 10){
    		int[] newValues = new int [this.myValues.length-5];
    		for (int i = 0; i<this.myCount; i++){
    			newValues[i]=this.myValues[i];
    		}
    		this.myValues = newValues;
    	}
    	// remove thing first i guess 
    	// when myCount is less than length by a considerable amount
    	// make a new myValues array with smaller capacity
    	// copy values over from myValues to the new version
    	// 
    }
	
}
