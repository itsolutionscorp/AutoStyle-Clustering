import java.util.HashMap;

public class Fibonacci {
	int callsToFib;
	int result;
	private HashMap<Integer, Integer> hashMap;

	public Fibonacci(int n){
		hashMap = new HashMap<Integer, Integer>();
		this.callsToFib = 0;
		this.result = fib(n);
	}
	
	private int fib(int n) {
		callsToFib++;
		if (n == 0) {
			return 0;
		} else if (n == 1) {
			return 1;
		} else if (hashMap.containsKey(n)){
			return hashMap.get(n);
		} else {
			int returnValue = fib(n-1)+ fib(n-2);
			hashMap.put(n, returnValue);
			return returnValue;
		}
	}

}
