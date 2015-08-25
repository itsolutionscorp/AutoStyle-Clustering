public class ModNCounter extends Counter {
	int valueOfK;
	
	public ModNCounter(int K){
		valueOfK = K;	
	}
	
	public static void main(String [] args){
		ModNCounter modCounter = new ModNCounter(3);
		modCounter.increment();
		modCounter.increment();
		modCounter.increment();
		modCounter.increment();
		System.out.println(modCounter.value()); // prints 1
	}
	
	public void increment() {
		if (super.value() + 1 < valueOfK){
			super.increment();
		}
		else {
			reset();
		}
	}

}
