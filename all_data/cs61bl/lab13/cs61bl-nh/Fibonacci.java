import java.util.HashMap;
import java.util.Map;

public class Fibonacci{
	int callsToFib;
	int result;

	Map<Integer, Integer> myMap = new HashMap<Integer, Integer>();
	
	public Fibonacci(int n) {
		this.callsToFib = 0;
		this.result = fib(n);
	}
	
	private int fib(int n)	{
		callsToFib++;
		if (myMap.get(n)==null) {
			if (n == 0) {
				myMap.put(n, 0);
				return 0;
			} else if (n == 1) {
				myMap.put(n,1);
				return 1;
			} else {
				int returnValue = fib(n - 1) + fib(n - 2);
				myMap.put(n, returnValue);
				return returnValue;
			}
		} else
			return myMap.get(n);
	}

}
