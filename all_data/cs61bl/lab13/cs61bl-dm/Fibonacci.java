import java.util.*;

public class Fibonacci {
	int callsToFib;
	int result;
	HashMap<Integer, Integer> fibMap = new HashMap<Integer, Integer>(); 
	
	public Fibonacci(int n){
		this.callsToFib = 0;
		this.result = fib(n);
	}
	
	private int fib(int n) {
		callsToFib++;
		if (fibMap.get(n)!= null) {
			return fibMap.get(n);
		} else if (n == 0) {
			return 0;
		} else if (n == 1) {
			return 1;
		} else {
			int returnValue = fib(n - 1) + fib(n - 2);
			fibMap.put(n, returnValue);
			return returnValue;
		}
	}
	

}
