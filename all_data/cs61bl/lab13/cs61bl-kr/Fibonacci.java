import java.util.HashMap;

public class Fibonacci {
	int callsToFib;
	int result;
	HashMap<Integer, Integer> storevalues = new HashMap<Integer, Integer>();
	

	public Fibonacci(int n){
		this.callsToFib = 0;
		this.result = fib(n);
		storevalues.put(0,0);
		storevalues.put(1,1);
		storevalues.put(2,1);
	}
	
	private int fib(int n) {
		callsToFib++;
		int returnValue = 0;
		if (n == 0) {
			return 0;
		} else if (n == 1) {
			return 1;
		} 
		if (storevalues.containsKey(n)) {
			returnValue = storevalues.get(n);
		}
		else {
			returnValue = fib(n-1) + fib(n-2);
			if(!storevalues.containsKey(n)) {
				storevalues.put(n, returnValue);
			}
			return returnValue;
			
		}
		
		return returnValue;
		
	}

}
