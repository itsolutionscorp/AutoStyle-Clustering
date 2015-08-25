
public class ResizableIntSequence extends IntSequence {

	public ResizableIntSequence(int capacity) {
		super(capacity);
	}

	public void add(int toBeAdded) {
		if (myCount == myCapacity) {
			int[] oldValues = new int[myCapacity];
			oldValues = myValues;
			myCapacity++;
			myValues = new int[myCapacity];
			for (int k = 0; k < oldValues.length; k++) {
				myValues[k] = oldValues[k];
			}
    	}
    	myValues[myCount] = toBeAdded;
    	myCount++;
	}

	public void insert(int toInsert, int insertPos) {
		if (myCount == myCapacity) {
			myCapacity ++;
			int[] oldValues = myValues;
			myValues = new int[myCapacity];
			for (int k = 0; k < oldValues.length; k++) {
				myValues[k] = oldValues[k];
			}
    	}
		int nextValue = myValues[0];
        for (int k = 0; k < myCount + 1; k++) {
        	if (k == insertPos) {
        		myValues[k] = toInsert;
        		toInsert = nextValue;
        		insertPos++;
        	}
        	if (k + 1 < myCount + 1) {
        	nextValue = myValues[k+1];
        	}
        }
        myCount++;
	}
	
    public void remove(int pos) {
    	if (pos < 0 || pos >= myCount) {
    		System.err.println("Index doesn't exist.");
    		System.exit(1);
    	}
    	for (int i = 0; i < myCount; i++) {
    		if (i + 1 == myCount) {
    			break;
    		}
    		int store = myValues[i+1];
    		if (i == pos) {
    			myValues[i] = store;
    			pos++;
    		}
    	}
    	myCount--;
    	myCapacity--;
    }
	
	public static void main(String[] args) {
		ResizableIntSequence a = new ResizableIntSequence(2);
		a.add(1);
		a.add(2); //full now
		System.out.println(a);
		a.add(3); //should extend
		System.out.println(a);
		a.add(4);
		System.out.println(a);
		
		a.insert(5, 4);
		System.out.println(a);
		a.insert(0, 0);
		System.out.println(a);
		a.insert(8, 2);
		System.out.println(a);
		
		a.remove(2);
		System.out.println(a);
		System.out.println(a.myCapacity);
		a.remove(0);
		System.out.println(a);
		System.out.println(a.myCapacity);
		a.remove(4);
		System.out.println(a);
		System.out.println(a.myCapacity);
		
	}
}
