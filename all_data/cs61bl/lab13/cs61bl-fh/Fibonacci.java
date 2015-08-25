import java.util.HashMap;

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
		if (n == 0) {
			return 0;
		} else if (n == 1) {
			return 1;
		} else if (map.containsKey(new Integer(n))) {
			return map.get(new Integer(n)).intValue();
		} else {
			int returnValue = fib(n - 1) + fib(n - 2);
			map.put(new Integer(n), new Integer(returnValue));
			return returnValue;
		}
	}
}
