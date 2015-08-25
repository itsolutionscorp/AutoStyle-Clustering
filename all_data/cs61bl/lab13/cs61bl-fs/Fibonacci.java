import java.util.HashMap;

public class Fibonacci {
	int callsToFib;
	int result;
	HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();

	public Fibonacci(int n){
		this.callsToFib = 0;
		this.result = fib(n);
	}
	
	private int fib(int n) {
		callsToFib++;
		
		if (map.get(n) != null) {
			return map.get(n);
		}
		
		if (n == 0) {
			map.put(0, 0);
			return 0;
		} else if (n == 1) {
			map.put(1, 1);
			return 1;
		} else {
			int returnValue = fib(n - 2) + fib(n - 1);
			map.put(n, returnValue);
			return returnValue;
		}
	}

}
