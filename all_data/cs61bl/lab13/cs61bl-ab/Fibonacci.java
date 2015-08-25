import java.util.HashMap;

public class Fibonacci {
	int callsToFib;
	int result;
	HashMap<Integer, Integer> myMap;

	public Fibonacci(int n){
		myMap = new HashMap<Integer, Integer>();
		this.callsToFib = 0;
		this.result = fib(n);
		
		
	}
	
	private int fib(int n) {
		callsToFib++;
		if(myMap.containsKey(n)) {
			return myMap.get(n);
		}
		if (n == 0) {
			myMap.put(0, 0);
			return 0;
		} else if (n == 1) {
			myMap.put(1, 1);
			return 1;
		} else {
			int returnValue = fib(n-1)+fib(n-2);
			myMap.put(n, returnValue);
			return returnValue;
		}
	}

}
