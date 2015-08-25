public class Date {
    
    private int myMonth;        // months range from 1 (January) through 12 (December)
    private int myDateInMonth;  // dates-in-month range from 1 through the number of days in the month
    private int myYear;         // years are between 1900 and 2100 (arbitrary decision)

    private int[] daysInMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, // 1 - 10
    							 30, 31};								 // 11 - 12
    
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
        if (myYear < 1900 || myYear > 2100 || // year check
        	myMonth < 1   || myMonth>12    || // month check
        	myDateInMonth < 1 || myDateInMonth > getDaysInMonth(myMonth, leapDay) // day check
        	){
        		throw new IllegalStateException("Bad Format"); 
        	}

        // YOUR CODE HERE
    }
    
    public int getDaysInMonth(int month, boolean leapYear) {
    	int days = daysInMonth[month - 1];
    	if(month == 2 && leapYear) {
    		return days + 1;
    	}
    	return days;
    	
    }
}
