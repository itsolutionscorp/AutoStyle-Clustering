import java.util.Arrays;


public class SimpleDate implements Comparable<SimpleDate> {

	int month;
	int day;
	
	public SimpleDate(int month, int day) {
		// TODO Auto-generated constructor stub
		this.month=month;
		this.day=day;
		
	}

	/**
	 * @param args
	 */



	@Override
	/**
	 * 
	 * returns -1 if less than o, returns 0 if equal, returns 1 if larger
	 */
	public int compareTo(SimpleDate o) {
		// TODO Auto-generated method stub
		if (this.month>o.month){
			return 1;
		} else if (this.month<o.month){
			return -1;
		} else{
			if (this.day>o.day){
				return 1;
			}else if (this.day < o.day){
				return -1;
			}else{
				return 0;
			}
		}
	}

	public String toString() {
		return month + "/" + day;
	}
	
	
	public static void main (String [ ] args) {
	    SimpleDate [ ] dArray = new SimpleDate [4];
	    dArray[0] = new SimpleDate (5, 2); // 5/2
	    dArray[1] = new SimpleDate (2, 9); // 2/9
	    dArray[2] = new SimpleDate (6, 3); // 6/3dd
	    dArray[3] = new SimpleDate (1, 11); // 1/11
	    Arrays.sort (dArray);
	    for (int k=0; k<dArray.length; k++) {
	        System.out.println(dArray [k].toString());
	    }
	    // should print the dates in chronological order:
	    // 1/11, 2/9, 5/2, 6/3
	}
}
