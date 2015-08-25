public class ModNCounter {

	private int myCount;
	private int save;
	
	public ModNCounter(int l) {
		myCount = 0;
		save = l;
	}

	public void increment() {
		if (myCount == save-1) {
			myCount = 0;
			
		} else
			myCount++;
		
	}

	public void reset() {
		myCount = 0;
	}

	public int value() {
		return myCount;
	}

}
