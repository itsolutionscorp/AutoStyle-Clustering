public class ModNCounter {

	private int myCount;
	private int myN;

	public ModNCounter(int modN) {
		myN = modN;
		myCount = 0;
	}

	public void increment() {
		myCount++;
		myCount = myCount%myN;
	}

	public void reset() {
		myCount = 0;
	}

	public int mod() {
		return myN;
	}
	
	public int value() {
		return myCount;
	}

}
