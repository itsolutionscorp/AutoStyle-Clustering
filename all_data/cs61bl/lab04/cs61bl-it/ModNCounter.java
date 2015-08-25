public class ModNCounter {

	private int myCount;
	private int myN; 

	
	public ModNCounter (int num) {
		myCount = 0;
		this.myN = num; 
	}

	public void increment() {
		if (myCount < myN-1){
			myCount++;
		}else {
			reset();
		}
	}

	public void reset() {
		myCount = 0;
	}

	public int[] value() {
		int[] a = {myCount,myN};
		return a;
	}

}
