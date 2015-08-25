public class ModNCounter {

	private int myCount;
	private int num;

	public ModNCounter(int N) {
		myCount = 0;
		num = N;
	}

	public void increment() {
		myCount++;
		if (myCount==num){
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
