
public class ResizableIntSequence extends IntSequence {

	public ResizableIntSequence (int cap) {
		super(cap);
	}
	
	@Override
	public void add (int toBeAdded) {
		if (myCount < myValues.length) {
			super.add(toBeAdded);
		}
		else {
			int[] myValuesTemp = new int[myValues.length * 2];		
			for (int i = 0; i < myValues.length; i ++) {
				myValuesTemp[i] = myValues[i];
			}
			myValues = myValuesTemp;
			super.add(toBeAdded);
		}
	}
	
	public void insert (int newInt, int pos) {
		if (myCount < myValues.length) {
			super.insert(newInt, pos);
		}
		else {
			int[] myValuesTemp = new int[myValues.length * 2];		
			for (int i = 0; i < myValues.length; i ++) {
				myValuesTemp[i] = myValues[i];
			}
			myValues = myValuesTemp;
			super.insert(newInt, pos);
		}
	}
	
	public int remove (int pos) {
		int toReturn;
		toReturn = super.remove(pos);
		if (myCount < myValues.length/10){
			int[] myNewVals = new int[myValues.length/2+1];
			for (int i = 0; i < myCount; i++) {
				myNewVals[i] = myValues[i];
			}
			myValues = myNewVals;
		}
		return toReturn;
	}
}
