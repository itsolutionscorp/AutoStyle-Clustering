public class ModNCounter {

	private int myCount;
	private int nMod;

	public ModNCounter() {
		myCount = 0;
		nMod = 1;
	}
	
	public ModNCounter(int n) {
		myCount = 0;
		nMod = n;
	}
	
	public void increment() {
		myCount++;
		if (nMod == myCount){
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
