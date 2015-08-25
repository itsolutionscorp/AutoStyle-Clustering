import java.util.HashMap;
public class Fibonacci {
	int callsToFib;
	int result;
	HashMap<Integer,Integer> fibMap = new HashMap<Integer, Integer>();
	public Fibonacci(int n){
		this.callsToFib = 0;
		this.result = fib(n);
	}
	
	private int fib(int n) {	
		int returnValue = 0;
		callsToFib++;

		if (n == 0) {
			returnValue = 0;
		} else if (n == 1) {
			returnValue = 1;
		} else {
			if(fibMap.containsKey(n)){
				returnValue = fibMap.get(n);
			}
			else{
			returnValue = fib(n - 1) + fib(n - 2);
			
			}
			fibMap.put(n, returnValue);
			
		}
		return returnValue;
	}
	

}