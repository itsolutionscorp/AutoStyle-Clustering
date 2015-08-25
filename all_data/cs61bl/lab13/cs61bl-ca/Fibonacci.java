import java.util.HashMap;

public class Fibonacci {
	int callsToFib;
	int result;
	HashMap<Integer, Integer> hash;

	public Fibonacci(int n){
		this.callsToFib = 0;
		hash = new HashMap<Integer, Integer>();
		this.result = fib(n);

		
	}
	
	private int fib(int n) {
		callsToFib++;
		if (!hash.isEmpty() && hash.containsKey(n)) {
			return hash.get(n);
		}
		if (n == 0) {
			return 0;
		} else if (n == 1) {
			return 1;
		} else {
			int returnValue = fib(n - 1) + fib(n - 2);
			hash.put(n, returnValue);
			return returnValue;
		}
	}

}
