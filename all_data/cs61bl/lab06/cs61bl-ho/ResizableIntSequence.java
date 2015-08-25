
public class ResizableIntSequence extends IntSequence{
	
	public ResizableIntSequence(int capacity){
		super(capacity);
	}
    public void add(int toBeAdded) {
    	if (myCount==myValues.length){
    		int[] temp = new int [2*myCount];
    		for (int i=0; i<myCount; i++){
    			temp[i]=myValues[i];
    		}
    		myValues=temp;
    	} 
    	myValues[myCount]=toBeAdded;
    	myCount++;
    }
    public void insert(int toInsert, int insertPos) {
    	if (myCount==myValues.length){
    		int[] temp = new int [2*myCount];
    		for (int i=0; i<myCount; i++){
    			temp[i]=myValues[i];
    		}
    		myValues=temp;
    	} 
        for (int k = myCount; k > insertPos; k--) {
            myValues[k] = myValues[k-1];
        }
        myValues[insertPos] = toInsert;
        myCount++;
    }
    public int remove(int pos){
    	int temp = myValues[pos];
    	for (int i=pos; i<myCount-1; i++){
    		myValues[i]=myValues[i+1];
    	}
    	myCount--;
    	if (myCount<(int)myValues.length/2){
    		int[] temp2 = new int [(int)myValues.length/2];
    		for (int i=0; i<myCount; i++){
    			temp2[i]=myValues[i];
    		}
    		myValues=temp2;
    	} 
    	return temp;
    }
}
