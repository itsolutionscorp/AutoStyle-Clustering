
public class ResizableIntSequence extends IntSequence {
	
	public ResizableIntSequence(int capacity) {
		super(capacity);
	}
	
	@Override
	public void add(int toBeAdded) {
		if (myValues.length == this.size()) {
			int[] temp = new int[((myValues.length) + Math.max((myValues.length / 4), 1))];
			for (int item = 0; item < this.size(); item++) {
				temp[item] = this.myValues[item];
			}
			myValues = temp;
			super.add(toBeAdded);
		} else {
			super.add(toBeAdded);
	} 
	}
	
	@Override
	public void insert(int toInsert, int insertPos) {
		if (myValues.length == this.size()) {
			int[] temp = new int[((myValues.length) + Math.max((myValues.length / 4), 1))];
			for (int item = 0; item < this.size(); item++) {
				temp[item] = this.myValues[item];
			}
			myValues = temp;
		} super.insert(toInsert, insertPos);
	}
	
	@Override
	public int remove(int pos) {
		if ((myValues.length * .75) > (this.size())) {
			int temp2 = super.remove(pos);
			int[] temp = new int[((myValues.length) - Math.max(1, (myValues.length / 4)))];
			for (int item = 0; item < temp.length; item++) {
				temp[item] = this.myValues[item];
			}
			myValues = temp;
			return temp2;
		} else {
			return super.remove(pos);
		}
	}
} 