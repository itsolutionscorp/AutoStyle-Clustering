import java.util.*;

public class Fibonacci {
	int callsToFib;
	int result;
	private HashMap<Integer, Integer> map;

	public Fibonacci(int n){
		this.callsToFib = 0;
		map = new HashMap<Integer, Integer>();
		this.result = fib(n);
	}
	
	private int fib(int n) {
		callsToFib++;
		if (map.containsKey(n)) {
			return map.get(n);
		}
		if (n == 0) {
			map.put(n, 0);
			return 0;
		} else if (n == 1) {
			map.put(n, 1);
			return 1;
		} else {
			int returnValue = fib(n - 1) + fib(n - 2);
			map.put(n, returnValue);
			return returnValue;
		}
	}
}
