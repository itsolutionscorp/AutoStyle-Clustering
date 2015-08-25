
public class ResizeableIntSequence extends IntSequence{

	public ResizeableIntSequence(int capacity) {
		super(capacity); 
	}
	
	public void add(int toBeAdded) {
		if (myCount == myValues.length) {
			int[] bigger_int;
			if (myValues.length >= 100) {
				bigger_int = new int[myValues.length + myValues.length/10];
			} else {
				bigger_int = new int[myValues.length + 10];
			}
			for (int i = 0; i < myValues.length; i++) {
				bigger_int[i] = myValues[i];
			}
			
			bigger_int[myCount] = toBeAdded;
			myCount += 1;
			myValues = bigger_int;
		} else {
			super.add(toBeAdded);
		}
	}
	
	public void insert(int toBeAdded, int insertPos) {
		if (myCount == myValues.length) {
			int[] bigger_int;
			if (myValues.length >= 100) {
				bigger_int = new int[myValues.length + myValues.length/10];
			} else {
				bigger_int = new int[myValues.length + 10];
			}
			for (int i = 0; i < myValues.length; i++) {
				bigger_int[i] = myValues[i];
			}
			myValues = bigger_int;
			super.insert(toBeAdded, insertPos);
		} else {
			super.insert(toBeAdded, insertPos);
		}
	}
	
	public void remove(int delPos) {
		if (myCount < myValues.length/3) {
			int[] smaller_int;
			smaller_int = new int[myValues.length/2];
			for (int i = 0; i < smaller_int.length; i++) {
				smaller_int[i] = myValues[i];
			}
			myValues = smaller_int;
			super.remove(delPos);
		} else {
			super.remove(delPos);
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ResizeableIntSequence new_seq = new ResizeableIntSequence(3);
		new_seq.add(1);
		new_seq.add(2);
		new_seq.add(3);
		System.out.println(new_seq.size());
		System.out.println(new_seq.toString());
		new_seq.add(4);
		System.out.println(new_seq.toString());
		System.out.println(new_seq.myValues[3]);
	}

}
