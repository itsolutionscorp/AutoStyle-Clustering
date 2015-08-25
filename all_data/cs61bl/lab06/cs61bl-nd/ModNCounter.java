public class ModNCounter extends Counter{
	
	int myN;
	
	public ModNCounter(int n){
		myN = n;
	}
	
	public void increment() {
		if(this.value() == myN - 1){
			this.reset();
		} else {
			super.increment();
		}
	}

	
	
}