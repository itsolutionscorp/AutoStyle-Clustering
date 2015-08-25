
public class ResizableIntSequence extends IntSequence {

	public ResizableIntSequence(int capacity) {
		super(capacity);
		// TODO Auto-generated constructor stub
	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
    public void add(int toBeAdded) {
        if (myCount < myValues.length){
        	myValues[myCount] = toBeAdded;
        	myCount += 1;
        } else {
        	int []tempStorage = new int[myCount*2];
        	int i = 0;
        	for(i=0; i< myValues.length; i++){
        		tempStorage[i] = myValues[i];
        	}
        	tempStorage[i] = toBeAdded;
        	myValues = tempStorage;
        }
    }

    // Insert toInsert into the sequence at position insertPos,
    // shifting the later elements in the sequence over to make room
    // for the new element.
    // Assumptions: The array isn't full, i.e. myCount < myValues.length
    // Also, insertPos is between 0 and myCount, inclusive.
    @Override
    public void insert(int toInsert, int insertPos) {
    	//if (insertPos < 0 || insertPos > myCount) {
    	//	System.err.println("Invalid Insert Position!");
    	//	System.exit(1);
    	if(insertPos < 0 ){
    		System.exit(1);
		}else if(myCount == myValues.length && insertPos < myValues.length){
			int []tempStorage = new int[myCount*2];
			int i = 0;
			for(i=0; i< insertPos; i++){
				tempStorage[i] = myValues[i];
			}
			tempStorage[insertPos] = toInsert;
			for(i=insertPos; i< myValues.length; i++){
				tempStorage[i+1] = myValues[i];
			}
			myValues = tempStorage;
		}
    	else if (insertPos > myCount || myCount == myValues.length){
			int []tempStorage = new int[insertPos+1];
			int i = 0;
			for(i=0; i< myValues.length; i++){
				tempStorage[i] = myValues[i];
			}
			tempStorage[insertPos] = toInsert;
			myValues = tempStorage;
		}else{
			for (int i = myCount; i >= 0 ; i--) {
				if (i > insertPos) {
					myValues[i] = myValues[i-1];
				} else if (i == insertPos) {
					myValues[i] = toInsert;
				}
			}
		}
		myCount += 1;

    }
 

}
