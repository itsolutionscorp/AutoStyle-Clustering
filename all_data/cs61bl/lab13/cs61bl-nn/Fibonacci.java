import java.util.HashMap;

public class Fibonacci {
	int callsToFib;
	int result;
	HashMap<Integer,Integer> fibMemories=new HashMap<Integer,Integer>();

	public Fibonacci(int n){
		this.callsToFib = 0;
		this.result = fib(n);
	}
	
	private int fib(int n) {
		callsToFib++;
		if (n == 0) {
			return 0;
		} else if (n == 1) {
			return 1;
		}
		
		if(fibMemories.containsKey(n)){
			return fibMemories.get(n);
		}
		else {
			int returnValue = fib(n - 1) + fib(n - 2);
			fibMemories.put(n, returnValue);
			return returnValue;
		}
	}

}
