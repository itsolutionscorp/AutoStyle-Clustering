public class Date {
    
    private int myMonth;        // months range from 1 (January) through 12 (December)
    private int myDateInMonth;  // dates-in-month range from 1 through the number of days in the month
    private int myYear;         // years are between 1900 and 2100 (arbitrary decision)
    private int[] monthLengths = {31, 28, 31, 30, 31, 30, 31,
            31, 30, 31, 30, 31};
    public Date (int month, int dateInMonth, int year) {
        myMonth = month;
        myDateInMonth = dateInMonth;
        myYear = year;
    }
    
    
    public void setToTomorrow ( ) {
        myDateInMonth++;
        // This may invalidate the invariant relationship
        // if tomorrow is the first day of the next month.
        if (myDateInMonth > monthLengths[this.myMonth-1]) {
            myMonth++;
            if (myMonth == 13) {
                myMonth = 1;
                myYear = myYear+1;
            }
            myDateInMonth = 1;  // restore the invariant relationship
        }
        //another case is to check the year
    }


    // Determine if the date information is internally consistent.
    public void isOK() {
        boolean leapDay;
        if(myYear < 1900 || myYear >2100){
        	throw new IllegalStateException("year is out of range");
        }else if(myMonth < 1 || myMonth > 12){
        	throw new IllegalStateException("month is out of range");
        }else if (this.myDateInMonth > monthLengths[this.myMonth-1]  || myDateInMonth < 1){
        	throw new IllegalStateException("date is out of range");//check the month 
        }
        
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
    }
}
