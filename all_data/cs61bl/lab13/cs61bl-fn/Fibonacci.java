import java.util.HashMap;

public class Fibonacci {
	int callsToFib;
	int result;
	HashMap<Integer, Integer> record = new HashMap<Integer, Integer>();
	public Fibonacci(int n){
		this.callsToFib = 0;
		this.result = fib(n);
		
	}
	private int fib(int n) {
		callsToFib++;
		if (record.get(n) == null) {
			if (n == 0) {
				record.put(0, 0);
			} else if (n == 1) {
				record.put(1, 1);
			} else {
				record.put(n,fib(n - 1) + fib(n - 2));
			}
		}
		return record.get(n);
	}

}
