import java.util.Arrays;

public class ResizableIntSequence extends IntSequence{
	public ResizableIntSequence(int capacity) {
		super(capacity);
	}
	@Override
	public void add(int toBeAdded) {
		if (size()==myValues.length) {
			myValues=Arrays.copyOf(myValues,myValues.length*2);
			// get this static method from Stackflow
		}
		super.add(toBeAdded);
	}
	public void insert(int toInsert, int insertPos) {
		if (size()>=myValues.length) {
			myValues=Arrays.copyOf(myValues,myValues.length*2);
			// get this static method from Stackflow
		}
		super.insert(toInsert, insertPos);
	}
	public void remove(int pos) {
		if (size()<=myValues.length/4) {
			myValues=Arrays.copyOfRange(myValues, 0,myValues.length/2);
			// get this static method from Stackflow
		}
		super.remove(pos);
	}
}
