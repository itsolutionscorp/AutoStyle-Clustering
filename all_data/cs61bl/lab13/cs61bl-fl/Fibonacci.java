import java.util.*;

public class Fibonacci {
    int callsToFib; //How many times the method has been called.
    int maxDepth;   //The max recursion depth called thus far.
    int result;     //To return after calculation.
    HashMap<Integer,Integer> previous = new HashMap<Integer,Integer>();

    public Fibonacci(int n){
        this.callsToFib = 0;
        previous.put(0, 0);
        previous.put(1, 1);
        maxDepth = 1;
        this.result = fib(n);
        System.out.println("Number: " + n + "\tResult: " + result + "\tCalls: " + callsToFib);
    }

    private int fib(int n) {
        callsToFib ++;
        if (n > maxDepth) {
            int toReturn = fib(n-1) + fib(n-2);
            previous.put(n, toReturn);
            maxDepth = n;
            return toReturn;
        }
        return previous.get(n);
    }
}

