public class ModNCounter {

	private int myCount;
	private int myMod;

	public ModNCounter(int mod) {
		myCount = 0;
		myMod = mod;
	}

	public void increment() {
		myCount++;
		if (myCount == myMod) {
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
