public class ModNCounter extends Counter {
	int k;
	
	public ModNCounter (int N) {
		k = N;
	}
	
	public void increment(){
		if (value() == k-1) { // need to generalize for all N
			reset();
		} else { 
			super.increment();
		}
	}
	
	public static void main(String[] args){
		ModNCounter modCounter = new ModNCounter(3);
		modCounter.increment();
		modCounter.increment();
		modCounter.increment();
		modCounter.increment();
		System.out.println(modCounter.value()); // prints 1
	}
}