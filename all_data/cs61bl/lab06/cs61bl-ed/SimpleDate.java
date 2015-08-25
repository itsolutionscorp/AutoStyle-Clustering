import java.util.Arrays;

public class SimpleDate implements Comparable<SimpleDate> {
	int myMonth;
	int myDay;
	String mydate;
	public SimpleDate(int x, int y){
		myMonth = x;
		myDay = y;
		mydate =  myMonth+ "/" +myDay;
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
	    //System.out.println(dArray[0].mydate);}

	@Override
	public int compareTo(SimpleDate o) {
		// TODO Auto-generated method stub
		if (this.myMonth > o.myMonth){
			return 1;
			
		}
		else if(this.myMonth == o.myMonth)
		{
		    if(this.myDay > o.myDay){
		    	return 1;
		    }
		    else if (this.myDay == o.myDay){
		    	return 0;
		    
		    }
		    else{
		    	return -1;
		    }
		}
		else{
			return -1;
		}
		
	}
	@Override
	public String toString() {
	    return mydate;
	}
	
}
