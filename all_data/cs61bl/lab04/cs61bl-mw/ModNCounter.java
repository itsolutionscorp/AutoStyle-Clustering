public class ModNCounter {

	private int myCount;
	private int num;

	public ModNCounter() {
		myCount = 0;
	}
	
	public ModNCounter(int num) {
		myCount = 0;
		this.num = num;
	}

	public void increment() {
		myCount++;
		if (num != 0)
			myCount = myCount % num;
	}

	public void reset() {
		myCount = 0;
	}

	public int value() {
		return myCount;
	}

}
