import java.util.*;


public class SimpleDate implements Comparable<SimpleDate>{
	
	private int month;
	private int day;
	
    public SimpleDate(int input_month, int input_day) {
    	month = input_month;
    	day = input_day;
    }
    public int getMonth(){
    	return month;
    }
    public int getDay(){
    	return day;
    }
    
    public int compareTo(SimpleDate Date_To_Compare){
    	
    	if (this.getMonth()!= Date_To_Compare.getMonth()) {
    	   return this.getMonth()-Date_To_Compare.getMonth();
    	}else{
    	   return this.getDay()-Date_To_Compare.getDay();
    	}
    }
    public String toString(){
        
    	return month + "/" +day;
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
