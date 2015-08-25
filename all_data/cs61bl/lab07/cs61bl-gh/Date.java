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

        if (myYear<1900 || myYear>2100) {
        	throw new IllegalStateException("Year out of range.");
        } else if (myMonth<1 || myMonth>12) {
        	throw new IllegalStateException("Month out of range.");
        } else if (myMonth==1 || myMonth==3 || myMonth==5 || myMonth==7 ||myMonth==8 || myMonth==10 || myMonth==12) {
        	if (myDateInMonth < 1 || myDateInMonth > 31) {
        		throw new IllegalStateException("Day of month out of range.");
        	}
        } else if (myMonth==2) {
        	if (leapDay) {
        		if (myDateInMonth<1 || myDateInMonth>29) {
        			throw new IllegalStateException("Day of month out of range.");
        		}
        	} else {
        		if (myDateInMonth<1 || myDateInMonth>28) {
        			throw new IllegalStateException("Day of month out of range.");
        		}
        	}
        } else {
        	if (myDateInMonth<1 || myDateInMonth>30) {
        		throw new IllegalStateException("Day of month out of range.");
        	}
        }
    }
}
