import java.util.Arrays;

public class SimpleDate implements Comparable<SimpleDate> {
	
	private int myDayOfMonth;
	private int myMonth;
	
	
	public SimpleDate(int month, int dayOfMonth) {
		myDayOfMonth = dayOfMonth;
		myMonth = month;
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
	
	public String toString() {
		return "" + myMonth + "/" + myDayOfMonth;
	}
	
	public int compareTo(SimpleDate d) {
		if (this.myMonth < d.myMonth) {
			return -1; 
		} else if (this.myMonth > d.myMonth) {
			return 1;
		} else {
			if(this.myDayOfMonth == d.myDayOfMonth) {
				return 0;
			} else if(this.myDayOfMonth < d.myDayOfMonth) {
				return -1;
			} else {
				return 1;
			}
		}
	}
}
