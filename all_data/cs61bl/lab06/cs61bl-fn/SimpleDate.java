import java.util.Arrays;

public class SimpleDate implements Comparable<SimpleDate>{
	int month,daysOfMonth;
	public SimpleDate(int Month, int daysofmonth) {
		month=Month;
		daysOfMonth=daysofmonth;
	}
	public String toString(){
		return month+"/"+daysOfMonth;
	}
	@Override
	public int compareTo(SimpleDate toBeCompare) {
		if (month<toBeCompare.month) {
			return -1;
		} else if (month>toBeCompare.month) {
			return 1;
		} else if (daysOfMonth<toBeCompare.daysOfMonth) {
				return -1;
			} else if (daysOfMonth==toBeCompare.daysOfMonth) {
				return 0;
			} else return -1;
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
