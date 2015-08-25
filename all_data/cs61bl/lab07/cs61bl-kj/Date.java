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
        if (myMonth > 12 || myYear < 1900 || myYear > 2100 || myDateInMonth > 31 || myDateInMonth < 1 || myMonth < 1) {
        	throw new IllegalStateException("Date is out of range");
        } else if (myMonth == 2 && leapDay) {
        	if (myDateInMonth > 29) {
        		throw new IllegalStateException("Too many days for February");
        	}
        } else if (myMonth == 2 && !leapDay && myDateInMonth > 28) {
        	throw new IllegalStateException("Too many days for February");
        } else if (myMonth == 9 || myMonth == 4 || myMonth == 6 || myMonth == 11) {
        	if(myDateInMonth > 30) {
        		throw new IllegalStateException("Too many days for this month");
        	}
        } else {
        	if (myDateInMonth > 31) {
        		throw new IllegalStateException("Too many days for this month");
        	}
        }
    }
}
