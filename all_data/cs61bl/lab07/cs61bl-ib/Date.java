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

        if (myYear < 1900 || myYear > 2100) {
        	throw new IllegalStateException("Year is out of the acceptable range");
        }
        if (myMonth < 0 || myMonth > 12) {
        	throw new IllegalStateException("Month is out of the acceptable range");
        }
        if (myDateInMonth < 0 || myDateInMonth > 31) {
        	throw new IllegalStateException("Date is out of the acceptable range");
        }
        if (leapDay == false) {
        	if (myMonth == 2 && myDateInMonth == 29) {
        		throw new IllegalStateException("Not a leap year");
        	}
        }
        if (myMonth == 2 || myMonth == 4 || myMonth == 6 || myMonth == 9 || myMonth == 11) {
        	if (myDateInMonth == 31) {
        		throw new IllegalStateException("Month does not contain 31 days");
        	}
        }
    }
}
