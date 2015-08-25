import java.util.Arrays;
import java.lang.Comparable;

public  class SimpleDate implements Comparable<SimpleDate> {
	int Month;
	int Day;
	
	public SimpleDate(){
		
	}
	public SimpleDate(int x, int y){
		Month = x;
		Day = y;
	}
	
	@Override
	public String toString(){
		String result = new String();
		result = this.Month + "/" + this.Day;
		return result;
		
	}
	@Override
	public int compareTo(SimpleDate other) {
		int result = 0;
			if(this.Month > other.Month ){
				result = 1;
			}else if(this.Month < other.Month){
				result = -1;
			}else{
				result = 0 ;
			}
			return result;
		
	}
	
	public static void main (String [ ] args) {
	    SimpleDate [ ] dArray = new SimpleDate [4];
	    dArray[0] = new SimpleDate (5, 2); // 5/2
	    dArray[1] = new SimpleDate (2, 9); // 2/9
	    dArray[2] = new SimpleDate (6, 3); // 6/3
	    dArray[3] = new SimpleDate (1, 11); // 1/11
	    Arrays.sort(dArray);
	    for (int k=0; k<dArray.length; k++) {
	        System.out.println(dArray [k]);
	    }
	    // should print the dates in chronological order:
	    // 1/11, 2/9, 5/2, 6/3
	}


	
}
