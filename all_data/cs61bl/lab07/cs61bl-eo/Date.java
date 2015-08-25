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
        } if (myMonth == 1 || myMonth == 3 || myMonth == 5 ||myMonth == 7 ||myMonth == 8 ||myMonth == 10 ||myMonth == 12) {
        	if (myDateInMonth > 31 || myDateInMonth < 1 ) {
        		throw new IllegalStateException("Invalid date");
        	}
        } else if (myMonth == 4 || myMonth == 6 || myMonth == 9 || myMonth == 11) {
        	if (myDateInMonth > 30 || myDateInMonth < 1) {
        		throw new IllegalStateException("Invalid date");
        	}
        } else if (myMonth == 2) {
        	if (leapDay == true) {
        		if (myDateInMonth > 29 || myDateInMonth < 1) {
        			throw new IllegalStateException("Invalid date");
        		}
        	} else if (leapDay == false) {
        		if (myDateInMonth > 28 || myDateInMonth < 1) {
        			throw new IllegalStateException("Invalid date");
        		}
        	}
        } if (myMonth < 1 || myMonth > 12) {
        	throw new IllegalStateException("Invalid date");
        } if (myYear < 1900 || myYear > 2100) {
        	throw new IllegalStateException("Invalid date");
        }
        	
    }
}
