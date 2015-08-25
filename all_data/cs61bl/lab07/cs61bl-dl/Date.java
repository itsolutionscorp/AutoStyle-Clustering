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
        if (myYear > 2100 || myYear < 1900) {
        	throw new IllegalStateException("years must be between 1900 and 2100,"
        			+ "inclusive");
        }
        
        if (myMonth > 12 || myMonth < 1) {
        	throw new IllegalStateException("months must be between 1 and 12,"
        			+ "inclusive");
        }
        if (myDateInMonth < 0) {
        	throw new IllegalStateException("not a valid day in the month");
        }
        if (leapDay && myMonth == 2 && myDateInMonth > 29 ) {
        	throw new IllegalStateException("not a valid day in February during"
        			+ "Leap Years");
        }
        if (leapDay == false && myMonth == 2 && myDateInMonth > 28) {
        	throw new IllegalStateException("not a valid day in February.");
        }
        int[] day31s = new int[] {1,3,5,7,8,10,12};
        int[] day30s = new int[] {2,4,6,9,11};
        for (int i: day30s) {
        	if (myMonth == i) {
        		if (myDateInMonth > 30) {
        			throw new IllegalStateException("not a valid day in the month.");
        		}
        	}
        }
        for (int i: day31s) {
        	if (myMonth == i) {
        		if (myDateInMonth > 31) {
        			throw new IllegalStateException("not a valid day in the month.");
        		}
        	}
        }
        
    }
}
