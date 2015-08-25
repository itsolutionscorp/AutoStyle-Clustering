import java.util.Arrays;

public class SimpleDate implements Comparable<Object> {
	

	public SimpleDate(int month, int day) {
		this.month = month;
		this.day = day;
	}
	private int month, day;
	
	public String toString() {
		return this.month + "/" + this.day;
	}
	
	@Override
	public int compareTo(Object o) {
		if (this.month > ((SimpleDate)o).month) {
			return 1;
		}
		if (this.month < ((SimpleDate)o).month) {
			return -1;
		}
		if (this.day > ((SimpleDate)o).day) {
			return 1;
		}
		if (this.day < ((SimpleDate)o).day) {
			return -1;
		}
		return 0;	
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

	
	
		

	


