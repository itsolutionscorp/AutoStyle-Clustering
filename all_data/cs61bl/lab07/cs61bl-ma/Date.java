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
        int[] stdyear = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        int[] leapyear = {31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31}; 
        
        if (myYear < 1900 || myYear > 2100) {
        	throw new IllegalStateException("Year must be between 1900 and 2100, inclusive."); 
        }
        if (myMonth < 1 || myMonth > 12) {
        	throw new IllegalStateException("Month must be between 1 and 12, inclusive.");
        }
        if (leapDay) {
        	if (myDateInMonth < 1 || myDateInMonth > leapyear[myMonth-1]) {
        		throw new IllegalStateException("Day must be between 1 and " + leapyear[myMonth-1] + ", inclusive.");
        	}
        } else {
        	if (myDateInMonth < 1 || myDateInMonth  > stdyear[myMonth-1]) {
        		throw new IllegalStateException("Day must be between 1 and " + stdyear[myMonth-1] + ", inclusive."); 
        	}
        }
    }
}
