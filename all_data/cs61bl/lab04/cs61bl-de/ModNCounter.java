public class ModNCounter {

	private int myCount;
	private int bound;

	public ModNCounter(int N) {
		myCount = 0;
		bound = N;
		
		
	}

	public void increment() {
		if (myCount < bound) {
			myCount ++;
		} else {
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
