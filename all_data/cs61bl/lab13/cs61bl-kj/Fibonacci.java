import java.util.HashMap;

public class Fibonacci {
	int callsToFib;
	int result;
	private HashMap<Integer, Integer> myResults = new HashMap <Integer, Integer>();
	
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
			
		} else if (myResults.containsKey(n)) {
			return myResults.get(n);
		}
		else {
			int returnValue = fib(n - 1) + fib(n - 2);
			myResults.put(n, returnValue);
			return returnValue;
		}
	}

}
