import java.util.*;
public class Fibonacci {
	int callsToFib;
	int result;
	HashMap<Integer,Integer> table = new HashMap<Integer,Integer>();

	public Fibonacci(int n){
		this.callsToFib = 0;
		this.result = fib(n);
	}
	
	private int fib(int n) {
		callsToFib++;
		int returnValue;
		if (n == 0) {
			return 0;
		} else if (n == 1) {
			return 1;
		} else {
			if (table.get(n)!=null){
				returnValue = table.get(n);
			}
			else if (table.get(n-1)!=null && table.get(n-2)!=null){
				returnValue = table.get(n-1) + table.get(n-2);
			} else {
				returnValue = fib(n - 1) + fib(n - 2);
			}
			table.put(n, returnValue);
			return returnValue;
		}
	}

}
