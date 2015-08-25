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
        boolean leapYear;
        if (myYear % 4 != 0) {
            leapYear = false;
        } else if (myYear % 100 != 0) {
            leapYear = true;
        } else if (myYear % 400 != 0) {
            leapYear = false;
        } else {
            leapYear = true;
        }

        // YOUR CODE HERE
        int[] monthLengths = new int[] {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        if (myMonth == 2 && leapYear && myDateInMonth == 29) return;
        if (myYear > 2100 || myYear < 1900) throw new IllegalStateException("Year out of range");
        if (myMonth > 12 || myMonth < 1) throw new IllegalStateException("Month out of range");
        if (myDateInMonth < 1 || myDateInMonth > monthLengths[myMonth-1]) throw new IllegalStateException("Day not in range");
        
    }
}
