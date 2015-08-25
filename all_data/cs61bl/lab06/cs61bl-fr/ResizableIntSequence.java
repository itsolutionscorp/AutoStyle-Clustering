import java.util.ArrayList;
import java.util.List;


public class ResizableIntSequence extends IntSequence {
	
	List<Integer> myValues = new ArrayList<Integer>();
	
	public ResizableIntSequence(int capacity) {
		super(capacity);
	}
	
	@Override
	public void add(int toBeAdded) {
//		if (myValues.size() == myCount) {
//    		System.err.println("");
//    		System.exit(1);
//    	}
		if (myCount > myValues.size()) {
			myValues.add(toBeAdded);
		} else {
			myValues.add(myCount, toBeAdded);
		}
    	myCount++;
	}
	
	@Override
	public void insert (int newInt, int pos) {
    	if (pos < 0) {
			System.err.println();
			System.exit(1);
		}
    	
    	int last = myValues.get(myCount - 1);
    	
    	for (int i = myCount - 1; i >= pos; i--) {
			if (i == pos) {
				myValues.add(i, newInt);
			} else {
			myValues.set(i, myValues.get(i - 1));
			}
		}
    	
    	myValues.add(myCount, last);
    	myCount++;
    }
	
	@Override
	public int elementAt(int pos) {
		return myValues.get(pos);
	}
	
	@Override
	public int remove (int pos) {
    	if (pos < 0) {
    		System.err.println();
    		System.exit(1);
    	}
    	int num = myValues.get(pos);
		for (int i = pos; i < myValues.size(); i++) {

			if (i == pos) {
				myValues.remove(pos);
			}
		}
		myCount--;
		return num;
	}
}
