
public class ResizableIntSequence extends IntSequence {

	public ResizableIntSequence(int capacity) {
		super(capacity);
	}
	@Override
	public void add(int toBeAdded) {
	    	if (myCount >= myValues.length) {
	    		int[] a = new int[myCount + 10];
	    		for (int x = 0; x < myCount; x++) {
	    			a[x] = myValues[x];
	    			
	    		}
	    		a[myCount] = toBeAdded;
	    		myCount++;
	    		this.myValues = a;
	    		
	    	}
	    	else {
	    		myValues[myCount] = toBeAdded;
	    		myCount++;
	        // YOUR CODE HERE
	    	}
	    }
	@Override
	public void insert(int toInsert, int insertPos) {
		if (myCount >= myValues.length) {
    		int[] a = new int[myCount + 10];
    		for (int x = 0; x < myCount; x++) {
    			a[x] = myValues[x];
    			
    		}
    		this.myValues = a;
    		
    	}
		
    	int x = 0;
        for (int k = insertPos + 1; k <= myCount; k++) {
            myValues[myCount-x] = myValues[myCount-x-1];
            x++;
        }
        this.myValues[insertPos] = toInsert;
        myCount++;
    }
	
	public void remove(int pos) {
		
		if (myCount <= Math.floor(myValues.length/4)){
    		int[] a = new int[(int) Math.floor(myValues.length/2)];
    		for (int x = 0; x < myCount; x++) {
    			a[x] = myValues[x];
    			
    		}
    		this.myValues = a;
    		System.out.print(myValues.length);
    	}
		
    	int x = 0;
    	for (int y = pos; y < myCount; y++) {
    		this.myValues[pos+x] = this.myValues[pos+x+1];
    	x++;
    	}
    	myCount--;
    }
	

}
