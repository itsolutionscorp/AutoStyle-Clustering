
public class ModNCounter extends Counter {

	private int n;
	
	public ModNCounter(int n){
		super();
		this.n = n;
		
	}
	
	public void increment(){
		if (super.value() >= (n-1))
			super.reset();
		else
			super.increment();
	}
	
}
