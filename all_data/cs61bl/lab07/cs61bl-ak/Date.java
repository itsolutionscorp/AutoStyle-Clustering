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

        if (myYear < 1900 || myYear > 2100 || myMonth < 1 || myMonth > 12 ||
            myDateInMonth < 1 || myDateInMonth > 31) {
            throw new IllegalStateException("Not a valid date between the years 1900 and 2100"); 
        }
        if (myMonth == 4 && myDateInMonth > 30 || myMonth == 6 && myDateInMonth > 30 || 
            myMonth == 9 && myDateInMonth > 30|| myMonth == 11 && myDateInMonth > 30) {
            throw new IllegalStateException("Months of April, June, September and November only have 30 days"); 
        }
        if (myMonth == 2 && myDateInMonth > 28 && leapDay == false) {
            throw new IllegalStateException("Month of February only has 28 days"); 
        }
        if (myMonth == 2 && myDateInMonth > 29 && leapDay == true) {
        	throw new IllegalStateException("Month of February only has 29 days in leap"); 
        }
   }
}
