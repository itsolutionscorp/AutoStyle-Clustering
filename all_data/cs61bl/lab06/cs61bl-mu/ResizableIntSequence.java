
public class ResizableIntSequence extends IntSequence {


	 public ResizableIntSequence(int capacity) {
	        // YOUR CODE HERE
	    	super(capacity);
	    }
	
	 public void add(int toBeAdded) {
	        // YOUR CODE HERE
	    	if(myCount < myValues.length){
		    	this.myValues[myCount] = toBeAdded;
		    	this.myCount++;
	    	}else{
	    		ResizableIntSequence s = new ResizableIntSequence(this.myValues.length+1);
		    	
		    	for(int i = 0; i < this.myCount ; i++){
		    		s.myValues[i] = this.myValues[i];
		    	}
		    	s.myValues[this.myCount] = toBeAdded;
		    	this.myValues = s.myValues; 
		    	this.myCount++;
	    	}
	    }

	    // Insert toInsert into the sequence at position insertPos,
	    // shifting the later elements in the sequence over to make room
	    // for the new element.
	    // Assumptions: The array isn't full, i.e. myCount < myValues.length
	    // Also, insertPos is between 0 and myCount, inclusive.
//	
	 public void insert(int newInt, int pos) {
	    	if (pos < 0 || pos >= myValues.length) {
	    		System.err.println("Element does not exist at index");
	    		System.exit(1);
			}
	    	
	    	if (super.myCount == super.myValues.length) {
	    		ResizableIntSequence s = new ResizableIntSequence(this.myValues.length+1);
		    	
		    	for(int i = 0; i < this.myCount ; i++){
		    		s.myValues[i] = this.myValues[i];
		    	}
		
		    	this.myValues = s.myValues; 
	    	}
			for(int i  = myValues.length - 1; pos < i; i--){
				myValues[i] = myValues[i-1];
			}
			myValues[pos] = newInt;
			
	        myCount++;
	    }

	    
	    public int remove (int pos) {
	    	int k = myValues[pos];
	    	if (pos < 0 || pos >= myValues.length) {
	    		System.err.println("Element does not exist at index");
	    		System.exit(1);
			
			}else{
				
				for(int i = 1; (i+pos) <= myValues.length-1; i++){
					myValues[pos+i-1]= myValues[pos+i];
				}
					myValues[myValues.length-1] = 0;
					ResizableIntSequence s = new ResizableIntSequence(this.myValues.length-1);
					for(int i = 0; i < this.myValues.length-1 ; i++){
			    		s.myValues[i] = this.myValues[i];
			    	}
					this.myValues = s.myValues; 
			}
	    	this.myCount--;
	    	return k;
			// YOUR CODE HERE
		}

}
