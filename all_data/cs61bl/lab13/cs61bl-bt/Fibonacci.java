import java.util.*;
public class Fibonacci {
	int callsToFib;
	int result;
	HashMap<Integer, Integer> memoize;

	public Fibonacci(int n){
		this.callsToFib = 0;
		this.result = fib(n);
		this.memoize = new HashMap<Integer, Integer>();
	}
	
	private int fib(int n) {
		callsToFib++;
			if (n == 0) {
				return 0;
			} else if (n == 1) {
				return 1;}
				else if (memoize.containsKey(n)){
					return memoize.get(n);
				}
			else {
				int returnValue = fib(n - 1) + fib(n - 2);
				memoize.put(n, returnValue);
				return returnValue;
			}
	}

}
