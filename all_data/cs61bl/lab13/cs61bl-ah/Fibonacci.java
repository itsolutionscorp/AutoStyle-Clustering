import java.util.*;

public class Fibonacci {
	int callsToFib;
	int result;
	HashMap<Integer, Integer> hashBrown;

	public Fibonacci(int n){
		this.callsToFib = 0;
		hashBrown = new HashMap<>();
		this.result = fib(n);
	}
	
	private int fib(int n) {
		callsToFib++;
		Integer i = new Integer(n);
		if (hashBrown.containsKey(i)) {
			return hashBrown.get(i);
		}
		if (n == 0) {
			hashBrown.put(i, new Integer(0));
			return 0;
		} else if (n == 1) {
			hashBrown.put(i, new Integer(1));
			return 1;
		} else {
			int returnValue = fib(n - 1) + fib(n - 2);
			hashBrown.put(i, new Integer(returnValue));
			return returnValue;
			
		}
	}

}
