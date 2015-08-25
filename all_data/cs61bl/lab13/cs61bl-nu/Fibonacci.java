import java.util.HashMap;

public class Fibonacci {
	int callsToFib;
	int result;
	HashMap<Integer, Integer> myHashMap =new HashMap<Integer, Integer>();

	public Fibonacci(int n){
		this.callsToFib = 0;
		this.result = fib(n);
	}
	
	private int fib(int n) {
		callsToFib++;
		myHashMap.put(0, 0);
		myHashMap.put(1, 1);
		if (n == 0) {
			return 0;
		} else if (n == 1) {
			return 1;
		} else {
			int fib_left = fib(n-1);
			int fib_right = myHashMap.get(n-2);
			//System.out.println(fib_left);
			//System.out.println(fib_right);
			int returnValue = fib_left+fib_right;
			//int returnValue = myHashMap.get(n-1)+myHashMap.get(n-2);
			myHashMap.put(n, returnValue);
			//int returnValue = fib(n - 1) + fib(n - 2);
			return returnValue;
		}
	}

}
