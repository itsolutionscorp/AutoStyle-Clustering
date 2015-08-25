public class ModNCounter {

	private int myCount;
	private int myInt;

	public ModNCounter(int N) {
		myInt = N;
		myCount = 0;
	}

	public void increment() {
		if (myCount < (myInt - 1)) {
			myCount++;
		}
		else {
			this.reset();
		}
	}

	public void reset() {
		myCount = 0;
	}

	public int value() {
		return myCount;
	}

}
