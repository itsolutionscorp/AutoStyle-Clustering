public class Sorter {
	
	public static void sort (double[] values) {
		for (int k = 1; k < values.length; k++) {
			// Elements 0 through k-1 are in nondecreasing order:
			//   values[0] <= values[1] <= ... <= values[k-1].
			// Insert element k into its correct position, so that
			//   values[0] <= values[1] <= ... <= values[k-1].
			double temp = values[k];
			int j;
			for (j = k - 1; j >= 0 && values[j] > temp; j--) {
				values[j + 1] = values[j];
			}
			values[j + 1] = temp;
		}
	}
	
	public static void main (String[] args) throws Exception {
		if (args.length != 1) {
			System.err.println("arg should be a number");
			System.exit(1);
		}
		int n = Integer.parseInt(args[0]);
		double[] values = new double [n];
		int k;
		for (k = 0; k < n; k++) {
			values[k] = Math.random();
		}
		Timer t = new Timer();
		t.start();
		sort (values);
		long elapsedMs = t.stop();
		System.out.println (elapsedMs + " milliseconds elapsed");
		if (n < 20) {
			for (k = 0; k < n; k++) {
				System.out.println(values[k]);
			}
		}
	}

}
