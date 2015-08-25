
public class ResizableIntSequence extends IntSequence {

	public ResizableIntSequence(int capacity) {
		super(capacity);
	}
	
	public void add(int toBeAdded) { 
		if (myCount+1>myCap) {
			myCap = (int) Math.floor(myCap*1.1+5);
			int[] TempMyValues = new int[myCap];   
			for(int i=0; i<myCount;i++){
				TempMyValues[i]=myValues[i];
			}
			myValues = TempMyValues;
	       }
		myValues[myCount] = toBeAdded;
	    myCount++;
	    }
	
	 public int remove (int pos) {
		    	int del = myValues[pos];
				for(int k = pos; k < myCount-1;  k++){
					myValues[k]=myValues[k+1];
				}
				myValues[myCount-1] = 0;	
				myCount--;
				
			if (myCount <= Math.floor(myCap/3)) {
				myCap = (int) Math.floor(myCap/2);
				int[] TempMyValues = new int[myCap];   
				for (int i=0; i<myCount;i++){
					TempMyValues[i]=myValues[i];
				}
				myValues = TempMyValues;
			}
			return del;
			
	    }
}
