
public class ModNCounterTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ModNCounter modCounter = new ModNCounter(3);
		modCounter.increment();
		modCounter.increment();
		modCounter.increment();
		modCounter.increment();
		System.out.println(modCounter.value()); // prints 1

	}

}
