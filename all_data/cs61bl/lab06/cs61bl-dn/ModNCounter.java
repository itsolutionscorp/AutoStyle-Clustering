
public class ModNCounter extends Counter {
	
	private int myN;
	
	public ModNCounter(int N) {
		myN = N;
	}

	public void increment() {
		super.increment();
		if (super.value() % myN == 0){
			super.reset();
		} 		
	}

}
