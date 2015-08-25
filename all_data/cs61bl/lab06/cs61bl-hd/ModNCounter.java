
public class ModNCounter extends Counter{
	int myN;
	public ModNCounter(int N) {
		myN = N;
	}
	
	public void increment() {
		super.increment();
		if (value() == myN){
			reset();		
		}
	}
}
