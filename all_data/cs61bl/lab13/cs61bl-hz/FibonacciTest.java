import junit.framework.TestCase;

public class FibonacciTest extends TestCase {

	// This test should pass already
	public void testFibResult() {
		Fibonacci fib = new Fibonacci(0);
		assertTrue(fib.result == 0);
		fib = new Fibonacci(1);
		assertTrue(fib.result == 1);
		fib = new Fibonacci(2);
		assertTrue(fib.result == 1);
		fib = new Fibonacci(3);
		assertTrue(fib.result == 2);
		fib = new Fibonacci(4);
		assertTrue(fib.result == 3);
		fib = new Fibonacci(5);
		assertTrue(fib.result == 5);
		fib = new Fibonacci(6);
		assertTrue(fib.result == 8);
		fib = new Fibonacci(7);
		assertTrue(fib.result == 13);
		fib = new Fibonacci(1000);
		assertTrue(fib.result == 1556111435);
	}

	/*
	 * This test will only pass once you modify Fibonacci.java to avoid
	 * recursive calls in fib(n) if you have already calculated the value for
	 * fib(n)
	 */
	public void testFibCalls() {
		Fibonacci fib = new Fibonacci(0);
		assertTrue(fib.callsToFib == 1);
		fib = new Fibonacci(1);
		assertTrue(fib.callsToFib == 1);
		fib = new Fibonacci(2);
		assertTrue(fib.callsToFib == 3);
		fib = new Fibonacci(3);
		assertTrue(fib.callsToFib == 5);
		fib = new Fibonacci(4);
		assertTrue(fib.callsToFib == 7);
		fib = new Fibonacci(5);
		assertTrue(fib.callsToFib == 9);
		fib = new Fibonacci(6);
		assertTrue(fib.callsToFib == 11);
		fib = new Fibonacci(7);
		assertTrue(fib.callsToFib == 13);
		fib = new Fibonacci(1000);
		assertTrue(fib.callsToFib == 1999);
	}

}
