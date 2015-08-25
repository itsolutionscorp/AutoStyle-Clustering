import java.util.HashMap;

public class Fibonacci {
	int callsToFib;
	int result;
	private HashMap<Integer, Integer> memoized;

	public Fibonacci(int n) {
		this.callsToFib = 0;
		this.memoized = new HashMap<Integer, Integer>();
		this.result = fib(n);
	}

	private int fib(int n) {
		callsToFib++;
		if (this.memoized.containsKey(n)) {
			return this.memoized.get(n);
		} else {
			if (n == 0) {
				return 0;
			} else if (n == 1) {
				return 1;
			} else {
				int returnValue = fib(n - 1) + fib(n - 2);
				this.memoized.put(n, returnValue);
				return returnValue;
			}
		}
	}

}
