 import java.util.*;

/* Timer.java implements a simple stopwatch/timer type class based
 * on Java wall-clock time.
 *
 * RUNNING() == true  <==>  start() was called with no corresponding
 *                          call to stop()
 *
 * All times are given in units of msec.
 */

public class Timer {

    /* Initializes Timer to 0 msec */
    public Timer() {
        reset();
    }

    /* Starts the timer. Accumulates time across multiple calls to start. */
    public void start() {
        running = true;
        tStart = System.currentTimeMillis();
        tFinish = tStart;
    }

    /* Stops the timer.
     * Returns the time elapsed since the last matching call to start(), or
     * 0 if no such matching call was made. */
    public long stop() {
        tFinish = System.currentTimeMillis();
        if (running) {
            running = false;
            long diff = tFinish - tStart;
            tAccum += diff;
            return diff;
        }
        return 0;
    }

    /*
     * If RUNNING()     ==>     Returns the time since last call to start()
     * If !RUNNING()    ==>     Returns total elapsed time
     */
    public long elapsed() {
        if( running )
            return System.currentTimeMillis() - tStart;

        return tAccum;
    }

    /* Stops timing if currently RUNNING() and resets the accumulated
     * time to 0. */
    public void reset() {
        running = false;
        tStart = tFinish = 0;
        tAccum = 0;
    }

    private boolean running;
    private long tStart;
    private long tFinish;
    private long tAccum;    // total time
    
    public static void main(String[] args) {
    	ArrayList A = new ArrayList();
    	LinkedList L = new LinkedList();
    	Timer t1 = new Timer();
    	Timer t2 = new Timer();
    	t1.start();
    	for (int i = 0; i < 100000; i++) {
    		A.add(i);
    	}
    	t1.stop();
    	System.out.println("For ArrayList, adding 100000 elements took:");
    	System.out.println(t1.elapsed());
    	t2.start();
    	for (int i = 0; i < 100000; i++) {
    		L.add(i);
    	}
    	t2.stop();
    	System.out.println("For Linked List, adding 100000 elements took:");
    	System.out.println(t2.elapsed());
    	t1.reset();
    	t1.start();
    	for (int i = 0; i < 100000; i++) {
    		A.get(i);
    	}
    	t1.stop();
    	System.out.println("For ArrayList, getting 100000 elements took:");
    	System.out.println(t1.elapsed());
    	t2.reset();
    	t2.start();
    	for (int i = 0; i < 100000; i++) {
    		L.get(i);
    	}
    	t2.stop();
    	System.out.println("For Linked List, getting 100000 elements took:");
    	System.out.println(t2.elapsed());
    	t1.reset();
    	t1.start();
    	for (int i = 0; i < 10000; i++) {
    		A.remove(i);
    	}
    	t1.stop();
    	System.out.println("For ArrayList, removing 10000 elements took:");
    	System.out.println(t1.elapsed());
    	t2.reset();
    	t2.start();
    	for (int i = 0; i < 10000; i++) {
    		L.remove(i);
    	}
    	t2.stop();
    	System.out.println("For Linked List, removing 10000 elements took:");
    	System.out.println(t2.elapsed());
    	
    	
    	LinkedList X = new LinkedList();
    	for (int i = 0; i < 10000; i++) {
    		X.add(i);
    	}
    	Timer T = new Timer();
    	T.start();
    	X.get(9999);
    	T.stop();
    	System.out.println("9999th item took: "+T.elapsed());
    	T.reset();
    	T.start();
    	X.get(2);
    	T.stop();
    	System.out.println("2nd item took: "+T.elapsed());
    	
    }

}
