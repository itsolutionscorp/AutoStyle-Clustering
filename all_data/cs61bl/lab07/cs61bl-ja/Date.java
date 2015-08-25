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
        int[] months = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
        int[] numDays = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        int[] numDaysLeap = {31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        
        boolean monthOK = false;
        boolean dayOK = false;
        boolean yearOK = false;
        
        for (int k = 0; k < months.length; k++) {
        	if (myMonth <= 0) {
        		monthOK = false;
        	}
        	else if (myMonth == months[k]) {
        		monthOK = true;
        	}
        }
        
        
        
        System.out.println(monthOK);
        
        if (leapDay) {
        	if ((myDateInMonth <= numDaysLeap[Math.abs(myMonth)-1]) && (myDateInMonth > 0)) {
        		dayOK = true;
        	}
        }
        else {
        	if ((myDateInMonth <= numDays[Math.abs(myMonth)-1]) && (myDateInMonth > 0)) {
        		dayOK = true;
        	}
        }
        
        if ((myYear >= 1900) && (myYear <= 2100)) {
        	yearOK = true;
        }
        
        if ((monthOK == false) || (dayOK == false) || (yearOK == false)) {
        	throw new IllegalStateException("Date value is not legal.");
        }
        
    }
}
