
public class ModNCounter extends Counter {

	private int MaxValue;
	
	public ModNCounter(int MaxValue) {		
		this.MaxValue = MaxValue;
	}
	
	
	public void increment() {
		if (super.value() == MaxValue -1){
			super.reset(); 
		} else {
			super.increment();
		}
		
		System.out.println(super.value());		
	}
}
	
	

