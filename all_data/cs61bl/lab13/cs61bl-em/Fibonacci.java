import java.util.HashMap;

public class Fibonacci {
	int callsToFib;
	int result;
	HashMap<Integer, Integer> cache = new HashMap<>();

	public Fibonacci(int n){
		this.callsToFib = 0;
		this.result = fib(n);
	}
	
	private int fib(int n) {
		callsToFib++;
		if (cache.containsKey(n)) {
		    return cache.get(n);
		  } 
		else {    
			if (n == 0) {
				Integer returnValue = 0;
				cache.put(n, returnValue);
				return result;
			} else if (n == 1) {
				Integer returnValue = 1;
				cache.put(n, returnValue);
				return returnValue;
			} else {
				Integer returnValue = fib(n - 1) + fib(n - 2);
				cache.put(n, returnValue);
				return returnValue;
			}
		}
	}
	
}
	
