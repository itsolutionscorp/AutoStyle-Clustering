import java.util.HashMap;

public class Fibonacci {
	int callsToFib;
	int result;
	HashMap<Integer, Integer> shitter;
	
	
	public Fibonacci(int n){
		this.callsToFib = 0;
		shitter = new HashMap<Integer, Integer>();
		this.result = fib(n);
		
	}
	
	private int fib(int n) {
			callsToFib++;
			if (n == 0) {
				return 0;
			} else if (n == 1) {
				return 1;
			} else {
				if (shitter.containsKey(n)){
					return shitter.get(n);
				}
				int returnValue = fib(n - 1) + fib(n - 2);
				shitter.put(n, returnValue);
				return returnValue;
			}
		}
	}


