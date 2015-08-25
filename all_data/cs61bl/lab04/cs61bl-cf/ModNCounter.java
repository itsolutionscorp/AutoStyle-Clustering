public class ModNCounter {

	private int myCount;
	private int myN;

	public ModNCounter(int myN) {
		this.myN = myN;
		myCount = 1;
	}

	public void increment() {
		System.out.println(myCount % myN);
		myCount = myCount + 1;
	}

	public void reset() {
		myCount = 1;
	}

	public int value() {
		return myCount;
	}
	
	public int nValue() {
		return myN;
	}

}
