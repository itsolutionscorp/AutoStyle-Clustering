import java.util.*;

public class SimpleDate implements Comparable<SimpleDate> {

	int myMonth;
	int myDay;
	public SimpleDate(int month, int dayOfMonth) {
		myMonth = month;
		myDay = dayOfMonth;
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
	public int compareTo(SimpleDate date) {
		if (myMonth>date.myMonth) {
			return 1;
		} else if (myMonth == date.myMonth) {
			if (myDay > date.myDay) {
				return 1;
			} else if (myDay == date.myDay) {
				return 0;
			} else {
				return -1;
			} 
		} else {
			return -1;
		}
	}
	public String toString() {
		String result = new String(myMonth + "/"+myDay);
		return result;
	}
}
