import java.util.Arrays;
public class SimpleDate implements Comparable<SimpleDate>{

	int myMonth, myDate;
	
	public SimpleDate(int month, int day) {
		myMonth = month;
		myDate = day;
	}
	
	public int compareTo(SimpleDate o) {
		if (myMonth == o.myMonth){
			if (myDate > o.myDate){
				return 1;
			}else if (myDate < o.myDate){
				return -1;
			}else return 0;
		}else if (myMonth > o.myMonth){
			return 1;
		}else return -1;
	}
	
	public String toString(){
		return "" + myMonth + "/" + myDate;
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
	}

}
