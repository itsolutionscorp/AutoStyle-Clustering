import java.util.Arrays;

public class ResizableIntSequence extends IntSequence {

	public ResizableIntSequence(int capacity) {
		super(capacity);
	}
	
	@Override
	public void add(int toBeAdded) {
		if(size() < myValues.length){
    		super.add(toBeAdded);
    	}else {
    		// increase length by 10
    		myValues = Arrays.copyOf(myValues, myValues.length+10);
    		super.add(toBeAdded);
    	}    	
	}
	    

	@Override
	public void insert(int toInsert, int insertPos) {
		if (size() < myValues.length ){
			super.insert(toInsert, insertPos);
        }else{
        	myValues = Arrays.copyOf(myValues, myValues.length+1);
    		super.insert(toInsert, insertPos);
        }
        
    }
	
	
	// halve the capacity when myCurrent < 1/4 of the array length, 
	// except when the capacity - myCount <= 10.
	
	@Override
	public void remove (int pos) {
		super.remove(pos);
		if (myValues.length - myCount > 10 && myCount < myValues.length/4){
			myValues = Arrays.copyOf(myValues, myValues.length/2);
		}
	}
	
	
}
