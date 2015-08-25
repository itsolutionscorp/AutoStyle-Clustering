public class ModNCounter {

	private int myCount;
	int myN;

	public ModNCounter(int val) {
		myCount = 0;
		myN = val;
	}

	public void increment() {
		myCount++;
		if (myCount == myN) {
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
