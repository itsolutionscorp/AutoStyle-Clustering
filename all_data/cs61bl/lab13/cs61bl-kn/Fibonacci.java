import java.util.HashMap;
import java.util.HashSet;

public class Fibonacci {
	int callsToFib;
	Integer result;
	private HashMap<Integer, Integer> myFib;

	public Fibonacci(Integer n){
		this.callsToFib = 0;
		myFib = new HashMap<Integer,Integer>();
		myFib.put(0, 0);
		myFib.put(1, 1);
		this.result = fib(n);
	}
	
	private Integer fib(Integer n) {
		callsToFib++;
		if (n.equals(0)) { 
			return myFib.get(0);
		} else {
			if (n.equals (1)) {
				return 1;
			} else {
				Integer returnValue = fib(n - 1) + myFib.get(n - 2);
				myFib.put(n, returnValue);
				return returnValue;
				}
			}
	}	

}
