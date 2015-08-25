import java.util.HashMap;

public class Fibonacci {
	int callsToFib;
	int result;
	HashMap<Integer, Integer> map;

	public Fibonacci(int n){
		map = new HashMap<Integer, Integer>();
		this.callsToFib = 0;
		this.result = fib(n);
	}
	
	private int fib(int n) {
		callsToFib++;
		if (n == 0) {
			return 0;
		} else if (n == 1) {
			return 1;
		} else {
			if(map.containsKey(n)){
				return map.get(n);
			}
			int returnValue = fib(n - 1) + fib(n - 2);
			map.put(n, returnValue);
			return returnValue;
		}
	}

}
