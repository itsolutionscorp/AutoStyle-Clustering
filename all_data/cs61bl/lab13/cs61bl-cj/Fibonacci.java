import java.util.HashMap;

public class Fibonacci {
	int callsToFib;
	int result;
	HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
	
	public Fibonacci(int n){
		this.callsToFib = 0;
		this.result = fib(n);
	}
	
	private int fib(int n) {
		callsToFib++;
		if (n == 0) {
			map.put(0, 0);	
			return 0;
		} else if (n == 1) {
			map.put(1, 1);
			return 1;
		} else if (map.get(n)!= null) {
			return map.get(n);
		} else {
			int returnValue = fib(n - 1) + fib(n - 2);
			map.put(n, returnValue);
			return returnValue;
		}		
	}
	
	public static void main(String args[]) {
		Fibonacci f = new Fibonacci(10);
		System.out.println(f.result);
	}

}
