import java.util.HashMap;

public class Fibonacci {
	int callsToFib;
	int result;
	HashMap<Integer, Integer> myMap;

	public Fibonacci(int n){
		this.callsToFib = 0;
		myMap = new HashMap<Integer, Integer>();
		this.result = fib(n);
	}
	
	private int fib(int n) {
		callsToFib++;
		if (n == 0) {
			return 0;
		} else if (n == 1) {
			return 1;
		} else if (myMap.containsKey(Integer.valueOf(n))) {
			return (int) myMap.get(Integer.valueOf(n));
		} else {
			int returnValue = fib(n-1) + fib(n-2);
			myMap.put(Integer.valueOf(n), Integer.valueOf(returnValue));
			return returnValue;
		}
	}

}
