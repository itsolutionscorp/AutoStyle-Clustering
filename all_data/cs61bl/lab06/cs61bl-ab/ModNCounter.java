
public class ModNCounter extends Counter {
	private int module;
	public ModNCounter(int n) {
        reset();
        module = n;
    }
	public void increment ( ) {
        if (value() ==module-1)
        	reset();
        else
		increment();
    }
}

