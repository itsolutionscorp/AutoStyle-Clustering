import java.util.HashMap;

public class Fibonacci {
	int callsToFib = 0;
	int result = 0;
	HashMap<Integer, Integer> valHashMap;

	public Fibonacci(int n) {
		this.callsToFib = 0;
		this.valHashMap = new HashMap<Integer, Integer>();
		this.result = fib(n);
	}

	public int fib(int n) {
		callsToFib++;
		if (valHashMap.containsKey(n)){
			return valHashMap.get(n);
		}
		if (n == 0){
			return 0;
		} else if (n == 1) {
			return 1;
		} else {
			int returnValue = fib(n-1) + fib(n-2);
			valHashMap.put(n, returnValue);
			return returnValue; 
		}
	}
}
