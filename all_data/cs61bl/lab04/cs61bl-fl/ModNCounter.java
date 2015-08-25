public class ModNCounter {

	private int myCount;
	private int myN;

	public ModNCounter(int n) {
		myN = n;
		myCount = 0;
	}

	public void increment() {
		if (myN - myCount == 1)
			reset();
		else
			myCount++;
	}

	public void reset() {
		myCount = 0;
	}

	public int value() {
		return myCount;
	}

}
