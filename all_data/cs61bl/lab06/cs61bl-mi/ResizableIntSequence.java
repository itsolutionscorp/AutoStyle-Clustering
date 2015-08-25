
public class ResizableIntSequence extends IntSequence {

	public ResizableIntSequence(int Capacity) {
		super(Capacity);
	}
	
	public void add(int toBeAdded) {
		if (myCount < myValues.length){ 
	    	myValues[myCount] = toBeAdded;
	    	myCount++;}
	    else {
	    	int[] myValues2 = new int[myCount + 1];
	    	for (int i = 0; i < myCount; i++) {
	    		myValues2[i] = myValues[i];
	    	}
	    	myValues = myValues2;
	    	myValues[myCount] = toBeAdded;
	    	myCount++;
	    }
	}
	
	public void insert(int toInsert, int insertPos) {
		if(myCount >= myValues.length){
			int[] newMyValues = new int[myCount +1];
			for(int i=0;i<myCount;i++){
				newMyValues[i]=myValues[i];
			}
			myValues = newMyValues;
		}
		
		if (myCount < myValues.length) {
			int store1 = 0;
			int store2 = 0;
			for (int i = insertPos; i <myCount + 1; i++) {
				if (i == insertPos) {
					store1 = myValues[i];
					store2 = myValues[i];
					myValues[i] = toInsert;
				}
				else {
					store2 = myValues[i];
					myValues[i] = store1;
					
				}
				store1 = store2;

				}
			myCount++;
		}

}

	
	public void remove() {
		if (size() != 0 && size() < .1 * myValues.length){
			int[] myValues2 = new int[myValues.length / 2];
			for (int i = 0; i < size(); i++) {
				myValues2[i] = myValues[i];
			}
			myValues = myValues2;
		}
	}
}

