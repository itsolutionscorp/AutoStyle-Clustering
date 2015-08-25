import java.util.Arrays;

public class SimpleDate extends Date implements Comparable<SimpleDate> {
	
	public int month;
	public int day;
	
	public SimpleDate(int month, int day) {
		super(0, month, day);
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

	@Override
	public int compareTo(SimpleDate o) {
		// TODO Auto-generated method stub
		if (this.month() > o.month()) {
			return 1;
		} else if (this.month() == o.month() && this.dayOfMonth() > o.dayOfMonth()) {
			return 1;
		} else if (this.month() == o.month() && this.dayOfMonth() == o.dayOfMonth()) {
			return 0;
		} else {
			return -1;
		}
	}
	
	@Override
	public String toString() {
        return "" + month() + "/" + dayOfMonth();
    }

	@Override
	public int dayOfYear() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Date nextDate() {
		// TODO Auto-generated method stub
		return null;
	}

}