public class ModNCounter {

	private int myCount;
	public int endvalue;

	public ModNCounter( int endingthing  ) {
		endvalue = endingthing;
		myCount = 0;
	}

	public void increment() {
		
		
		if (myCount == (endvalue-1)) {
			this.reset();
		}
		else {
		myCount++;
		}
	
		
	}

	public void reset() {
		myCount = 0;
	}

	public int value() {
		return myCount;
	}

}
