import java.util.HashMap;

public class Fibonacci {
	int callsToFib;
	int result;
	HashMap<Integer, Integer> fibValue;

	public Fibonacci(int n){
		this.callsToFib = 0;
		fibValue = new HashMap<Integer, Integer>();
		this.result = fib(n);
	}
	
	private int fib(int n) {
		callsToFib++;
		if (fibValue.containsKey(n)){
			return fibValue.get(n);
	    } else if (n == 0) {
	    	fibValue.put(0,0);
			return 0;
		} else if (n == 1) {
			fibValue.put(1,1);
			return 1;
		} else {
			int returnValue = fib(n - 1) + fib(n - 2);
			fibValue.put(n,returnValue);
			return returnValue;
		}
	}

}
