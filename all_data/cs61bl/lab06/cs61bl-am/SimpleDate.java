import java.util.Arrays;
public class SimpleDate implements Comparable<SimpleDate> {
	public int myDayOfMonth;
    public int myMonth;
	public SimpleDate(int month, int dayOfMonth) {
		myMonth = month;
		myDayOfMonth = dayOfMonth;
	}
	
	public int compareTo(SimpleDate other){
		if (myMonth!=other.myMonth) {
			return myMonth-other.myMonth;
		} else if (myDayOfMonth!=other.myDayOfMonth){
			return myDayOfMonth-other.myDayOfMonth;
		} else {
			return 0;
		}		
	}
	
	 public String toString() {
	        return "" + myMonth + "/" + myDayOfMonth;
	 }
	
	public static void main (String [ ] args) {
	    SimpleDate [ ] dArray = new SimpleDate [4];
	    dArray[0] = new SimpleDate (5, 2); // 5/2
	    dArray[1] = new SimpleDate (2, 9); // 2/9
	    dArray[2] = new SimpleDate (6, 3); // 6/3
	    dArray[3] = new SimpleDate (1, 11); // 1/11
	    Arrays.sort(dArray);
	    for (int k=0; k<dArray.length; k++) {
	        System.out.println(dArray [k]);
	    }
	    // should print the dates in chronological order:
	    // 1/11, 2/9, 5/2, 6/3
	}
}
