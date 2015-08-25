public class ResizableIntSequence extends IntSequence {
	
	public ResizableIntSequence(int capacity) {
		super(capacity);
	}
	
	 public void add(int toBeAdded) {
	        // YOUR CODE HERE
		 if (myCount == myValues.length) {
			 int[] a = new int[myCount + 1];
			 for (int i = 0; i < myCount; i++) {
				 a[i] = myValues[i];
			 }
			 a[myCount] = toBeAdded;
			 myCount++;
			 myValues = a;
		 } else {
	    	myValues[myCount] = toBeAdded;
	    	myCount++;
	    }
	 } 
	 public void insert(int pos, int newInt) {
			if (pos < 0 || pos >= myValues.length) {
				return;
			}
			// YOUR CODE HERE
			if (myCount == myValues.length) {
				int[] a = new int[myCount+1];
				for (int i = 0; i < myCount; i++) {
					 a[i] = myValues[i];
				 }
				myValues = a;
			}
			for (int i =  myCount; i > pos; i--) {
				myValues[i] = myValues[i-1];
			}
			myValues[pos] = newInt;
			myCount++;
		}
	 
	 public void remove(int pos) {
		 if (myCount < .25*myValues.length){
			 int[] a = new int[myValues.length/2];
				for (int i = 0; i < myCount; i++) {
					 a[i] = myValues[i];
				 }
				myValues = a;
		 }
			for (int i = pos; i < myCount; i++) {
				myValues[i] = myValues[i+1];
			}
			myCount--;
		}

}
