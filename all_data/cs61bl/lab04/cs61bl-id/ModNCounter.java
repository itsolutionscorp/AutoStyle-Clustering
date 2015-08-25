public class ModNCounter {

	private int myCount;
	private int N;
	
	public ModNCounter(int N) {
		myCount = 0;
		this.N = N;
	}

	public void increment() {
		if (myCount < this.N - 1) {
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
