public class Date {
    
    private int myMonth;        // months range from 1 (January) through 12 (December)
    private int myDateInMonth;  // dates-in-month range from 1 through the number of days in the month
    private int myYear;         // years are between 1900 and 2100 (arbitrary decision)
    private static int[] monthLengths = {31, 28, 31, 30, 31, 30, 31,
        31, 30, 31, 30, 31};
    
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
        
        if (myYear > 2100 || myYear < 1900) {
        		throw new IllegalStateException("year is out of bounds");
        }
        	
        	if (myMonth < 1 || myMonth > 12) {
        		throw new IllegalStateException("month is out of bounds");
        	}
        	
        	
        	if (leapDay && myMonth == 2) {
        		if (myDateInMonth < 1 || myDateInMonth > monthLengths[myMonth - 1] + 1) {
        			throw new IllegalStateException("day is out of bounds");
        		}
        	} else {
        		if (myDateInMonth < 1 || myDateInMonth > monthLengths[myMonth - 1]) {
        			throw new IllegalStateException("day is out of bounds");
        		}
        	}
        
        	
        	
    }
}
