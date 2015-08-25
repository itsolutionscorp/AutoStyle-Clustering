
public class ResizableIntSequence extends IntSequence{

	/**
	 * @param args
	 */
	public ResizableIntSequence(int capacity) {
		super(capacity);
	}
	
    public void add(int toBeAdded) {
        // YOUR CODE HERE
    	if (myCount >= myValues.length) {
    		int[] newValues = new int[myValues.length * 2];
    		
    		for (int i = 0; i < myValues.length; i ++) {
    			newValues[i] = myValues[i];
    		}
    		myValues = newValues;
    	}
    	
    	super.add(toBeAdded);
    		
    	}
    
    public void insert(int toInsert, int insertPos) {
    	if (insertPos > myCount || myCount >= myValues.length) {
    		int[] newValues = new int[myValues.length * 2];
    		
    		for (int i = 0; i < myValues.length; i ++) {
    			newValues[i] = myValues[i];
    		}
    		myValues = newValues;
    	}
    	
    	super.insert(toInsert, insertPos);
    }
    public void remove(int removePos) {
    	if (myCount < myValues.length / 2) {
    		int[] newValues = new int[myValues.length - myValues.length / 4];
    		
    		for (int i = 0; i < myCount; i ++) {
    			newValues[i] = myValues[i];
    		}
    		myValues = newValues;
    		
    	}
    	super.remove(removePos); 
    }
	public static void main(String[] args) {
		ResizableIntSequence test = new ResizableIntSequence(20);
		test.add(1);
		test.add(5);
		test.add(7);
		test.remove(1);
		System.out.println(test);

	}

}
