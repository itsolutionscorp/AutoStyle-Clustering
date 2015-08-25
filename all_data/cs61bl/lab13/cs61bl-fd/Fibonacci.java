import java.util.HashMap;

public class Fibonacci {
	int callsToFib;
	int result;
	HashMap<Integer, Integer> store;

	public Fibonacci(int n){
		this.callsToFib = 0;
		this.store = new HashMap<Integer, Integer>();
		this.result = fib(n);
	}
	
	private int fib(int n) {
		callsToFib++;
		if (n == 0) {
			return 0;
		} else if (n == 1) {
			return 1;
		} else {
			if (store.containsKey(n)) {
				return store.get(n);
			}
			int returnValue = fib(n - 1) + fib(n - 2);
			store.put(n, returnValue);
			return returnValue;
		}
	}

}
