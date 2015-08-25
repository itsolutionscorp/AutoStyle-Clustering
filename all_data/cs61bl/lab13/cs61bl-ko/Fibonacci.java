import java.util.HashMap;

public class Fibonacci {
	int callsToFib;
	int result;
	HashMap<Integer, Integer> cache;
	

	public Fibonacci(int n){
		cache = new HashMap<Integer, Integer>();
		this.callsToFib = 0;
		this.result = fib(n);
		
	}
	
	private int fib(int n) {
		callsToFib++;
		if (cache.containsKey(n)) {
			return cache.get(n);
		} else if (n == 0) {
			return 0;
		} else if (n == 1) {
			return 1;
		} else {
			int returnValue = fib(n - 1) + fib(n - 2);
			cache.put(n, returnValue);
			return returnValue;
		}
	}

}
