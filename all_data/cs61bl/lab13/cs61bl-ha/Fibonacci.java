import java.util.*;
public class Fibonacci {
	int callsToFib;
	int result;
	
	private HashMap<Integer, Integer> fMap = new HashMap<Integer, Integer>();

	public Fibonacci(int n){
		this.callsToFib = 0;
		this.result = fib(n);
	}
	
	private int fib(int n) {
		callsToFib++;
		int returnValue;
		if (n == 0) {
			returnValue = 0;
		} else if (n == 1) {
			returnValue = 1;
		} else {
			if (fMap.get(n - 1) != null){
				returnValue = fMap.get(n - 1) + fMap.get(n - 2);
			} else {
				returnValue = fib(n - 1) + fib(n - 2);
			}
		}
		fMap.put(n, returnValue);
		return returnValue;
	}

}
