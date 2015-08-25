
public class ResizableIntSequence extends IntSequence {
	
	public ResizableIntSequence(int capacity) {
		super (capacity);
	}
	
	@Override
	public void add(int toBeAdded) {
        if (myCount < myValues.length) { 
        	myValues[myCount] = toBeAdded;
        	myCount++;
      } else if (myCount == myValues.length) {
    	  int[] result = new int[myValues.length + myValues.length / 2];
    	  for (int i = 0; i < myValues.length; i++) {
    		  result[i] = myValues[i];
    	  	}
    	  myValues = result;
    	  super.add(toBeAdded);
      	} 
	}
	
	@Override
	public void insert (int toInsert, int pos) {
		if (pos < 0 || pos >= myValues.length) {
			return;
		}
		if (myCount == myValues.length) {
			int[] result = new int[myValues.length + myValues.length / 2];
	    	for (int i = 0; i < myValues.length; i++) {
	    		result[i] = myValues[i];
	    		myValues = result;
	    	  }
	    	super.insert(toInsert, pos);
	    	return;
		}
		for (int i = 0; i < myCount; i++) {
			if (i >= pos) {
				int temp = myValues[i];
				myValues[i] = toInsert;
				toInsert = temp;
			}
		}
		myCount++;
	}
	
	@Override
	public int remove(int pos) {
		if (pos < 0 || pos >= myCount) {
			System.err.println("index doesn't exist, cannot remove element");
			System.exit(1);
	    	return -1;
		}
			int result = myValues[pos];		
			if (myValues.length - myCount >= myCount) {
			int[] smaller = new int[myValues.length - ((myValues.length - myCount)/2)];
			for (int i = 0; i < myValues.length - ((myValues.length - myCount)/2); i++) {
	    		smaller[i] = myValues[i];
	    		myValues = smaller;
	    	  }
		} 
			for (int i = 0; i < myCount; i++) {
				if (i >= pos) {
					myValues[i] = myValues[i+1];
				}
			}

			myCount -= 1;
			return result;
	}
	
}
