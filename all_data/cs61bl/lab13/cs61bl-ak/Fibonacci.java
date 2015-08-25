import java.util.HashMap;

public class Fibonacci {
	int callsToFib;
	int result;
	HashMap<Integer, Integer> calls;

	public Fibonacci(int n){
		this.callsToFib = 0;
		this.calls = new HashMap();
		this.result = fib(n);
	}
	
	private int fib(int n) {
		callsToFib++;
		if (calls.containsKey(n)) {
			return calls.get(n);
		}
		if (n == 0) {
			return 0;
		} else if (n == 1) {
			return 1;
		} else {
			int returnValue = fib(n - 1) + fib(n - 2);
			calls.put(n, returnValue);
			return returnValue;
		}
	}

}
