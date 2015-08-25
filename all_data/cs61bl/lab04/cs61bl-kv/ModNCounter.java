public class ModNCounter {

	private int myCount;
	private int myN;

	public ModNCounter(int N) {
		myN = N;
		myCount = 0;
	}

	public void increment() {
		myCount++;
		if (this.myCount == this.myN) {
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
