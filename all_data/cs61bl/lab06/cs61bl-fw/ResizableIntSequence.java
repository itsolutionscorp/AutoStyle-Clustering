
public class ResizableIntSequence extends IntSequence {

	public ResizableIntSequence(int capacity) {
		// TODO Auto-generated constructor stub
		super(capacity);
		
	}
	
	@Override 
    public void add(int toBeAdded) {
        // YOUR CODE HERE
    	if (this.myCount == this.myValues.length) {
    		ResizableIntSequence newSeq = new ResizableIntSequence(this.myValues.length*2);
    		for (int i = 0; i < this.myCount; i++) {
    			newSeq.myValues[i] = super.myValues[i];
    		}
    		super.myValues = newSeq.myValues;
    	}
    	this.myValues[this.myCount] = toBeAdded;
    	this.myCount++;
    }
	 
	 @Override
	 public void insert (int newInt, int pos) {
		 if (pos < 0 || pos >= myValues.length) {
			 System.err.println("Invalid Index for Inserting!");
			 System.exit(1);
		 }

		 if (this.myCount == this.myValues.length) {
			 ResizableIntSequence newSeq = new ResizableIntSequence(this.myValues.length*2);
			 for (int i = 0; i < this.myCount; i++) {
				 newSeq.myValues[i] = super.myValues[i];
			 }
			 super.myValues = newSeq.myValues;
		 }
		 // YOUR CODE HERE
		 boolean found = false;
		 int temp = 0;
		 for (int i = 0; i < myValues.length; i++) {
			 if (found) {
				 int currentValue = myValues[i];
				 myValues[i] = temp;
				 temp = currentValue;
			 }
			 if (i == pos) {
				 found = true;
				 temp = myValues[i];
				 myValues[pos] = newInt;	
			 }
		 }
		 this.myCount++;
	 }
	 
	 public int remove (int pos) {
			if (pos < 0 || pos >= myValues.length) {
				System.err.println("Invalid Index!");
				System.exit(1);
			}
			// YOUR CODE HERE
			if (super.myCount * 2 < super.myValues.length) {
				ResizableIntSequence newSeq = new ResizableIntSequence(this.myCount*2);
				for (int i = 0; i < this.myCount; i++) {
					 newSeq.myValues[i] = super.myValues[i];
				 }
				 super.myValues = newSeq.myValues;
			}
			int result = 0;
			boolean found = false;
			for (int i = 0; i < myValues.length; i++) {
				if (found) {
					if (i < myValues.length-1)
						myValues[i] = myValues[i+1];
					else 
						myValues[i] = 0;
					continue;
				}
				if (i == pos) {
					found = true;
					result = myValues[i];
					if (i == myValues.length-1)
						myValues[i] = 0;
					else
						myValues[i] = myValues[i+1];
				} 	
			}
			this.myCount--;
			return result;
		}
}
