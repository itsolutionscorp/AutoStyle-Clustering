import java.util.*;

public class Fibonacci {
	int callsToFib;
	int result;
	HashMap<Integer, Integer> numbers = new HashMap<Integer, Integer>();

	public Fibonacci(int n){
		this.callsToFib = 0;
		this.result = fib(n);
	}
	
	private int fib(int n) {
		callsToFib++;
		if (n == 0) {
			return 0;
		} else if (n == 1) {
			return 1;
		} else if (numbers.containsKey(n)) {
			return numbers.get(n);
		} else {
			int returnValue = fib(n - 1) + fib(n - 2);
			numbers.put(n, returnValue);
			return returnValue;
		}
	}

}
