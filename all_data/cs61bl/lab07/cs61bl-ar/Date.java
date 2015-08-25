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

        // YOUR CODE HERE
        if (this.myMonth>12 ||this.myMonth <1 ||this.myYear >2100 || this.myYear <1900 || this.myDateInMonth<1){
        	throw new IllegalStateException("Month or year out of bound");
        }else if (this.myMonth == 2){
            	if (leapDay && this.myDateInMonth > 29){
                	throw new IllegalStateException("More days in leap feburary");
            	}else if (!leapDay && this.myDateInMonth > 28){
                	throw new IllegalStateException("More days in non-leap feburary");
            	}
        }else if ((this.myMonth == 1 || this.myMonth == 3 || this.myMonth == 5 || this.myMonth == 7||
        		this.myMonth == 8||this.myMonth == 10||this.myMonth == 12)
        		 && this.myMonth < 8 && this.myDateInMonth>31){
        	throw new IllegalStateException("More days in 31-day month");

        
        }else if((this.myMonth == 2 || this.myMonth == 4 || this.myMonth == 6 || this.myMonth == 9||
        		this.myMonth == 11)
        		&&this.myDateInMonth > 30){
        	throw new IllegalStateException("More days in 30-day month");
        }
    }
}
