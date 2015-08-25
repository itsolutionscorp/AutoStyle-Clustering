
public class ModNCounter extends Counter {
	private int N;
	public ModNCounter(int mod) {
		N = mod;
    }
	
	public void increment() {
        if (super.value() + 1 == N){
        	super.reset();
        } else{
        	super.increment();
        }
    }
}
