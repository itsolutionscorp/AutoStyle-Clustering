import java.util.*;

public class Fibonacci {
	int callsToFib;
	int result;
	HashMap<Integer, Integer> fibs;

	public Fibonacci(int n){
		this.callsToFib = 0;
		fibs = new HashMap<Integer, Integer>();
		fibs.put(0, 0);
		fibs.put(1, 1);
		this.result = fib(n);
	}

	private int fib(int n) {
		callsToFib++;
		int returnValue = 0;
		if(fibs.containsKey(n)){
			return fibs.get(n);
		} else{
			returnValue = fib(n - 1) + fib(n - 2);
			fibs.put(n, returnValue);
		}
		return returnValue;
	}

}
