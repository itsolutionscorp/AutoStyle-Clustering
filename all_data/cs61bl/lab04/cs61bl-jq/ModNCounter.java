public class ModNCounter {

	private int myCount;
	private int myN;
	
	public ModNCounter(int n) {
		myN = n;
		myCount = 0;	
	}

	public void increment() {
		myCount++;
	}

	public void reset() {
		myCount = 0;
	}

	public int value() {
		return myCount % myN;
	}
	
	public static void main(String[] args) {
			ModNCounter c = new ModNCounter(2);
			System.out.println (c.value ( ));
			c.increment ( );
			System.out.println (c.value ( ));
			c.increment ( );
			System.out.println (c.value ( ));
			c.increment ( );
	}

}
