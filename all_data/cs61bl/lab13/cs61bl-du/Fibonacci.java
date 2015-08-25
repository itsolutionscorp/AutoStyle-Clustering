import java.util.HashMap;

public class Fibonacci {
	int callsToFib;
	int result;
    HashMap<Integer, Integer> table = new HashMap<Integer, Integer>();

	public Fibonacci(int n){
		this.callsToFib = 0;
		this.result = fib(n);
	}
	
	private int fib(int n) {
		callsToFib++;
		if (table.containsKey(n)) {
			return table.get(n);
		}
		if (n == 0) {
			return 0;
		} else if (n == 1) {
			return 1;
		} else {
			int returnValue = fib(n-1) + fib(n-2) ;
			table.put(n, returnValue);
			return returnValue;
		}
	}

}
