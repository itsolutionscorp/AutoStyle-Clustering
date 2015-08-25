import java.util.HashMap;

public class Fibonacci {
	int callsToFib;
	int result;
	HashMap<Integer, Integer> fibList;

	public Fibonacci(int n){
		this.callsToFib = 0;
		this.fibList =  new HashMap<Integer, Integer>();
		this.result = fib(n);


	}
	
	private int fib(int n) {
		callsToFib++;
		if (n == 0) {
			fibList.put(0, 0);
			return 0;
		} else if (n == 1) {
			fibList.put(1,1);
			return 1;
		} else if (fibList.containsKey(n)) {
			return fibList.get(n);			
		} else {
			int returnValue = fib(n - 1) + fib(n - 2);
			fibList.put(n, returnValue);
			return returnValue;
		}
	}

}
