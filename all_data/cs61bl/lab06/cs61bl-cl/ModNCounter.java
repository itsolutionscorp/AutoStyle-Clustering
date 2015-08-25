
public class ModNCounter extends Counter {
	int N;
	public ModNCounter(int n){
		super ();
		N = n;
	}

	@Override
	public void increment() {
		
		if(this.value()+1<N){
			super.increment();
		}
		else{
			super.reset();
			super.increment();
		}
		
	}
}
