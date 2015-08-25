import java.util.HashMap;

public class Fibonacci {
	int callsToFib;
	int result;
	@SuppressWarnings("rawtypes")
	HashMap<Integer,Integer> myfib; 

	public Fibonacci(int n){
		myfib= new HashMap<Integer, Integer>(n+1); 
		myfib.put(0, 0);
		myfib.put(1, 1);
		this.callsToFib = 0;
		this.result = fib(n);
		
	}
	
	private int fib(int n) {
		callsToFib++;
		 if (myfib.containsKey(n)){
			    return myfib.get(n);
		}else{
			myfib.put(n, fib(n - 1) + fib(n - 2));
			return myfib.get(n);
		}
	}
}
