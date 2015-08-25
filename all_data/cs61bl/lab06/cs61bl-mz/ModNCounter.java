
public class ModNCounter extends Counter {

	private int myN;
	
	// Constructor for ModNCounter
    public ModNCounter(int j) {
        reset();
        myN = j;
    }

    public void increment() {
		if (value() == myN - 1) {
			reset();
		} else {
			super.increment();
		}
    }
}
