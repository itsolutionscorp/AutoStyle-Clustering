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
        
        if (this.myMonth < 1 || this.myMonth > 12) {
        	throw new IllegalStateException("Illegal month value");
        } 
        if (this.myYear < 1900 || this.myYear > 2100) {
        	throw new IllegalStateException("Illegal year value");
        } 
        if (this.myMonth == 2 && this.myDateInMonth > 28 && leapDay == false) {
        	throw new IllegalStateException("Illegal day value");
        }
        if (this.myMonth == 2 && this.myDateInMonth > 29 && leapDay == true) {
        	throw new IllegalStateException("Illegal day value");
        }
        if ((this.myMonth == 4 || this.myMonth == 6 || this.myMonth == 9 || this.myMonth == 11)  && this.myDateInMonth > 30) {
        	throw new IllegalStateException("Illegal day value");
        }
        if ((this.myMonth == 1 || this.myMonth == 3 || this.myMonth == 5 || this.myMonth == 7 || this.myMonth == 8 || this.myMonth == 10 || this.myMonth == 12) && this.myDateInMonth > 31) {
        	throw new IllegalStateException("Illegal day value");
        }
        if (this.myDateInMonth < 1) {
        	throw new IllegalStateException("Illegal day value");
        }


    }
}
