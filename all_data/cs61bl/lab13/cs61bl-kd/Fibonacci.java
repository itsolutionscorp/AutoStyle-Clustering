import java.util.HashMap;

public class Fibonacci {
	int callsToFib;
	int result;
	HashMap<Integer, Integer> results;

	public Fibonacci(int n){
		this.callsToFib = 0;
		this.results = new HashMap<Integer, Integer>();
		this.result = fib(n);
	}
	
	private int fib(int n) {
		callsToFib++;
		if (this.results.containsKey(n)) {
			return this.results.get(n);
		}
		if (n == 0) {
			this.results.put(0, 0);
			return 0;
		} else if (n == 1) {
			this.results.put(1, 1);
			return 1;
		} else {
			int returnValue = fib(n - 1) + fib(n - 2);
			this.results.put(n, returnValue);
			return returnValue;
		}
	}

}
