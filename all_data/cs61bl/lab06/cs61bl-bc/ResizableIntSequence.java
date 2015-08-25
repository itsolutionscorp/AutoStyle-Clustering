public class ResizableIntSequence extends IntSequence {
	public ResizableIntSequence(int startCapacity) {
		super(startCapacity);
	}
	@Override
	public void add(int num) {
		if (size() == myValues.length) {
			int newValues[] = new int [2 * myValues.length];
			for (int i = 0; i < myValues.length; i++) {
				newValues[i] = myValues[i];
			}
			myValues = newValues;
		}
		super.add(num);
	}
	
	@Override
	public void insert(int toInsert, int insertPos) {
		if (size() == myValues.length) {
			int newValues[] = new int [2 * myValues.length];
			for (int i = 0; i < myValues.length; i++) {
				newValues[i] = myValues[i];
			}
			myValues = newValues;
		}
		super.insert(toInsert, insertPos);
	}
	
	@Override
	public int remove(int pos) {
		int val = super.remove(pos);
		if (myCount <= myValues.length / 4) {
			int newValues[] = new int [myValues.length / 2];
			for (int i = 0; i < myCount; i++) {
				newValues[i] = myValues[i];
			}
			myValues = newValues;
		}
		return val;
	}
	
	public static void main(String[] args) {
		ResizableIntSequence seq = new ResizableIntSequence(2);
		seq.add(1);
		seq.add(2);
		seq.insert(3, 1);
		seq.insert(4, 0);
		seq.insert(5, 2);
		seq.add(6);
		seq.add(7);
		System.out.println(seq);
		seq.remove(0);
		System.out.println(seq);
		seq.remove(0);
		System.out.println(seq);
		seq.remove(0);
		System.out.println(seq);
		seq.remove(0);
		System.out.println(seq);
		seq.remove(0);
		System.out.println(seq);
		seq.remove(0);
		System.out.println(seq);
		seq.remove(0);
		System.out.println(seq);
		System.out.println(seq.myValues.length);
	}
}
