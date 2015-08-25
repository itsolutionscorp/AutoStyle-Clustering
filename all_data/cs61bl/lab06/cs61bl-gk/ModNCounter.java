
public class ModNCounter extends Counter{

	private int modCap;
	
	public ModNCounter (int modCap) {
		super();
		this.modCap = modCap;
	}
	
	public void increment () {
		if (value() + 1 == modCap) {
			reset();
		}
		else {
			super.increment();
		}
	}
	
	public static void main (String[] args) {
		ModNCounter mC = new ModNCounter(3);
		System.out.println(mC.value());
		mC.increment();
		System.out.println(mC.value());
		mC.increment();
		mC.increment();
		System.out.println(mC.value());
	}
}
