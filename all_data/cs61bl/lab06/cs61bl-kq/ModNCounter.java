
public class ModNCounter extends Counter{
	int myN;
	public ModNCounter(int i) {
		myN = i;
	}
	public void increment()  {
		if (myN-1 == super.value()) {
			super.reset();
		}
		else
			super.increment();
		
	}
}
