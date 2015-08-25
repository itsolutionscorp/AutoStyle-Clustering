
public class ModNCounter extends Counter {
    int myN;

    public ModNCounter(int n) {
        super();
        myN = n;
    }

    @Override
    public void increment() {
    	if (this.value() < myN - 1) {
    		super.increment();
    	} else {
    		this.reset();
    	}
    }

}
