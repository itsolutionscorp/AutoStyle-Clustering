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
        if (1900>myYear || 2100<myYear) {
        	throw new IllegalStateException("year out of range");
        } else if (myMonth>12 || myMonth<1) {
        	throw new IllegalStateException("month out of range");
        } else if (!leapDay) {
        	int[] monthday=new int[] {31,28,31,30,31,30,31,31,30,31,30,31};
        	if (myDateInMonth<1 || myDateInMonth>monthday[myMonth-1]) {
        		throw new IllegalStateException("month out of range");
        	}
        } else if (leapDay) {
        	int[] monthday=new int[] {31,29,31,30,31,30,31,31,30,31,30,31};
        	if (myDateInMonth<1 || myDateInMonth>monthday[myMonth-1]) {
        		throw new IllegalStateException("month out of range");
        	}
        }
    }
}
