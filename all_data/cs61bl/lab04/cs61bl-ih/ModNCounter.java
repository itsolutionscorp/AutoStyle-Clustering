public class ModNCounter {

	private int n;
	private int myCount;

	public ModNCounter(int n) {
		this.n = n;
		myCount = 0;
	}

	public int n() {
		return n;
	}

	public void increment() {
		myCount = (myCount + 1) % n;
	}

	public void reset() {
		myCount = 0;
	}

	public int value() {
		return myCount;
	}

}
