import java.util.HashMap;

public class Fibonacci {
	int callsToFib;
	int result;
	HashMap memo;

	public Fibonacci(int n){
		this.callsToFib = 0;
		this.memo = new HashMap<Integer, Integer>();
		this.result = fib(n);
	}
	
	private int fib(int n) {
		callsToFib++;
		if (memo.containsKey(n))
			return (int) memo.get(n);
		if (n == 0) {
			return 0;
		} else if (n == 1) {
			return 1;
		} else {
			int returnValue = fib(n - 1) + fib(n - 2);
			
			memo.put(n, returnValue);
			return returnValue;
		}
	}

}
