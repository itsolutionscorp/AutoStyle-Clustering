
import java.awt.*;


public class ResizableIntSequence extends IntSequence1 {
	 int myValues[];
	 int myCount;
	 
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
	}
	public ResizableIntSequence(int capacity){
		myValues = new int [capacity];
			
	}
	@Override
	 public void add (int toBeAdded) {
        // YOUR CODE HERE
    	if (myValues.length > myCount){
    		myValues[myCount] = toBeAdded;
    		myCount ++;
    	}
    	else if(myValues.length == myCount){
    		myValues[myCount] = toBeAdded;
    	}
    	else{
    		System.err.println("No more space avalible");
    		System.exit(1);
    	}
	}
	@Override
    public void insert(int toInsert, int insertPos){
        for (int k = insertPos + 1; k <= myCount; k++) {
            myValues[k] = myValues[k-1];
        }
        myValues[insertPos] = toInsert;
        myCount++;
    }

}
