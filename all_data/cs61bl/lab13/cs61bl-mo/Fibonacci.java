import java.util.HashMap;

public class Fibonacci {
	int callsToFib;
	int result;
	HashMap<Integer, Integer> val;

	public Fibonacci(int n){
		this.callsToFib = 0;
		val = new HashMap<Integer, Integer>();
		this.result = fib(n);
	}
	
	private int fib(int n) {
		callsToFib++;
		if (val.containsKey(n)){
			return val.get(n);
		} else if (n == 0) {
			return 0;
		} else if (n == 1) {
			return 1;
		} else {
			int returnValue = fib(n - 1) + fib(n - 2);
			val.put(n, returnValue);
			return returnValue;
		}
	}

}
