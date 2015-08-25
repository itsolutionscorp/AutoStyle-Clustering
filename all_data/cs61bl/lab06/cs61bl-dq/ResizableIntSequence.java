
public class ResizableIntSequence extends IntSequence{
	
	public ResizableIntSequence(int c){
		super(c);
	}
	
	@Override
	public void add(int toBeAdded) {
        if (myCount == myValues.length){
        	int[] newValues = new int[myValues.length * 2];
        	for (int i =0; i < myCount; i++){
        		newValues[i] = myValues[i];
        	}
        	myValues = newValues;
        }
    	super.add(toBeAdded);
    }
	
	@Override
	public void insert(int toInsert, int insertPos) {
		if (myCount == myValues.length){
			int[] newValues = new int[myValues.length * 2];
        	for (int i =0; i < myCount; i++){
        		newValues[i] = myValues[i];
        	}
        	myValues = newValues;
        } 
		super.insert(toInsert, insertPos);
    }

	@Override
	public int remove(int pos){
		if (myValues.length * 1/2 == myCount){
			int[] newValues = new int[myValues.length * 3/4];
        	for (int i =0; i < myCount; i++){
        		newValues[i] = myValues[i];
        	}
        	myValues = newValues;
		}
		return super.remove(pos);
	}
	
	public int getCapacity(){
		return myValues.length;
	}
}
