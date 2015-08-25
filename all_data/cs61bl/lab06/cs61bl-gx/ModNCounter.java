
public class ModNCounter extends Counter {
	int myX;
	
	public ModNCounter(int x) {
		myX = x;
	}
	
	public void increment() {
        super.increment();
        if (super.value() >= myX) {
        	super.reset();
        }
    }
	
}
