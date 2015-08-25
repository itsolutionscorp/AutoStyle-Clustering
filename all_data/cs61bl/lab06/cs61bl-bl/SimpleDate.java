import java.util.Arrays;

public class SimpleDate extends Date implements Comparable<SimpleDate> {
	public static int[] monthLengths = {31, 28, 31, 30, 31, 30, 31,
        31, 30, 31, 30, 31};

	public SimpleDate(int month, int day) {
		super(0, month, day);
	}

    public int dayOfYear() {
        int rtnValue = 0;
        for (int m = 0; m < month() - 1; m++) {
            rtnValue += monthLengths[m];
        }
        return rtnValue + dayOfMonth();
    }

    public int nextDate() {
        return dayOfYear() + 1;
    }

    public int compareTo(SimpleDate o) {
    	if (this.dayOfYear() < o.dayOfYear()) {
    		return -1;
    	} else if (this.dayOfYear() > o.dayOfYear()) {
    		return 1;
    	} else {
    		return 0;
    	}
    }

    @Override
    public String toString() {
    	return month() + "/" + dayOfMonth();
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