import java.util.*;

public class Fibonacci {
	int result;
	int callsToFib;
	HashMap<Integer, Integer> memoMap = new HashMap<Integer, Integer>();
	
	public Fibonacci(int n) {
		callsToFib = 0;
		this.result = fib(n);
	}
	
	private int fib(int n) {
		callsToFib++;
		if (n == 1) {
			return 1;
		} else if (n == 0) {
			return 0;
		}
		if (memoMap.get(n) == null) {
			memoMap.put(n, fib(n-1) + (fib(n-2)));
		}
		return memoMap.get(n);
	}
}
