public class ModNCounter {

	private int myCount, n;

	public ModNCounter(int x) {
		n = x;
		myCount = 0;
	}

	public void increment() {
		myCount ++;
		if (myCount == n){
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
