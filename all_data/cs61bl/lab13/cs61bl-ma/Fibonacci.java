import java.util.HashMap;

public class Fibonacci {
	int callsToFib;
	int result;
	HashMap<Integer, Integer> calculated;

	public Fibonacci(int n){
		this.callsToFib = 0;
		this.calculated = new HashMap<Integer, Integer>(); 
		this.result = fib(n);
	}
	
	private int fib(int n) {
		callsToFib++;
		if (n == 0) {
			return 0;
		} else if (n == 1) {
			return 1;
		} else {
			if (calculated.containsKey(n)) {
				return calculated.get(n); 
			} else {
				int returnValue = fib(n - 1) + fib(n - 2);
				calculated.put(n, returnValue); 
				return returnValue;
			}
		}
	}
}
