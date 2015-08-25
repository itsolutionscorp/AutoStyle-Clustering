import java.util.*;

public class ResizableIntSequence extends IntSequence{

	protected int [] myVals;
	int myCount;

	public ResizableIntSequence(int capacity){
		super(capacity);
		myVals = new int[capacity];
	}

	@Override
	public void add(int toBeAdded) {
		if(myCount == myVals.length){
			int [] x = new int [myVals.length+5];
			for(int i = 0; i<myVals.length; i++){
				x[i] = myVals[i];
			}
			myVals = x;
		}
		myVals[myCount] = toBeAdded;
		myCount ++;
	}


	@Override
	public void insert(int toInsert, int insertPos) {
		int [] x = new int [myVals.length+1];
		if (insertPos < 0 || insertPos >myCount) {
			for(int i = 0; i<myVals.length; i++){
				x[i] = myVals[i];
			}
		}
		for (int k = myCount; k > insertPos; k--) {
			myVals[k] = myVals[k-1];
		}
		myVals[insertPos] = toInsert;
		myCount++;
	}
	
	@Override
	public void remove(int pos) {
		if (pos < 0 || pos >= myCount) {
			return;
		}
		for(int i = pos; i < myCount-1; i++){
			myVals[i] = myVals[i+1];
		}
		myCount--;
		
		if(myCount < myVals.length/4 && myVals.length > 4){
			int [] x = new int[myVals.length/4];
			for(int i = 0; i < myVals.length/4; i++){
				x[i] = myVals[i];
			}
			myVals = x;
		}
	}

	@Override
	public String toString(){
    	String vals = "";
    	for(int i = 0; i < myCount; i++){
    		vals = vals + myVals[i];
    		if(i != myCount-1){
    			vals+=" ";
    		}
    	}
    	return vals;
    }



}