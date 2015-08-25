import java.util.*;

public class Fibonacci {
	int callsToFib;
	int result;
	HashMap<Integer, Integer> storage = new HashMap<Integer, Integer>();

	public Fibonacci(int n){
		this.callsToFib = 0;
		this.result = fib(n);
		storage.put(n, result);
	}
	
	private int fib(int n) {
		callsToFib++;
		if (storage.containsKey(n)) {
			return storage.get(n);
		}
		if (n == 0) {
			storage.put(n, 0);
			return 0;
		} else if (n == 1) {
			storage.put(n, 1);
			return 1;
		} else {
			int returnValue = fib(n - 1) + fib(n - 2);
			storage.put(n, returnValue);
			return returnValue;
		}
	}

}
