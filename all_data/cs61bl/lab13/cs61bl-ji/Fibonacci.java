import java.util.*;
public class Fibonacci {
	int callsToFib;
	int result;
	HashMap<Integer, Integer> table;
	

	public Fibonacci(int n){
		
		this.callsToFib = 0;
		table = new HashMap<Integer,Integer>();
		table.put(0, 0);
		table.put(1, 1);
		this.result = fib(n);

	}
	
	private int fib(int n) {
		int returnValue;
		callsToFib++;
		if (table.containsKey(n)){
			returnValue = table.get(n);
		}
		else{
			returnValue = fib(n - 1) + fib(n - 2);
			table.put(n, returnValue);
		}
		
		return returnValue;
	}
}

