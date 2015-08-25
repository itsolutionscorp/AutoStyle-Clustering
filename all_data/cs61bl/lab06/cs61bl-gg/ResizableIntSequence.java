
public class ResizableIntSequence extends IntSequence {

	public ResizableIntSequence(int capacity){
		super(capacity);
	}
	
	@Override
	public void add(int toBeAdded) {
    	if(myCount >= this.myValues.length){
    		ResizableIntSequence temp = new ResizableIntSequence(this.size()+10);
    		for(int i=0;i<this.myValues.length;i++){
    			temp.add(this.myValues[i]);
    		}
    		this.myValues = temp.myValues;
    	}
        this.myValues[myCount] = toBeAdded;
        this.myCount++;
    }
	
	@Override
	public void insert(int toInsert, int insertPos) {
    	myCount++;
    	if(myCount >= this.myValues.length){
    		ResizableIntSequence temp = new ResizableIntSequence(this.size()+10);
    		for(int i=0;i<this.myValues.length;i++){
    			temp.add(this.myValues[i]);
    		}
    		this.myValues = temp.myValues;
    	}
    	for (int i = myCount-1;i>insertPos;i--){
			myValues[i]=myValues[i-1];
		}
        myValues[insertPos] = toInsert;
    }
	
	public int remove (int pos) {
    	int result = this.elementAt(pos);
		for (int i = pos;i<myCount-1;i++){
			myValues[i]=myValues[i+1];
		}
		myCount--;
		if(myCount == myValues.length/4){
			ResizableIntSequence temp = new ResizableIntSequence(this.myValues.length/2);
    		for(int i=0;i<this.myCount;i++){
    			temp.add(this.myValues[i]);
    		}
    		this.myValues = temp.myValues;
    	}
		return result;
    }
}
