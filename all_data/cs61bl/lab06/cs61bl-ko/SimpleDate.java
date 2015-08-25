
import java.util.Arrays;
public class SimpleDate implements Comparable<SimpleDate> {
		
	private int myMonth;
	private int myDay;
	
	public SimpleDate (int month, int day) {
		myMonth = month;
		myDay = day;
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
		String result = new String();
		result = myMonth + "/" + myDay;
		return result;
	}

	public int compareTo(SimpleDate o) {
		// TODO Auto-generated method stub
		if (myMonth > o.myMonth) {
			return 1;
		}
		else if (this.myMonth == o.myMonth) {
			if (this.myDay > o.myDay) {
				return 1;
			}
		}
		return -1;
	}
}
