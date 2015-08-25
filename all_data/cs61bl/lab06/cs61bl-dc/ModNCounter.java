
public class ModNCounter extends Counter
{
	private int myN;
	
	/**
	 * Constructor: sets myN to 10 by default
	 */
	public ModNCounter()
	{
		myN = 10;
	}
	public ModNCounter(int n)
	{
		myN = n;
	}
	@Override
	public void increment()
	{
		super.increment();
		if (this.value() == myN)
			this.reset();
	}
	public static void main(String[] args)
	{
		ModNCounter modCounter = new ModNCounter(3);
		modCounter.increment();
		System.out.println(modCounter.value()); // prints 1
		modCounter.reset();
		modCounter.increment();
		System.out.println(modCounter.value()); // still prints 1
		modCounter = new ModNCounter(3);
		modCounter.increment();
		modCounter.increment();
		modCounter.increment();
		modCounter.increment();
		System.out.println(modCounter.value()); // prints 1
	}
}
