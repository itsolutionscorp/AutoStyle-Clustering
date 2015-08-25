import java.util.Arrays;

public class SimpleDate implements Comparable<SimpleDate>{
	private int myMonth;
	private int myDayOfMonth;

	public SimpleDate(int x, int y){
		myMonth = x;
		myDayOfMonth = y;
	}

	public String toString() {
        return "" + myMonth + "/" + myDayOfMonth;
    }

    public int myMonth(){
    	return myMonth;
    }

    public int myDayOfMonth(){
    	return myDayOfMonth;
    }

    public int compareTo(SimpleDate x){
    	if(x.myMonth() > this.myMonth()){
    		return -1;
    	}
    	if(x.myMonth() < this.myMonth()){
    		return 1;
    	}
    	if(x.myDayOfMonth() > this.myDayOfMonth()){
    		return -1;
    	}
    	if(x.myDayOfMonth() < this.myDayOfMonth()){
    		return 1;
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