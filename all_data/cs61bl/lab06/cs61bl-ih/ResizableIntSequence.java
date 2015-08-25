
public class ResizableIntSequence extends IntSequence {
	
	public ResizableIntSequence (int capacity) {
		super(capacity);
	}
	
	public void add(int toBeAdded) {
    	if (this.size() == myValues.length) {
    		int[] newArray = new int[myValues.length*2];
    		for (int i = 0; i < this.size(); i += 1) {
    			newArray[i] = myValues[i];
    		}
    		myValues = newArray;
    		super.add(toBeAdded);
    	} else {
    		super.add(toBeAdded);
    	}
    }
	
	public int remove(int pos) {
		if (this.size() < myValues.length/2) {
    		int[] newArray = new int[myValues.length*3/4];
    		for (int i = 0; i < this.size(); i += 1) {
    			newArray[i] = myValues[i];
    		}
    		myValues = newArray;
		}
		return super.remove(pos);
    }

}
