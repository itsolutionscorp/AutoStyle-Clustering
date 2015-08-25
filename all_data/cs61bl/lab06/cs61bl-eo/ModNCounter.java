
public class ModNCounter extends Counter {

		private int myN;

		public ModNCounter(int N) {
			myN = N;
		}
		
		@Override 
		public void increment() {
			super.increment();
			if (myN == super.value() ) {
				super.reset();
			}
		}
	}