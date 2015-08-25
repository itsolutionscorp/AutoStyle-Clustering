public class ModNCounter {

	private int myCount;
	public int max;

	public ModNCounter() {
		myCount = 0;
	}
	public ModNCounter(int arg){
		myCount = 0;
		max = arg;
	}

	public void increment() {
		myCount ++ ;
		if(max != 0)
		{if (myCount == max)
		{reset();}}
	}
		
		

	public void reset() {
		myCount = 0;
	}

	public int value() {
		return myCount;
	}

}
