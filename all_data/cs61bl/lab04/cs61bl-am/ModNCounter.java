public class ModNCounter {

	private int myCount;
    private int myWrap;

	public ModNCounter(int n){
		myCount = 0;
		myWrap = n;
	}

	public void increment() {
		if (myCount < myWrap-1){
			myCount++;
		} else {
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
