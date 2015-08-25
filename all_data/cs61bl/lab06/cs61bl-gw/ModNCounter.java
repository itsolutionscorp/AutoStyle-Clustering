
public class ModNCounter extends Counter {
	
	int X;

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public ModNCounter (int number) {
		super ();
		X = number;
	}
	
	public void increment () {
		if (super.value() < X) {
			super.increment();
		} else {
			super.reset();
		}
	}

}
