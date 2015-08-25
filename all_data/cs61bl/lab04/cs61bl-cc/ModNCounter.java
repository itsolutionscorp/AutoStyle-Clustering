public class ModNCounter {

	private int myCount;
	public int N;
	
	public ModNCounter() {
		myCount = 0;
	}

	public ModNCounter(int N) {
		this();
		this.N = N;
	}
	
	public void increment() {
		myCount++;
		if (N != 0) {
			if (myCount == N) {
				this.reset();
			}
		}
	}

	public void reset() {
		myCount = 0;
	}

	public int value() {
		return myCount;
	}

}
