import java.util.HashMap;

public class Fibonacci {
	int callsToFib;
	int result;
	HashMap<Integer,Integer> list;
	

	public Fibonacci(int n){
		this.list = new HashMap<Integer,Integer>();
		this.callsToFib = 0;
		this.result = fib(n);
		
	}
	
	private int fib(int n) {
		callsToFib++;
		if (list.containsKey(n)) {
			return list.get(n);
		}
		if (n == 0) {
			list.put(n, 0);
			return 0;
		} else if (n == 1) {
			list.put(n, 1);
			return 1;
		} else {
			int returnValue = fib(n - 1) + fib(n - 2);
			list.put(n, returnValue);
			return returnValue;
		}
	}
}
