import java.util.HashMap;

public class Fibonacci {
	int callsToFib;
	int result;
	HashMap<Integer, Integer> map;

	public Fibonacci(int n) {
		this.callsToFib = 0;
		map = new HashMap<Integer, Integer>();

		this.result = fib(n);

	}

	public int fib(int n) {
		int value;
		this.callsToFib++;
		map.put(0, 0);
		map.put(1, 1);
		if (n == 0) {
			value = map.get(0);
		} else if (n == 1) {
			value = map.get(1);

		} else {

			if (!map.containsKey(n)) {
				map.put(n, fib(n - 2) + fib(n - 1));
			}
			value = map.get(n);
		}

		return value;
	}
}
