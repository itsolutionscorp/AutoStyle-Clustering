import java.util.Arrays;

public class SimpleDate implements Comparable<SimpleDate> {
	
	private int month;
	private int day;
	
	// constructor
	public SimpleDate(int month, int day) {
		this.month = month;
		this.day = day;
	}
	
	public int compareTo(SimpleDate o) {
		if (this.month < o.month) {
			return -1;
		}
		else if (this.month > o.month) {
			return 1;
		}
		else if (this.day < o.day) {
			return -1;
		}
		else if (this.day > o.day) {
			return 1;
		}
		else {
			return 0;
		}
		
		// https://docs.oracle.com/javase/8/docs/api/java/lang/Comparable.html 
	}
	
	@Override
	public String toString() {
		return this.month + "/" + this.day;
	}
	
	

	/**
	 * @param args
	 */
	public static void main (String [ ] args) {
	    SimpleDate [ ] dArray = new SimpleDate [4];
	    dArray[0] = new SimpleDate (5, 2); // 5/2
	    dArray[1] = new SimpleDate (2, 9); // 2/9
	    dArray[2] = new SimpleDate (6, 3); // 6/3
	    dArray[3] = new SimpleDate (1, 11); // 1/11
	    Arrays.sort (dArray);
	    for (int k=0; k<dArray.length; k++) {
	        System.out.println(dArray [k]);
	    }
	    // should print the dates in chronological order:
	    // 1/11, 2/9, 5/2, 6/3
	}

}
