
public class ResizableIntSequence extends IntSequence {

	public ResizableIntSequence(int capacity) {
		super(capacity);
	}
    public void add(int toBeAdded) {
    	if (size() == myValues.length) {
    		int[] newmyValues = new int[size()+1];
    		for (int i=0; i<size(); i++) {
    			newmyValues[i] = myValues[i];
    		}
    		myValues = newmyValues;
    		
    	}
        myValues[myCount] = toBeAdded;
        myCount++;
    }
    
    public void insert(int toInsert, int insertPos) {
    	if (size() == myValues.length) {
    		int[] newmyValues = new int[size()+1];
    		for (int i=0; i<size(); i++) {
    			newmyValues[i] = myValues[i];
    		}
    		myValues = newmyValues;
    	}
		for (int i = size(); i>= insertPos;i--) {
			if (i == insertPos) {
				myValues[i] = toInsert;
				myCount++;
			} else {
				myValues[i] = myValues [i-1];
			}
		}
	}
    public int remove(int pos) {
    	int result = elementAt(pos);
    	if (pos < 0 || pos > size()) {
    		System.err.println("Error: Index Out of Range (GG)");
    		System.exit(1);
    	}
    	for (int i = pos; i<= size()-1;i++) {
    		if (i == size()-1) {
    			myCount--;
    		} else {
    			myValues[i]=myValues[i+1];
    		}
    	}
    	int[] newmyValues = new int[size()];
    	if (size() == 1){
    		newmyValues[0] = myValues[0];
    	} else {
    		for (int i=0; i<size()-1; i++) {
    			newmyValues[i] = myValues[i];
    		}
    	}
    		myValues = newmyValues;
    	return result;
    }    

}
