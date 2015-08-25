import java.util.Arrays;

public class SimpleDate implements Comparable<SimpleDate> {
	
	SimpleDate [] array;
	int date, month;
	
	public SimpleDate(int x){
		array = new SimpleDate[x];
	}
	
	public SimpleDate(int x, int y){
		date = y;
		month = x;
	}

	public int compareTo(SimpleDate other){
		if(this.month > other.month) return 1;
		else if (this.month < other.month) return -1;
		else {
			if(this.date > other.date) return 1;
			else if (this.date < other.date) return -1;
			else return 0;
		}
	}
	
	public String toString() {
		return month+ "/" + date;
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
