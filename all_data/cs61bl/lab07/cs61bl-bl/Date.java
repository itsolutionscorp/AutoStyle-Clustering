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
        int[] monthLengths = {31, 28, 31, 30, 31, 30, 31,
                    31, 30, 31, 30, 31};
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

        if (leapDay == true) {
            monthLengths[1] = 29;
        }

        if (myYear >= 1900 && myYear <= 2100 &&
            myMonth >= 1 && myMonth <= 12 &&
            myDateInMonth >= 1 && myDateInMonth <= monthLengths[myMonth - 1]) {
           
        } else {
            throw new IllegalStateException("Invalid month, year, or date in month.");
        }
    }
}
