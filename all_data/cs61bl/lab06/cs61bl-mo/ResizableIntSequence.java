
public class ResizableIntSequence extends IntSequence {
	
	
	public ResizableIntSequence(int capacity) {
		super(capacity);
    }
	
	public int length() {
		return myValues.length;
	}
	
	@Override
	public void add(int toBeAdded) {
		if (super.size() == length()) {
			int[] newSeq = new int[length() + 1];
			for (int i = 0; i < length(); i++) {
				newSeq[i] = myValues[i];
			}
			myValues = newSeq;
			super.add(toBeAdded);
		} else {
			super.add(toBeAdded);
		}
	}
	

//	@Override
//	public void insert(int toInsert, int insertPos) {
//		We didn't override this method because it calls 
//	    the overridden add method, so it still works.
//	}
	
	@Override
	public int remove(int intPos) {
		int removed = super.remove(intPos);
		if (super.size() == ((int)(.5*length()))) {
			int[] smallerSeq = new int[(int)(.75*length())];
			for (int i = 0; i < super.size(); i++) {
				smallerSeq[i] = myValues[i];
			}
		myValues = smallerSeq;	
		}
		
		
		return removed;
	}
}
