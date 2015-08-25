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
        
        if (myYear > 2100 || myYear <1900) {
        	throw new IllegalStateException("The year is out of range");
        }
        
        if(myMonth < 1 || myMonth > 12) {
        	throw new IllegalStateException("The month is out of range");
        }
        
        if(myDateInMonth < 0) {
        	throw new IllegalStateException("The date is out of range");
        }
        
        int[] monthLengths = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        if (monthLengths[myMonth-1] < myDateInMonth && !leapDay) {
        	throw new IllegalStateException("The date is out of range");
        }
        
        if (myMonth == 2 && leapDay && myDateInMonth>29) {
        	throw new IllegalStateException("The date is out of range");
        }

        // YOUR CODE HERE
    }
}
