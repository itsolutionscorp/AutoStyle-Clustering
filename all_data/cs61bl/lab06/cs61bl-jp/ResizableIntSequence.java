
public class ResizableIntSequence extends IntSequence{
	private int initial;

	public ResizableIntSequence(int capacity){
		super(capacity);
		initial = capacity;
	}

	public void add(int toBeAdded) {
        if(myCount==myCapacity){
        	int[] temp = new int[myCapacity+initial];
        	for (int k = 0; k < myCapacity; k++) {
            	temp[k] = myValues[k];
        	}
        	temp[myCapacity]=toBeAdded;
        	myValues = temp;
        	myCapacity+=initial;
        	myCount++;
        }else{
	        for (int k = 0; k < myCapacity; k++) {
	            if(myValues[k]==-1){
	                myValues[k] = toBeAdded;
	            }
	        }
	        myCount++;
	    }
    }

    public void insert(int toInsert, int insertPos) {
        if(myCount==myCapacity){
        	int[] temp = new int[myCapacity+initial];
        	for (int k = 0; k < insertPos; k++) {
            	temp[k] = myValues[k-1];
        	}
        	for (int k = insertPos + 1; k <= myCount; k++) {
            	temp[k] = myValues[k-1];
        	}
        	temp[insertPos] = toInsert;
        	myValues = temp;
        	myCapacity+=initial;
        	myCount++;
        }else{
	        for (int k = insertPos + 1; k <= myCount; k++) {
	            myValues[k] = myValues[k-1];
	        }
	        myValues[insertPos] = toInsert;
	        myCount++;
	    }
    }

    public int remove(int pos){
    	int r = elementAt(pos);
        for (int k = pos+1; k <= myCount; k++) {
            myValues[k-1] = myValues[k];
        }
        myCount--;
        if(myCapacity-myCount>initial){
        	int[] temp = new int[myCapacity-initial];
        	for (int k = 0; k < myCount; k++) {
            	temp[k] = myValues[k];
        	}
        	myValues = temp;
        	myCapacity-=initial;
        }
        return r;
    }

}