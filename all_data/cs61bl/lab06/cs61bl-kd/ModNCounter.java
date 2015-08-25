
public class ModNCounter extends Counter {

	/**
	 * @param args
	 */
	private int myN;
	
	public ModNCounter (int x){
		myN = x;
	}
	
	public void increment() {
		if (getMyCount() == myN -1)
		{
			reset();
		}
		else
		{
			super.increment();
		}
	}
}
