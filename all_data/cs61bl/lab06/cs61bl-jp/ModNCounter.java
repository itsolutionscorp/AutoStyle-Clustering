public class ModNCounter extends Counter{

	private int myN;

	public ModNCounter() {
		super();
	}

	public ModNCounter(int n){
		super();
		myN = n;
	}

	public int value() {
		return super.value()%myN;
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
