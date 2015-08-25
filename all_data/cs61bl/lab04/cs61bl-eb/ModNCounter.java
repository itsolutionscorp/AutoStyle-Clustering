public class ModNCounter {

	private int myCount;
	private int n; 

	public ModNCounter(int n) {
		myCount = 0;
		this.n = n; // use "this" to distinguish between instance variable and argument 
	}

	public void increment() {
		myCount++;
		if (myCount >= n) {
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
