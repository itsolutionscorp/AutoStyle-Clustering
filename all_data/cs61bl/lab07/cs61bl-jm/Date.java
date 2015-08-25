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

        if (myYear < 1900 || myYear > 2100){
        	throw new IllegalStateException("Error - Year is out of range (1900-2100)");
        } if (myMonth < 1 || myMonth > 12) {
        	throw new IllegalStateException("Error - Month is out of range (1-12)");
        } if (myDateInMonth < 1 || myDateInMonth > 31) {
        	throw new IllegalStateException("Error - Day is out of range (1-31)");
        } if (myMonth == 2 && ((myDateInMonth == 29 && leapDay == false) || myDateInMonth > 29)) {
        	throw new IllegalStateException("Error - February cannot have " + myDateInMonth + " days");
        } if (myDateInMonth==31 && (myMonth == 4 || myMonth==6 || myMonth==9 || myMonth==11)) {
        	throw new IllegalStateException("Error - Month " + myMonth + " cannot have " + myDateInMonth + " days");
        }
    }
}
