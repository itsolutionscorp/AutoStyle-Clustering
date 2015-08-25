import java.util.Arrays;
public class SimpleDate implements Comparable<SimpleDate> {

	private int myDayOfMonth;
    private int myMonth;

    public SimpleDate(int month, int dayOfMonth) {
    	myMonth = month;
    	myDayOfMonth = dayOfMonth;
    }
    public int compareTo(SimpleDate s) {
    	if (this.myMonth < s.myMonth) {
    		return -1;
    	}
    	if (this.myMonth > s.myMonth){
    		return 1;
    	}
    	if (this.myDayOfMonth < s.myDayOfMonth) {
    		return -1;
    	}
    	if (this.myDayOfMonth > s.myDayOfMonth) {
    		return 1;
    	}
    	return 0;
    }
    
    public String toString() {
        return "" + myMonth + "/" + myDayOfMonth;
    }
    
	public static void main(String[] args) {
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
