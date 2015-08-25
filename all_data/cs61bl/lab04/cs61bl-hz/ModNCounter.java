public class ModNCounter {

	private int myCount;
	int myN;

	public ModNCounter(int N) {
		this.myCount = 0;
		this.myN = N;
	}
	
	public void increment() {
		if (this.myCount == this.myN - 1) {
			this.myCount = 0;
		} else {
			this.myCount++;
		}
	}

	public void reset() {
		myCount = 0;
	}

	public int value() {
		return myCount;
	}

}
