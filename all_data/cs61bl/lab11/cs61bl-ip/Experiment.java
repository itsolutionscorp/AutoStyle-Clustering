import java.util.ArrayList;
import java.util.LinkedList;


public class Experiment {
	public static void Experiment1() {
		int n = 30000;
		LinkedList linked = new LinkedList();
		ArrayList array = new ArrayList();
		int k;
		double get;

		Timer t1 = new Timer();
		t1.start();
		for (k = 0; k < n; k++) {
			double value = Math.random();
			linked.add(value);
		}
		long elapsedMs1 = t1.stop();

		Timer t2 = new Timer();
		t2.start();
		for (k = 0; k < n; k++) {
			double value = Math.random();
			array.add(value);
		}
		long elapsedMs2 = t2.stop();

		Timer t3 = new Timer();
		t3.start();
		for (k = 0; k < n; k++) {
			get = (double) linked.get(k);
		}
		long elapsedMs3 = t3.stop();

		Timer t4 = new Timer();
		t4.start();
		for (k = 0; k < n; k++) {
			get = (double) array.get(k);
		}
		long elapsedMs4 = t4.stop();

		System.out.println ("LINKED ADD TEST: "+elapsedMs1 + " milliseconds elapsed");
		System.out.println ("ARRAY ADD TEST:  "+elapsedMs2 + " milliseconds elapsed");
		System.out.println ("LINKED GET TEST: "+elapsedMs3 + " milliseconds elapsed");
		System.out.println ("ARRAY GET TEST:  "+elapsedMs4 + " milliseconds elapsed");
	}
	
	public static void Experiment2() {
		int n = 500000;
		LinkedList linked = new LinkedList();
		ArrayList array = new ArrayList();
		int k;

		double[] lasts = new double[n];
		
		for (k = 0; k < n; k++) {
			double value = Math.random();
			linked.add(value);
			lasts[k] = value;
		}

		Timer t1 = new Timer();
		t1.start();
		for (k = n-1; k > 0; k--) {
			linked.lastIndexOf(lasts[k]);
			linked.remove(k);
		}
		long elapsedMs1 = t1.stop();
		System.out.println("SIZE: "+n);
		System.out.println ("TEST: "+elapsedMs1 + " milliseconds elapsed");
	}

	public static void main(String[] args) {
		Experiment2();
		}

}
