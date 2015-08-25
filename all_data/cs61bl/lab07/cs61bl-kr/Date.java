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
        }
        else if (myYear % 100 != 0) {
            leapDay = true;
        } 
        else if (myYear % 400 != 0) {
            leapDay = false;
        }
        else {
            leapDay = true;
        }
        
        if ( myYear < 1900 || myYear > 2100) {
        	throw new IllegalStateException("Year is not within paramenters.");
        }
        if (myMonth < 1 || myMonth > 12) {
        	throw new IllegalStateException("Invalid Month.");
        }
        if (myMonth == 2 && leapDay == false && myDateInMonth == 29) {
        	throw new IllegalStateException("This is not a leap year.");
        }
        if (myMonth == 2 && leapDay == true && (myDateInMonth < 1 || myDateInMonth > 29)) {
            throw new IllegalStateException("Invalid Day for February.");
        }
        if ((myMonth == 4 || myMonth == 6 || myMonth == 9 || 
        myMonth == 11) && (myDateInMonth > 30 || myDateInMonth < 1)) {
            throw new IllegalStateException("Invalid Day for the " + myMonth + "th month.");  
        }
        if ((myMonth == 1 || myMonth == 3 || myMonth == 5 || myMonth == 7 ||
        	myMonth == 8 || myMonth ==  10 || myMonth == 12) 
        	&& (myDateInMonth < 1 || myDateInMonth >31)) {
        	throw new IllegalStateException("Day cannot exceed 31 and be less than 1.");
        }
    }
        	
    
}
