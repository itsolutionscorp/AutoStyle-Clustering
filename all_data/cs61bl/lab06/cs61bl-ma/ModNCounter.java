
public class ModNCounter extends Counter{
	private int n; 
	
	public ModNCounter(int n) {
		this.n = n; 
	}
	
	public void increment() {
		if (this.value() < n - 1) {
			super.increment(); 
		}
		else {
			this.reset(); 
		}
	}
}
