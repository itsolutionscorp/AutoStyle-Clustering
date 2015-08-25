import java.util.*;

public class Fibonacci {
	int callsToFib;
	int result;
	HashMap<Integer,Integer> myMap = new HashMap<Integer,Integer>(2000);
	int returnValue;

	public Fibonacci(int n){
		this.callsToFib = 0;
		this.result = fib(n);
	}
	
	private int fib(int n) {
		callsToFib++;
		if (n == 0) {
			myMap.put(n,returnValue);
			return 0;
		} else if (n == 1) {
			myMap.put(n,returnValue);
			return 1;
		} else {
			if (myMap.containsKey(n-1) & myMap.containsKey(n-2)){
				returnValue = myMap.get(n-1) + myMap.get(n-2);
			} else if (myMap.containsKey(n-1)) {
				returnValue = myMap.get(n-1) + fib(n-2);
			} else if (myMap.containsKey(n-2)) { 
				returnValue = myMap.get(n-2) + fib(n-1);
			} else {
				returnValue = fib(n-1) + fib(n-2);
			}
		}
		if (!myMap.containsKey(n)){
			myMap.put(n,returnValue);
		}
		return returnValue;
	}
}
