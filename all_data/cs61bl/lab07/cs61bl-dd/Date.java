import java.util.Arrays;

public class Date {
    
    private int myMonth;        // months range from 1 (January) through 12 (December)
    private int myDateInMonth;  // dates-in-month range from 1 through the number of days in the month
    private int myYear;         // years are between 1900 and 2100 (arbitrary decision)

    public Date (int month, int dateInMonth, int year) {
        myMonth = month;
        myDateInMonth = dateInMonth;
        myYear = year;
    }
    
    // Determine if the date information is internally consistent.
    public void isOK() {
        boolean leapDay;
        if (myYear % 4 != 0) {
            leapDay = false;
        } else if (myYear % 100 != 0) {
            leapDay = true;
        } else if (myYear % 400 != 0) {
            leapDay = false;
        } else {
            leapDay = true;
        }

        // YOUR CODE HERE
        String smallMonth[] = {"4", "6", "9", "11"};
        if(myYear > 2100 || myYear < 1900){
        	throw new IllegalStateException("Year out of range");
        }
        if(myMonth > 12 || myMonth < 1){
        	throw new IllegalStateException("Month out of range");
        }
        if(myDateInMonth > 31 || myDateInMonth < 0){
        	throw new IllegalStateException("Date in month out of range.");
        }
        if(Arrays.asList(smallMonth).contains(Integer.toString(myMonth)) && myDateInMonth == 31){
        	throw new IllegalStateException("Small month does not have 31 days.");
        }
        if(leapDay == true && myMonth == 2 && myDateInMonth != 29){
        	throw new IllegalStateException("Leap year has 29 days in February.");
        }
        if(leapDay == false && myMonth == 2 && myDateInMonth != 28){
        	throw new IllegalStateException("Non leap year has 28 days in February.");
        }
    }
    
    public static void main(String [] args){
    	String x = "0";
    	String test [] = {"0", "1", "2", "3"};
    	if(Arrays.asList(test).contains("0")){
    		System.out.println("yes");
    	}else{
    		System.out.println("No");
    	}
    }
}
