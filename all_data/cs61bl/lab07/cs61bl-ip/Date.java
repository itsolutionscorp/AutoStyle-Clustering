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

        if (myYear > 2100 || myYear < 1900) {
        	throw new IllegalStateException("wrong year");
        }
        
        if (myMonth > 12 || myMonth < 1) {
        	throw new IllegalStateException("wrong month");
        }
        
        if (myDateInMonth < 1) {
        	throw new IllegalStateException
        		("no negative or zero days");
        }
        
        IllegalStateException tooLarge 
        	= new IllegalStateException("day too large");
        
        if (myMonth == 2 && leapDay) {
        	if (myDateInMonth > 29) {
        		throw tooLarge;
        	}
        } else if (myMonth == 2) {
        	if (myDateInMonth > 28) {
        		throw tooLarge;
        	}
        } else if (myMonth == 1 || myMonth == 3
        		|| myMonth == 5 || myMonth == 7
        		|| myMonth == 8 || myMonth == 10
        		|| myMonth == 12) {
        	if (myDateInMonth > 31) {
        		throw tooLarge;
        	}
        } else {
        	if (myDateInMonth > 30)
        		throw tooLarge;
        }
    }
}
