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
        // check year
        if (myYear > 2100 || myYear < 1900) {
        	throw new IllegalStateException("Year out of bounds 1900-2100");
        }
        // check month
        if (myMonth > 12 || myMonth < 1) {
        	throw new IllegalStateException("illegal month");
        }
        // check date
        if (myMonth == 2) {
        	if (leapDay) {
        		if (myDateInMonth > 29 || myDateInMonth < 1) {
        			throw new IllegalStateException("date in February outside bounds 1-29 on leap year");
        		}
        	} else {
        	if (myDateInMonth > 28 || myDateInMonth < 1) {
        		throw new IllegalStateException("illegal date");
        	}}
        }
        if (myMonth == 4 || myMonth == 6 || myMonth == 9 || myMonth == 11) {
        	if (myDateInMonth > 30 || myDateInMonth < 1) {
        		throw new IllegalStateException("illegal date outside 1-30");
        	}
        } else {
        	if (myDateInMonth > 31 || myDateInMonth < 1) {
        		throw new IllegalStateException("illegal date outside 1-31");
        		}
        }
    }
}
