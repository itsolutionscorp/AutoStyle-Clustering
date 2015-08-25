public class ModNCounter {

	private int myCount;
	private int myN;

	public ModNCounter(int n) {
		assert n > 0;
		myCount = 0;
		myN = n;
	}

	public void increment() {
		if (myCount == myN - 1) {
			reset();
		} else {
			myCount++;
		}
	}

	public void reset() {
		myCount = 0;
	}

	public int value() {
		return myCount;
	}

}
