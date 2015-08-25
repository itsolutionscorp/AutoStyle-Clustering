public class ModNCounter {

	private int myCount;
	private int initial;

	public ModNCounter(int n) {
		myCount = 0;
		initial= n;
	}

	public void increment() {
		
		if (myCount < initial -1)
		{
			myCount++;
		}
		else
		{
			myCount= 0;
		}
		
	}

	public void reset() {
		myCount = 0;
	}

	public int value() {
		return myCount;
	}

}
