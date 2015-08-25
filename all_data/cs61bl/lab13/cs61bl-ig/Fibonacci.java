import java.util.HashMap;

public class Fibonacci {
	int callsToFib;
	int result;
	
	HashMap<Integer,Integer> fibonacci;

	public Fibonacci(int n){

		fibonacci = new HashMap<Integer, Integer>();
		this.callsToFib = 0;
		this.result = fib(n);
	}
	
	private int fib(int n) {
		callsToFib++;
		if (fibonacci.containsKey(n)){
			int returnValue=fibonacci.get(n);
			return returnValue;
		}
		
		if (n == 0) {
			return 0;
		} else if (n == 1) {
			return 1;
		} else {
			int returnValue = fib(n - 1) + fib(n - 2);
			fibonacci.put(n, returnValue);
			return returnValue;
		}
	}
	
	

}
