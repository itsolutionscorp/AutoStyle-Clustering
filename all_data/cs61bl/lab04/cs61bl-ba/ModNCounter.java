public class ModNCounter {

	private int myCount;
	public int myN;

	public ModNCounter(int N) {
		myN = N;
	}

	public void increment() {
		if(myCount<myN-1) {
			myCount++;
		} else {
			myCount=0;
		}
	}

	public void reset() {
		myCount = 0;
	}

	public int value() {
		return myCount;
	}

}
