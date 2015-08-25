import java.util.HashMap;

public class Fibonacci {
	int callsToFib;
	int result;
	HashMap<Integer,Integer> memoization;

	public Fibonacci(int n){
		this.callsToFib = 0;
		this.memoization = new HashMap<Integer, Integer>(n);
		this.result = fib(n);
	}
	
	private int fib(int n) {
		callsToFib++;
		if (memoization.containsKey(n)) {
			return memoization.get(n);
		}
		if (n == 0) {
			memoization.put(0, 0);
			return 0;
		} else if (n == 1) {
			memoization.put(1, 1);
			return 1;
		} else {
			int returnValue = fib(n - 1) + fib(n - 2);
			memoization.put(n, returnValue);
			return returnValue;
		}
	}

}
