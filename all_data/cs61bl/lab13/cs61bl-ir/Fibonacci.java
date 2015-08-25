import java.util.HashMap;
public class Fibonacci {
	int callsToFib;
	int result;
	HashMap<Integer, Integer> table = new HashMap<Integer, Integer>();

	public Fibonacci(int n){
		this.callsToFib = 0;
		this.result = fib(n);
	}
	
	private int fib(int n) {
		callsToFib++;
		if (n == 0) {
			table.put(0, 0);
			return 0;
		} else if (n == 1) {
			table.put(1, 1);
			return 1;
		} else {
			if (table.containsKey(n-1) && table.containsKey(n-2)){
				return table.get(n-1) + table.get(n-2);
			}else{
				int returnValue = fib(n - 1) + fib(n - 2);
				table.put(n, returnValue);
				return returnValue;
			}
		}
	}

}
