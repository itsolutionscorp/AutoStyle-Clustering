public class ModNCounter {

	private int myCount;
	private int X;
	

	public ModNCounter() {
		myCount = 0;
	}
	
	public ModNCounter(int N) {
		X=N;
		
	}
	

	public void increment() {
		myCount++;
		if (myCount == X) {
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
