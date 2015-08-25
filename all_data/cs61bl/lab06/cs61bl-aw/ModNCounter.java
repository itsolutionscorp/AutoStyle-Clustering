
public class ModNCounter extends Counter {
	int myLimit;
	public ModNCounter(int args) {
		myLimit = args;

	}
	public void increment ( ) {
		super.value();
		if (super.value() == (myLimit - 1)) {
			super.reset();
		} else {
        super.increment();
		}
    }
}
