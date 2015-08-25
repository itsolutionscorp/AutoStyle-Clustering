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
        if (myDateInMonth <= 0) {
        	throw new IllegalStateException("must be positive number!");
        }
        if (myYear < 1900 || myYear > 2100) {
        	throw new IllegalStateException("year is out of range");
        } else if (myMonth > 12 || myMonth < 1) {
        	throw new IllegalStateException("month is out of range");
        } else if (myMonth == 2) {
        	 if (myDateInMonth > 28 && leapDay == false) {
        		 throw new IllegalStateException("Date is out of range");
        	 } else if (myDateInMonth > 29 && leapDay == true) {
        		 throw new IllegalStateException("Date is out of range");
        	 }
        } if (myMonth == 4 || myMonth == 6 || myMonth == 9 || myMonth == 11) {
        	if (myDateInMonth > 30) {
             	throw new IllegalStateException("Date is out of range");
        	}
        } else if (myDateInMonth > 31) {
         	throw new IllegalStateException("Date is out of range");

        }
        
    }
}
