
public class ResizableIntSequence extends IntSequence {

	public ResizableIntSequence(int capacity) {
		super(capacity);
	}

	public void add(int toBeAdded) {
		if (myCount == myValues.length) {
			int[] pre = myValues;
			myValues = new int[myCount + 10];
			for (int idx = 0; idx < this.myCount; idx++) {
				myValues[idx] = pre[idx];
			}
		}
		myValues[myCount] = toBeAdded;
		myCount++;

	}

	public void insert(int toInsert, int insertPos) {
		// if (insertPos < 0 || insertPos > myCount) {
		// int [] pre = myValues;
		// myValues = new int[myCount+10];
		// for (int idx = 0; idx<this.myCount; idx++) {
		// myValues[idx] = pre[idx];
		// }
		// }
		//
		// for (int k = myCount; k > insertPos; k--) {
		// myValues[k] = myValues[k - 1];
		// }
		// myValues[insertPos] = toInsert;
		// myCount++;

		if (insertPos < 0 || insertPos >= myCount) {
			System.err.println("Index does not exist.");
			System.exit(1);
		} else if (myCount == myValues.length) {
			int[] pre = new int[myCount + 10];
			myCount++;
			for (int i = myCount - 1; i >= insertPos; i--) {
				if (i == insertPos) {
					pre[i] = toInsert;
					break;
				}
				pre[i] = myValues[i - 1];
			}
			for (int i = 0; i < insertPos; i++) {
				pre[i] = myValues[i];
			}

			myValues = pre;
		} else {
			for (int i = myCount; i >= insertPos; i--) {
				if (i == insertPos) {
					myValues[i] = toInsert;
					break;
				}
				myValues[i] = myValues[i - 1];
			}
			myCount++;
		}
	}

	public void remove(int toBeRemoved) {
		if ((myValues.length - (myCount-1)) > 9) {
			int[] pre = new int[myCount];
			for (int idx = 0; idx < myCount; idx++) {
				pre[idx] = myValues[idx];
			}
			myCount--;
			for (int ind = toBeRemoved; ind < myCount; ind++) {
				pre[ind] = pre[ind + 1];
			}
			
			myValues = pre;

		} else {

			myCount--;
			for (int ind = toBeRemoved; ind <= myCount; ind++) {
				myValues[ind] = myValues[ind + 1];
			}
		}
	}

//	public static void main(String[] args) {
//		ResizableIntSequence r = new ResizableIntSequence(3);
//		r.add(1);
//		r.add(2);
//		r.add(3);
//		r.insert(999, 1);
//		System.out.println(r.toString());
//		System.out.println(r.myCount);
//		System.out.println(r.myValues.length);
//		
//		r.remove(1);
//		System.out.println(r.toString());
//		System.out.println(r.myCount);
//		System.out.println(r.myValues.length);
//	}

}
