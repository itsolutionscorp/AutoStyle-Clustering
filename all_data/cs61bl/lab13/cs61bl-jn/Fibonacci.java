import java.util.*;

public class Fibonacci {
	int callsToFib;
	int result;
	HashMap<Integer, Integer> memo;

	public Fibonacci(int n){
		memo = new HashMap<Integer, Integer>();
		this.callsToFib = 0;
		this.result = fib(n);	
	}
	
	private int fib(int n) {
		callsToFib++;
		if (n == 0) {
			return 0;
		} else if (n == 1) {
			return 1;
		} else {
			if (memo.containsKey(n)) {
				return memo.get(n);
			}
			int returnValue = fib(n - 1) + fib(n - 2);
			memo.put(n, returnValue);
			return returnValue;
		}
	}

}
