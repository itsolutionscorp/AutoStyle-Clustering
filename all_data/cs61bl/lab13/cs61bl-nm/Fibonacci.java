import java.util.HashMap;
import java.util.Map;

public class Fibonacci {
	int callsToFib;
	int result;
	Map<Integer, Integer> fibValues;

	public Fibonacci(int n){
		this.callsToFib = 0;
		this.fibValues = new HashMap<Integer, Integer>();
		this.result = fib(n);
		
	}
	
	private int fib(int n) {
		callsToFib++;
		if (n == 0) {
			return 0;
		} else if (n == 1) {
			return 1;
		} else {
			if (fibValues.containsKey(n)) {
				return fibValues.get(n);
			} else {
				int returnValue = fib(n - 1) + fib(n - 2);
				fibValues.put(n, returnValue);
				return returnValue;
			}
		}
	}

}
