public class ModNCounter {

	private int myCount;
	private int mod;
	public ModNCounter(int x) {
		myCount = 0;
		mod = x;
	}

	public void increment() {
		myCount++;
	}

	public void reset() {
		myCount = 0;
	}

	public int value() {
		return myCount % mod;
	}

}
