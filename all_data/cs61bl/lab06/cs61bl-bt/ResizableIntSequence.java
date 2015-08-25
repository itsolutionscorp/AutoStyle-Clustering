
public class ResizableIntSequence extends IntSequence{

	public ResizableIntSequence(int capacity) {
		super(capacity);
		}
	
	public void add(int toBeAdded) {
        if (size() == myValues.length){
        	int [] copyOfValues = new int [myValues.length+1];
    		for (int i = 0; i < myValues.length; i++){
    			copyOfValues[i] = myValues[i];
    		}
        	copyOfValues[myValues.length] = toBeAdded;
        	myValues = copyOfValues;
        }
        else{
        	myValues[size()] = toBeAdded;
        }
        myCount++;
    }

	public void insert(int toInsert, int insertPos) {
    	if (myCount == myValues.length){
    		int [] copyOfValues = new int [myValues.length+1];
    		for (int i = 0; i < myValues.length; i++){
    			copyOfValues[i] = myValues[i];
    		}
    		myValues = copyOfValues;
    	}
    	if ((insertPos < 0) || (insertPos > myCount)){
    		System.err.println("Index out of bounds.");
    		System.exit(1);
    	}
        for (int k = myCount; k >= insertPos + 1; k--) {
            myValues[k] = myValues[k-1];
        }
        myValues[insertPos] = toInsert;
        myCount++;
    }
	
	public int remove (int pos) {
		int toReturn = super.remove(pos);
		int [] copyOfValues = new int [myCount];
		System.arraycopy(myValues, 0, copyOfValues, 0, myCount);
		myValues = copyOfValues;
		return toReturn;
	}
}
