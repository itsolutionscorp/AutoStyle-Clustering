import java.util.*;

public class SimpleDate implements Comparable<SimpleDate> {
	int myDay;
	int myMonth;
	public SimpleDate(int month, int day) {
		myDay = day;
		myMonth = month;// TODO Auto-generated constructor stub
	}
	
	public int compareTo(SimpleDate date) {
		if(this.myMonth > date.myMonth){
			return 1;
		} else if(this.myMonth == date.myMonth) {
			if(this.myDay > date.myDay){
				return  1;
			} else if (this.myDay < date.myDay){
				return -1;
			} else {
				return 0;
			}
		} else {
			return -1;
		}
			
	}
	
	public String toString() {
        return myMonth+ "/" + myDay ;
    }
	
	public static void main (String [ ] args) {
	    SimpleDate [ ] dArray = new SimpleDate [4];
	    dArray[0] = new SimpleDate (5, 2); // 5/2
	    dArray[1] = new SimpleDate (2, 9); // 2/9
	    dArray[2] = new SimpleDate (6, 3); // 6/3
	    dArray[3] = new SimpleDate (1, 11); // 1/11
	    Arrays.sort(dArray);
	    for (int k=0; k<dArray.length; k++) {
	        System.out.println(dArray [k] );
	    }
	    // should print the dates in chronological order:
	    // 1/11, 2/9, 5/2, 6/3
	}
}
