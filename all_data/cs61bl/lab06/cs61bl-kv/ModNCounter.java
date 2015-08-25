
public class ModNCounter extends Counter{
	
	int myN;
	
	public ModNCounter (int n) {
		super();
		this.myN = n;
	}
	
	public void increment() {
		super.increment();
		if (this.value() == myN){
			this.reset();
		}
	}
}
