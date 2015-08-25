import java.util.HashMap;

public class Fibonacci {
	int callsToFib;
	int result;
	HashMap<Integer,Integer> memo = new HashMap<Integer,Integer>();

	public Fibonacci(int n){
		this.callsToFib = 0;
		this.result = fib(n);
	}
	
	private int fib(int n) {
		callsToFib++;
		if (n == 0) {
			memo.put(0,0);
			return 0;
		} else if (n == 1) {
			memo.put(1,1);
			return 1;
		} else {
			int returnValue = 0;
			if (n < memo.size()){
				returnValue = memo.get(n);
			} else {
				returnValue = fib(n - 1) + fib(n - 2);				
				memo.put(n, returnValue);
			}
			return returnValue;
		}
	}

}
