public class ModNCounter extends Counter {

	public ModNCounter(int Count) { }
	
	@Override
    public void increment() {
        super.increment();
        if(super.value() == this.value()){
        	reset();
        	super.increment();
        	
        }
     }
	
	public static void main(String[] args) {	
		ModNCounter modCounter = new ModNCounter(3);
		modCounter.increment();
		modCounter.increment();
		modCounter.increment();
		modCounter.increment();
		System.out.println(modCounter.value()); // prints 1
	}

}
