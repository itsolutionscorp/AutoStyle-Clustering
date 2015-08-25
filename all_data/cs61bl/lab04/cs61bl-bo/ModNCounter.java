public class ModNCounter {

	private int myCount;
	private int N;
	
	public ModNCounter(int N) {
		this.N = N;
		myCount = 0;
	}

	public ModNCounter() {
		myCount = 0;
		this.N = 4;
	}

	public void increment() {
		myCount++;
		if (myCount == N) {
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
