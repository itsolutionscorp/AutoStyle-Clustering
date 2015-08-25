public class ModNCounter extends Counter{

    int myN;

    public ModNCounter (int n) {
    	myN = n;
    }

    public void increment ( ) {
    	if (super.value() < myN - 1){
    		super.increment();
    	}
    	else {
    		reset();
    	}
    }
}
