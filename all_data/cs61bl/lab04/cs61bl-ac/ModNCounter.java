public class ModNCounter {

	private int myCount;
	private int myN;

	public ModNCounter(int n) {
		myCount = 0;
		myN = n;
	}

	public void increment() {
		if (myCount == myN) {
			reset();
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
	
	public int myN() {
		return myN;
	}

}