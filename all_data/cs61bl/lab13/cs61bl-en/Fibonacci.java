import java.util.Hashtable;

public class Fibonacci {
	int callsToFib;
	int result;
	Hashtable<Integer, Integer> table;
	
	public Fibonacci(int n){	
		table = new Hashtable<Integer, Integer>();
		this.callsToFib = 0;
		this.result = fib(n);
		
	}
	
	private int fib(int n) {
		callsToFib++;
		if (n == 0) {
			table.put(n, 0);
			return 0;
		} else if (n == 1) {
			table.put(n, 1);
			return 1;
		} else {						
			if (table.get(n-1) != null && table.get(n-2) != null){
				return table.get(n-1) + table.get(n-2);				
			}			
			int returnValue = fib(n - 1) + fib(n - 2);
			table.put(n, returnValue);
			return returnValue;
		}
	}

}
