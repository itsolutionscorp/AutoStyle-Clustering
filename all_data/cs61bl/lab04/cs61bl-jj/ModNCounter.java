public class ModNCounter {

	private int myCount;
	private int N;

	public ModNCounter(int n) {
		myCount = 0;
		N = n;
	}

	public void increment() {
		if (myCount < N-1) {
			myCount++;
		}
		else {
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
