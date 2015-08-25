import java.util.*;

public class Fibonacci {
	int callsToFib;
	int result;
	static HashMap<Integer, Integer> save = new HashMap<Integer, Integer>();
	// static HashMap<Integer, Integer> save_calls = new HashMap<Integer, Integer>();
	
	public Fibonacci(int n){
		this.callsToFib = 0;
		this.result = fib(n);
		if (n == 0) callsToFib = 1;
		else callsToFib = 2 * n - 1;
	}
	
	private int fib(int n) {
		if (n == 0) {
			save.put(0, 0);
			// save_calls.put(0, 1);
			// callsToFib = 1;
			return 0;
		} else if (n == 1) {
			save.put(1, 1);
			// save_calls.put(1, 1);
			// callsToFib = 1;
			return 1;
		} else {
			// callsToFib++;
			
			Integer n_1 = save.get(n-1);
			if (n_1 == null) {
				// not put before
				// compute and then put it
				n_1 = fib(n-1);
				// callsToFib += save_calls.get(n-1);
				save.put(n-1, n_1);
			} else {
				// already there
				// callsToFib += save_calls.get(n-1);
			}
			
			Integer n_2 = save.get(n-2);
			if (n_2 == null) {
				// not put before
				// compute and then put it
				n_2 = fib(n-2);
				save.put(n-2, n_2);
				// callsToFib += save_calls.get(n-2);
			} else {
				// already there
				// callsToFib += save_calls.get(n-2);
			}
			
			// save_calls.put(n, callsToFib);
			return n_1 + n_2;
		}
	}

}
