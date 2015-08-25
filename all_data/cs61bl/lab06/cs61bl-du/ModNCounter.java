
public class ModNCounter extends Counter {

    int myN;

    public ModNCounter(int N) {
        myN = N;
    }

    @Override
    public void increment() {
        if (this.value() == this.myN - 1) {
        	this.reset();
        } else {
        	super.increment();
        }
    }

}