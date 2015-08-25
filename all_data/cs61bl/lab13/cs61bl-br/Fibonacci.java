import java.util.HashMap;

public class Fibonacci {
	int callsToFib;
	int result;
	HashMap<Integer, Integer> myHashMap = new HashMap<Integer, Integer>();

	public Fibonacci(int n){
		this.callsToFib = 0;
		this.result = fib(n);
	}
	
	private int fib(int n) {
		callsToFib++;
		if (myHashMap.containsKey(n)){
			return myHashMap.get(n);
		}
		if (n == 0) {
			myHashMap.put(n, 0);
			return 0;
		} else if (n == 1) {
			myHashMap.put(n, 1);
			return 1;
		} else {
			int returnValue = fib(n - 1) + fib(n - 2);
			myHashMap.put(n, returnValue);
			return returnValue;
		}
	}

}
