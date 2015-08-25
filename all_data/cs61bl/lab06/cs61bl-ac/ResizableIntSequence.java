
public class ResizableIntSequence extends IntSequence {
	
	public ResizableIntSequence(int n) {
		super (n);
	}
	
	public void add(int toBeAdded) {
    	if (myCount < myValues.length){ 
    		myValues[myCount] = toBeAdded;
    		myCount += 1;
    	} else {
    		int[] copy = new int[myValues.length];
    		for (int i=0; i<myCount; i++) {
    			copy[i] = myValues[i];
    		}
    		myValues = new int[myCount+1];
    		for (int i= 0; i<myCount; i++) {
    			myValues[i] = copy[i];
    		}
    		myValues[myCount] = toBeAdded;
    		myCount++;
    	}
    }
	
	 public void insert (int newInt, int pos) 
	 {
			if (pos < 0) 
			{
				return;
			} else if (pos >= myCount) 
			{
				int[] copy = new int[myCount];
	    		for (int i=0; i<myCount; i++) 
	    		{
	    			copy[i] = myValues[i];
	    		}
	    		myValues = new int[myCount+1];
	    		for (int i= 0; i<myCount; i++) 
	    		{
	    			myValues[i] = copy[i];
	    		}
			} else if (myCount == myValues.length)
			{
				int[] copy = new int[myCount];
				for (int i=0; i<myCount; i++) 
	    		{
	    			copy[i] = myValues[i];
	    		}
				myValues = new int[myCount+1];
				for (int k = pos; k < myCount; k+=1) 
				{
					myValues[k+1] = copy[k];
				}
				for (int k = 0; k < pos; k+= 1)
				{
					myValues[k] = copy[k];
				}
			} else {
				for (int k = myCount; k > pos; k-=1) 
				{
					myValues[k] = myValues[k-1];
				}
			}
			myValues[pos] = newInt;
    		myCount++;
	 }
	 
	 public int remove(int pos) 
	 {
	    	int oldInt = myValues[pos];
	    	if (pos < 0 || pos >= myCount) {
	    		return 0;
	 		} else if ((myValues.length/myCount) >= 4) 
	 		{
	 		
	    		int[] copy = new int[myCount];
	    		for (int i=0; i<myCount; i++) 
	    		{
	    			copy[i] = myValues[i];
	    		}
	    		myValues = new int[myCount];
	    		for (int k = (pos+1); k < myCount; k+=1) 
				{
					myValues[k-1] = copy[k];
				}
	    		for (int k = 0; k < pos; k++)
	    		{
	    			myValues[k] = copy[k];
	    		}
	    	} else 
	    	{
	    		for (int k = (pos+1); k<myCount; k+=1) 
	    		{
	    			myValues[k-1] = myValues[k];
	    		}
	    	}
	    	myCount -= 1;
	    	myValues[myCount] = 0;
	    	return oldInt;
	 }
}
	    