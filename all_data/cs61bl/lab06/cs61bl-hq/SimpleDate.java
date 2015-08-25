import java.util.Arrays;
public class SimpleDate implements Comparable<SimpleDate> {
	int myMonth;
	int myDate;
	
	public SimpleDate(int month, int date) {
		myMonth = month;
		myDate = date;
	}
	
	@Override
	public int compareTo(SimpleDate o) {
		if (this.myMonth > o.myMonth || this.myMonth == o.myMonth && this.myDate > o.myDate) {
			return 1;
		} else if (this.myMonth == o.myMonth && this.myDate == o.myDate) {
			return 0;
		} else {
			return -1;
		}
	}

	public static void main (String [ ] args) {
	    SimpleDate[] dArray = new SimpleDate[4];
	    dArray[0] = new SimpleDate(5, 2); // 5/2
	    dArray[1] = new SimpleDate(2, 9); // 2/9
	    dArray[2] = new SimpleDate(6, 3); // 6/3
	    dArray[3] = new SimpleDate(1, 11); // 1/11
	    Arrays.sort (dArray);
	    for (int k=0; k<dArray.length; k++) {
	        System.out.println(dArray[k]); // The way the main method is written, the objects' 
	        							   // month and date information will not be printed.
	    }
	    // should print the dates in chronological order:
	    // 1/11, 2/9, 5/2, 6/3
	    for (int k=0; k<dArray.length; k++) {
	        System.out.print(dArray[k].myMonth + "/" + dArray[k].myDate);
	        if (k != dArray.length - 1) {
	        	System.out.print("," + " ");
	        }
	    }
	}
}
