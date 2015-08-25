
public class ModNCounter extends Counter {
	int x;
	
	public ModNCounter(int n) {
		x = n;
	}
	
    public void increment() {
        super.increment();	
        if (super.value() == x) {
        	super.reset();
        }
    }
}
