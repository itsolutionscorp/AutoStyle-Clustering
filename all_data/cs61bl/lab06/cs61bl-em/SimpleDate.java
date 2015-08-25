import java.util.Arrays;

public class SimpleDate implements Comparable<SimpleDate> {

	int myMonth;
	int myDay;
	
	public SimpleDate (int month, int day) {
		myMonth = month;
		myDay = day;
	}
	
	public int compareTo (SimpleDate other) {
		if (myMonth == other.myMonth && myDay == other.myDay) {
			return 0;
		}
		else if (myMonth < other.myMonth) {
			return -1;
		}
		else if (myMonth > other.myMonth) {
			return 1;
		}
		else {
			if (myDay < other.myDay) {
				return -1;
			}
			else if (myDay > other.myDay) {
				return 1;
			}
		}
		return 0;
	}
	
	@Override
	public String toString() {
		return (myMonth + "/" + myDay);
	}
	
	public static void main (String [ ] args) {
	    SimpleDate [ ] dArray = new SimpleDate [6];
	    dArray[0] = new SimpleDate (5, 2); // 5/2
	    dArray[1] = new SimpleDate (2, 9); // 2/9
	    dArray[2] = new SimpleDate (6, 3); // 6/3
	    dArray[3] = new SimpleDate (1, 11); // 1/11
	    dArray[4] = new SimpleDate (1, 8); // 1/8
	    dArray[5] = new SimpleDate (2, 10); // 2/10
	    
	    System.out.println("Before sort:");
	    for (int k=0; k<dArray.length; k++) {
	        System.out.println(dArray [k]);
	    }
	    
	    System.out.println();
	    
	    Arrays.sort (dArray);
	    System.out.println("After sort:");
	    for (int k=0; k<dArray.length; k++) {
	        System.out.println(dArray [k]);
	    }
	    // should print the dates in chronological order:
	    // 1/8, 1/11, 2/9, 2/10, 5/2, 6/3
	}
	
}
