public class ModNCounter {

	private int myCount;
	int max;

	public ModNCounter(int N) {
		myCount = 0;
		max = N;
	}

	public void increment() {
		myCount++;
		if (myCount == max) {
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
