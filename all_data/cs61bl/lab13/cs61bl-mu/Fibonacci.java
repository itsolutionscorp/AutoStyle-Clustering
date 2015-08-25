import java.util.HashMap;
public class Fibonacci {
	int callsToFib;
	int result;
	HashMap<Integer,Integer> Book = new HashMap<Integer,Integer>();
	
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
		} else if(Book.containsKey(n)){
			return Book.get(n);
		}else{
			int returnValue = fib(n - 1) + fib(n - 2);
			Book.put(n, returnValue);
			return returnValue;
		}
	}

}
