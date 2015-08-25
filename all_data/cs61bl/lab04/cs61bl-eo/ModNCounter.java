public class ModNCounter {

	private int myCount;
	private int number;

	public ModNCounter(int N) {
		number = N;
		myCount = 0;
	}

	public void increment() {
		if (number> myCount) {
			myCount++;
		} else{
			reset();
		}
		
	}

	public void reset() {
		myCount = 0;
	}

	public int value() {
		return myCount;
	}

}
