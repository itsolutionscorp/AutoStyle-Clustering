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
        int daysPerMonth[] = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        if (myYear % 4 != 0) {
            leapDay = false;
        } else if (myYear % 100 != 0) {
            leapDay = true;
        } else if (myYear % 400 != 0) {
            leapDay = false;
        } else {
            leapDay = true;
        }
	    if (leapDay) {
	    	daysPerMonth[1] = 29;
	    	System.out.println("its a leap year");
	    }
	    if (myYear > 2100 | myYear < 1900){
	    	throw new IllegalStateException("Year must be between 1900 and 2100.");
	    }
	    if (myMonth > 12 | myMonth < 1){
	    	throw new IllegalStateException("Month must be between 1 and 12.");
	    }
	    if (myDateInMonth > daysPerMonth[myMonth - 1] | myDateInMonth < 1){
	    	throw new IllegalStateException("Day must be greater than 1 and at most " + daysPerMonth[myMonth -1]);
	    }
    }
}
