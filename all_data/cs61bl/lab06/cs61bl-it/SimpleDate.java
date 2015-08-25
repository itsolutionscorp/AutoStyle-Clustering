import java.util.Arrays;
public class SimpleDate implements Comparable<SimpleDate> {
	public int month;
	public int Day;
	
	public SimpleDate(int monthin , int dayin) {
		 month= monthin;
		 Day= dayin;
	}
	/*Compares this object with the specified object 
	 * for order. Returns a negative integer, zero, or 
	 * a positive integer as this object is less 
	 * than, equal to, or greater than the specified 
	 * object.ava.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(SimpleDate arg0) {
		int value;
		if (this.month>arg0.month){
			value=1;
		}else if (this.month==arg0.month && this.Day>arg0.Day){
			value=1;
		}else if (this.month==arg0.month && this.Day==arg0.Day){
			value=0;
		}else{
			value=-1;
		}
		return value;
	}
	public String toString() {
        return "" + month + "/" + Day;
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
