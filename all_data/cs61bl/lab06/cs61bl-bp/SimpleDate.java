import java.util.Arrays;
public class SimpleDate implements Comparable<SimpleDate> {
	private int month;
	private int dayOfMonth;
	public SimpleDate(int month, int dayOfMonth) {
		this.month = month;
		this.dayOfMonth = dayOfMonth;
	}
	
	public int month() {
		return month;
	}
	
	public int dayOfMonth() {
		return dayOfMonth;
	}
	
	public int compareTo(SimpleDate o) {
		if(month() == o.month()) {
			return dayOfMonth() - o.dayOfMonth();
		}
		return month()-o.month();
	}

	public String toString() {
		return month + "/" + dayOfMonth;
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
