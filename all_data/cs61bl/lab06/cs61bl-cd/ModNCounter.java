
public class ModNCounter extends Counter {
	int mod;
	public ModNCounter(int x) {
		mod = x;
	}
	public int value() {
		return (super.value() % mod);
	}
	public static void main(String[] args) {
		ModNCounter modCtr = new ModNCounter(3);
		modCtr.increment();
		modCtr.increment();
		modCtr.increment();
		modCtr.increment();
		System.out.println(modCtr.value());
	}


}


