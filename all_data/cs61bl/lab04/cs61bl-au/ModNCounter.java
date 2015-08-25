package lab04;

public class ModNCounter {
	
	private int myN;
	private int myCount;

	public ModNCounter(int n) {
		myN = n;
		myCount = 0;
	}

	public void increment() {
		myCount++;
		if (myCount >= myN) reset();
	}

	public void reset() {
		myCount = 0;
	}

	public int value() {
		return myCount;
	}

}
