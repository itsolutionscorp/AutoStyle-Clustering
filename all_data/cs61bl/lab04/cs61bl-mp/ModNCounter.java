public class ModNCounter {

	private int myCount;
	private int max;

	public ModNCounter(int n) {
		myCount = 0;
		max = n;
	}

	public void increment() {
		myCount++;
		if (this.myCount == max) {
			this.reset(); 
		}
	}

	public void reset() {
		myCount = 0;
	}

	public int value() {
		return myCount;
	}

}
