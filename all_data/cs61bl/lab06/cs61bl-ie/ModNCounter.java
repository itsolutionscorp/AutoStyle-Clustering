public class ModNCounter extends Counter{
	
	private int myN;
	
	public ModNCounter(int n) {
        super.reset();
        myN = n;
    }

	public void increment() {
		if(super.value() < myN){
			super.increment();
		}
		else{
			super.reset();
		}
	}
}