import java.util.HashMap;

public class Fibonacci {
	int callsToFib;
	int result;
	static HashMap<Integer, Integer> myHashMap;

	public Fibonacci(int n){
		this.myHashMap = new HashMap<Integer,Integer>();
		this.callsToFib = 0;
		this.result = fib(n);
	}
	
//	private int fib(int n) {
//		callsToFib++;
//		if (n == 0) {
//			return 0;
//		} else if (n == 1) {
//			return 1;
//		} else {
//			int returnValue = fib(n - 1) + fib(n - 2);
//			return returnValue;
//		}
//	}
	
	private int fib(int n) {
		callsToFib++;
		if (myHashMap.containsKey(n)) {
			return myHashMap.get(n);
		}
		else if (n == 0) {
			myHashMap.put(0, 0);
			return 0;
		}
		else if (n == 1) {
			myHashMap.put(1, 1);
			return 1;
		}
		else {
			int returnValue = fib(n-1) + fib(n-2);
			myHashMap.put(n, returnValue);
			return returnValue;
		}
	}

}
