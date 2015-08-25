public class Fibonacci {
	int callsToFib;
	int result;
	int[] already;
	

	public Fibonacci(int n){
		this.callsToFib = 0;
		this.result = fib(n);
	}
	
	private int fib(int n) {
		callsToFib++;
		if (n > 0 && callsToFib == 1) {
			already = new int[n];
		} 
		if (n == 0) {
			return 0;
		} else if (n == 1) {
			return 1;
		} else {
			int q = already[n-1];
			if (q == 0) {
				q = fib(n-1) + fib(n-2);
				already[n-1] = q;
			}
		return q;
		}
	}

}
