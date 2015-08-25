public class ModNCounter {

	private int myCount;
	private int myN;

	public ModNCounter(int N) {
		myCount = 0;
		myN = N;
		
	}

	public void increment() {
		if (myCount + 1 == myN) {
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
