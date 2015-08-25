public class ModNCounter {

	private int myCount;
	private int myN;

	public ModNCounter(int n) {
		myCount = 0;
		myN = n;
	}

	public void increment() {
		if(myCount == 0) {
			myCount++;
		}
		else if(myCount % myN != 0) {
			myCount++;
			if(myCount % myN == 0) {
				reset();
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
