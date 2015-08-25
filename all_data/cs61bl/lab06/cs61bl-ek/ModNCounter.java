
public class ModNCounter extends Counter{	
		int number;
		public ModNCounter(int n) {
			super();
			number = n;
		}

@Override
		public void increment() {
			if (number> super.value()) {
				super.increment();
			} else{
				reset();
			}
			
		}
}
