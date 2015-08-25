import java.util.HashMap;

public class Fibonacci {
	int callsToFib;
	int result;
	static HashMap<Integer, Integer> map;

	public Fibonacci(int n){
		this.callsToFib = 0;
		this.result = fib(n);
		map = new HashMap<Integer, Integer>();
		map.put(0, 0);
		map.put(1, 1);
	}
	
	private int fib(int n) {
		callsToFib++;
		if (n == 0) {
			return 0;
		} else if (n == 1) {
			return 1;
		} else {
			if (map.containsKey(n)) {
				return map.get(n);
			}
			int returnValue = fib(n - 1) + fib(n - 2);
			map.put(n, returnValue);
			return returnValue;
		}
	}

}
