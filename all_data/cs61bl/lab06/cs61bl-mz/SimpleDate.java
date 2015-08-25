import java.util.Arrays;

public class SimpleDate implements Comparable<SimpleDate> {

	private int month;
	private int day;
	
	public SimpleDate(int m, int d) {
		this.month = m;
		this.day = d;
	}
	
	
	//  Returns a negative integer, zero, or a 
	// positive integer as this object is less than, 
	// equal to, or greater than the specified object. 
	public int compareTo(SimpleDate date) {
		if (date.month == this.month && date.day == this.day) {
			return 0;
		} else if (date.month * 12 + date.day < this.month * 12 + this.day) {
			return 1;
		} else {
			return -1;
		}
	}
	
	public String toString() {
		return this.month + "/" + this.day;
	}
	
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
