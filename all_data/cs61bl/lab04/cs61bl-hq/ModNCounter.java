public class ModNCounter {

	private int myCount;
	private int myN;

	public ModNCounter(int N) {
		myCount = 0;
		myN = N - 1;
	}

	public void increment() {
		if (myCount < myN) {
			myCount++;
		} else {
			myCount = 0;
		}
	}

	public void reset() {
		myCount = 0;
	}

	public int value() {
		return myCount;
	}

}

