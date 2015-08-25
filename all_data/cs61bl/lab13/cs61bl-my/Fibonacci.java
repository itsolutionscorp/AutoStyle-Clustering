import java.util.HashMap;

public class Fibonacci {
	private HashMap<Integer, Integer> memorize = new HashMap<Integer, Integer>();
	int callsToFib;
	int result;

	public Fibonacci(int n){
		this.callsToFib = 0;
		this.result = fib(n);
	}
	
	private int fib(int n) {
		callsToFib++;
		if (memorize.containsKey(n)) {
			return memorize.get(n);
		} else {
			if (n == 0) {
				memorize.put(0,0);
				return 0;
			} else if (n == 1) {
				memorize.put(1,1);
				return 1;
			} else {
				int returnValue = fib(n - 1) + fib(n - 2);
				memorize.put(n, returnValue);
				return returnValue;
			}
		}
	}

}
