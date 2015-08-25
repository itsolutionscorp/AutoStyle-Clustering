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
        	throw new IllegalStateException("Year is out of range.");
        } else if (myMonth < 1 || myMonth > 12) {
        	throw new IllegalStateException("Month is out of range.");
        } else if (myDateInMonth < 1) {
        	throw new IllegalStateException("Date must be 1 or greater.");
        } else if (myMonth % 2 != 0 || myMonth == 8) {
        	if (myDateInMonth > 31) {
        		throw new IllegalStateException("Indicated month does not have more than 31 days.");
        	}
        } else if (myMonth % 2 == 0 && myMonth != 8 && myMonth != 2) {
        	if (myDateInMonth > 30) {
        		throw new IllegalStateException("Indicated month does not have more than 30 days.");
        	}
        } else if (myMonth == 2) {
        	if (leapDay && myDateInMonth > 29) {
        		throw new IllegalStateException("Indicated month does not have more than 29 days.");
        	} else if (leapDay != true && myDateInMonth > 28) {
        		throw new IllegalStateException("Indicated month does not have more than 28 days.");
        	}
        }
    }
}

/*        // YOUR CODE HERE
        
        if (myYear<1900||myYear>2100) {
        	throw new IllegalArgumentException("ERROR: year out of range");
        } else {
        	if (myMonth<1||myMonth>12) {
            	throw new IllegalArgumentException("ERROR: month out of range");        		
        	} else {
            	if (leapDay==true) {
         		
            	} else {
            		
            	}
        	}
        }

*/
