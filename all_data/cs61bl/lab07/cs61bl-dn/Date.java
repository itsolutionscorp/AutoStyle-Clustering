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
        if(myYear < 1900 || myYear > 2100){
        	throw new IllegalStateException("year must be between 1900 or 2100");
        } else if(myDateInMonth < 0 || myDateInMonth > 31){
        	throw new IllegalStateException("days cannot be less than 0 or greater than 31");
        } else if (myMonth < 0 || myMonth > 12){
        	throw new IllegalStateException("invalid month");
        } else if(myMonth == 4 || myMonth == 6 || myMonth == 9 || myMonth == 11){
        	if (myDateInMonth > 30){
        		throw new IllegalStateException("april june september november only have 30 days");
        	}
        } else if(myMonth == 2){
        	if(leapDay){
        		if(myDateInMonth > 29){
        			throw new IllegalStateException("only 29 days in February of a leap year");
        		}
        	} else{
        		if (myDateInMonth > 28){
        			throw new IllegalStateException("February only has 28 days");
        		}
        	}
        }
        
    }
}
