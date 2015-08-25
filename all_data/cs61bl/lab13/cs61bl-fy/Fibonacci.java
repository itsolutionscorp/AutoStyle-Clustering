import java.util.HashMap;

public class Fibonacci {
	int callsToFib;
	int result;
	HashMap<Integer, Integer> prevCalls = new HashMap<Integer, Integer>(); 

	public Fibonacci(int n){
		this.callsToFib = 0;
		this.result = fib(n);
	}
	
	private int fib(int n) {
		callsToFib++;
		if (n == 0) {
			prevCalls.put(0, 0);
			return 0;
		} else if (n == 1) {
			prevCalls.put(1, 1);
			return 1;
		} else {
			if (prevCalls.containsKey(n - 1) && prevCalls.containsKey(n - 2)) {
				result = prevCalls.get(n-1) + prevCalls.get(n-2);
				prevCalls.put(n, result);
				return result;
			}
			int returnValue = fib(n - 1) + fib(n - 2);
			return returnValue;
		}
	}

}
