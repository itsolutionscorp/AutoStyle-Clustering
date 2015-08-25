public class ModNCounter {
	
	int myN;

	private int myCount;

	public ModNCounter() {
		myCount = 0;
	}
	
	public ModNCounter(int N) {
		myCount = 0;
		myN = N;
	}

	public void increment() {
		myCount++;
		if (myCount % myN == 0){
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
