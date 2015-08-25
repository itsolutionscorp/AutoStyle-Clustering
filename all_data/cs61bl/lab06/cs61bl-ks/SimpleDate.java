import java.util.Arrays;

public class SimpleDate implements Comparable<SimpleDate> {
	int myMonth;
	int myDay;
	
	
	
	public SimpleDate(int X, int Y){
		
		myMonth = X;
		myDay = Y;
		
		
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
	public String toString(){
		return myMonth + "/" + myDay;
		
	}

	@Override
	public int compareTo(SimpleDate o) {
		// TODO Auto-generated method stub
		if (this.myMonth < o.myMonth) {
			return -1;}
		else if (this.myMonth == o.myMonth){
			if (this.myDay < o.myDay){
				return -1;
			}
			else if (this.myDay == o.myDay) {
				return 0;}
			return 1;}
		return 1;
		
			}
		
		
}

