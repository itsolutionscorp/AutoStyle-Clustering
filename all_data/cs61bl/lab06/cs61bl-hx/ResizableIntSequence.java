
public class ResizableIntSequence extends IntSequence {

	public ResizableIntSequence (int capacity){
		super (capacity);
	}
	
	@Override
	public void add(int toBeAdded) {
        // YOUR CODE HERE
		if (myValues.length == 0){
			myValues = new int[1];
		} else if (myCount == myValues.length){
			int[] newArray = new int[myValues.length*2];
			for(int c=0; c<myCount; c++){
				newArray[c] = myValues[c];
			}
			myValues = newArray;
		} 
		
    	myValues[myCount] = toBeAdded;
    	myCount++;
    	
    }
	
	@Override
	public void remove (int pos) {
		if (pos < 0 || pos >= myCount) {
			return;
		}
		int next = 0;
		if ((pos+1)<myCount){
				next = myValues[pos+1];
		}
		for (int i = pos; i<myCount; i++){
			myValues[i]=next;
			if ((i+2)<myCount){
				next = myValues[i+2];
			} else {
				next = 0;
			}
		}
		myCount -= 1;
		
		if (myCount == myValues.length/2){
			int[] newArray = new int[myValues.length/2];
			for(int c=0; c<myCount; c++){
				newArray[c] = myValues[c];
			}
			myValues = newArray;
		}
		
	}
}
