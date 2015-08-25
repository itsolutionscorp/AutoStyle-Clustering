import java.util.*;

public class Fibonacci {
	int callsToFib;
	int result;
	HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();

	public Fibonacci(int n) {
		this.callsToFib = 0;
		this.result = fib(n);
	}

	private int fib(int n) {
		callsToFib++;
		if (map.containsKey(n)) {
			return map.get(n);
		} else if (n == 0 || n == 1) {
			map.put(n, n);
			return n;
		} else {
			int returnValue = fib(n - 1) + fib(n - 2);
			map.put(n, returnValue);
			return returnValue;
		}
	}
}
