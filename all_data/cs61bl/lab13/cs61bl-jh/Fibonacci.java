//package lab13;
import java.util.*;

public class Fibonacci {
	int callsToFib;
	int result;
	HashMap<Integer, Integer> map = new HashMap <Integer, Integer>();

	public Fibonacci(int n) {
		this.callsToFib = 0;
		this.result = fib(n);
	}

	private int fib(int n) {
		callsToFib++;
		if (n == 0) {
			return 0;
		} else if (n == 1) {
			return 1;
		} else if(map.containsKey(n)){
			return map.get(n);
		}
		else{
			int returnValue = fib(n - 1) + fib(n - 2);
			returnValue=(Integer)returnValue;
			map.put(n, returnValue);
			return returnValue;
		}
	}

}
