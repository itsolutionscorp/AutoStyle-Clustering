package lab04;

public class ModNCounter {

	private int myCount;
	private int myN;

	public ModNCounter(int N) {
		myN = N;
		myCount = 0;
	}
	
	public ModNCounter() {
		myCount = 0;
	}

	public void increment() {
		if (myCount == myN-1) {
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
