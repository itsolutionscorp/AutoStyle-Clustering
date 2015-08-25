import java.util.Arrays;

public class SimpleDate implements Comparable<SimpleDate> {
	private int month;
	private int day;
	
	// 0 if equal, neg if smaller, pos if greater
	
	public SimpleDate(int newMonth, int newDay) {
		month = newMonth;
		day = newDay;
	}
	
	public int month() {
		return month;
	}
	
	public int day() {
		return day;
	}
	
	public int compareTo(SimpleDate date) {
		if (date.month() > this.month()) {
			return -1;
		} else if (date.month() < this.month()) {
			return 1;
		} else if (date.day() > this.day()) {
			return -1;
		} else if (date.day() < this.day()) {
			return 1;
		} else {
			return 0;
		}
	}
	
	
	public String toString() {
		return ("" + this.month() + "/" + this.day() + "");
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
