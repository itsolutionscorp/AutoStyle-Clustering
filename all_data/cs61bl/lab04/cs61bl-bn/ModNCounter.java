public class ModNCounter {

	private int myCount;
	private int n;

	public ModNCounter(int n) {
		myCount = 0;
		this.n = n;
	}

	public void increment() {
		if (myCount == n - 1) {
			myCount = 0;
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
