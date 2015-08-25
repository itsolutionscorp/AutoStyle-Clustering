
public class ResizableIntSequence extends IntSequence {
	
	public ResizableIntSequence(int capacity) {
		super(capacity);
	}
	
	public void add(int toBeAdded) {
        if (myCount == myValues.length) {
        	int[] newArray = new int[myValues.length + 1];
        	for (int i = 0; i < myValues.length; i++) {
        		newArray[i] = myValues[i];
        	}
        	myValues = newArray;
        }
        super.add(toBeAdded);
    }
	
    public void insert (int newInt, int pos) {
    	if (myCount == myValues.length) {
        	int[] newArray = new int[myValues.length + 1];
        	for (int i = 0; i < myValues.length; i++) {
        		newArray[i] = myValues[i];
        	}
        	myValues = newArray;
        }
    	super.insert(newInt, pos);
	}

    public void remove (int pos) {
    	if (myCount <= myValues.length - 1) {
    		super.remove(pos);
    		int[] newArray = new int[myValues.length - 1];
        	for (int i = 0; i < myCount; i++) {
        		newArray[i] = myValues[i];
        	}
        	myValues = newArray;
        }
    	else {
    		super.remove(pos);
    	}
	}
}
