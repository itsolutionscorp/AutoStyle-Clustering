public class ModNCounter {

	private int myCount;
	int myNum;

	public ModNCounter(int num) {
		myCount = 0;
		myNum = num;
	}

	public void increment() {
		if (myCount != myNum) {
			myCount++;
		}
		else {
			reset();
		}
	}

	public void reset() {
		myCount = 0;
	}

	public int value() {
		return myCount;
	}

}
