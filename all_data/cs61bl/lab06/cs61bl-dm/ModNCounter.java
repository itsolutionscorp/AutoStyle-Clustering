
public class ModNCounter extends Counter {
	
	public ModNCounter(int n){
		myN = n;
	}
	
	@Override
	public void increment() {
      	super.increment();
      	if(value() == myN) {
      		reset();
      	}
    }
	
	//Instance variables of subclass
	int myN;
}
