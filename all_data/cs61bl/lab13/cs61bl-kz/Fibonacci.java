import java.util.HashMap;

public class Fibonacci {
	
	int callsToFib;
	int result;
	HashMap<Integer, Integer> fibbin;
	
	public Fibonacci(int n){
		this.callsToFib = 0;
		fibbin = new HashMap<Integer, Integer>();
		this.result = fib(n);	
	}
	
	private int fib(int n) {
		callsToFib++;
		if (n == 0) {
			return 0;
		} else if (n == 1) {
			return 1; 
		} else if (fibbin.containsKey(n)) {
			return fibbin.get(n);
		} else {
			int returnValue = fib(n - 1) + fib(n - 2);
			fibbin.put(n, returnValue);
			return returnValue;
		}
	}

}

