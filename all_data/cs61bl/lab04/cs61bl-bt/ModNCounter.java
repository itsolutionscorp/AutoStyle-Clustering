public class ModNCounter {

	private int myCount;
	public int valueOfN;
	
	public ModNCounter(int N) {
		myCount = 0;
		valueOfN = N;	
	}

	public void increment() {
		if (myCount +1 < valueOfN){
			myCount++;
		}
		else {
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
