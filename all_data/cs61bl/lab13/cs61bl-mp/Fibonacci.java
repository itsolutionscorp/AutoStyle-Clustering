import java.util.HashMap;

public class Fibonacci {
	int callsToFib;
	int result;
	HashMap<Integer, Integer> resultMap;

	public Fibonacci(int n){
		this.callsToFib = 0;
		this.resultMap = new HashMap<Integer, Integer>();
		this.result = fib(n);
	}
	
	private int fib(int n) {
		callsToFib++;
		if (this.resultMap.containsKey(n)) {
			return this.resultMap.get(n);
		} if (n == 0) {
			this.resultMap.put(0, 0);
			return 0;
		} else if (n == 1) {
			this.resultMap.put(1, 1);
			return 1;
		} else {
			int returnValue = fib(n - 1) + fib(n - 2);
			this.resultMap.put(n, returnValue);
			return returnValue;
		}
	}
}
