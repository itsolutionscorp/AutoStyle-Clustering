import java.util.Arrays;

public class SimpleDate implements Comparable<SimpleDate>{
	private int myMonth;
	private int myDay;
	
	public SimpleDate(int month, int day) {
		this.myMonth = month;
		this.myDay = day;
	}

    public int month() {
        return myMonth;
    }

    public int day() {
        return myDay;
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
        return "" + myMonth + "/" + myDay;
    }

	@Override
	public int compareTo(SimpleDate o) {
		if (this.myMonth == o.month()) {
			if (this.myDay == o.day())
				return 0;
			else if (this.myDay > o.day())
				return 1;
			else
				return -1;
		} else if (this.myMonth > o.month())
			return 1;
		else
			return -1;
	}
}
