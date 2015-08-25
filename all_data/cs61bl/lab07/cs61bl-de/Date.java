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
        boolean ok = true;
        if (myYear > 2100 || myYear < 1900) ok = false;
        if (myMonth < 1 || myMonth > 12) ok = false;
        if (myDateInMonth < 1) ok = false;
        if (myMonth == 4 || myMonth == 6 || myMonth == 9 || myMonth == 11)
        	if (myDateInMonth > 30) ok = false;
        if (myMonth == 2) {
        	if (leapDay) {
        		if (myDateInMonth > 29) ok = false;
        	}
        	else
        		if (myDateInMonth > 28) ok = false;
        }
        if (!ok) throw new IllegalStateException();
    }
}
