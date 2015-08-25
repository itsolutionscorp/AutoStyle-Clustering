import java.util.Arrays;
public class SimpleDate implements Comparable<SimpleDate>{
	int month;
	int dayOfMonth;
	public SimpleDate(int m, int d) {
		month = m;
		dayOfMonth = d;
	}
	public int compareTo(SimpleDate obj){
		if(month<obj.month){
			return -1;
		}
		else if (month == obj.month) {
			if(dayOfMonth < obj.dayOfMonth) return -1;
			if(dayOfMonth == obj.dayOfMonth) return 0;
			return 1;
		}
		else return +1;
	}
	
	public String toString() {
		String str = "" + month + "/" + dayOfMonth;
		return str;
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
