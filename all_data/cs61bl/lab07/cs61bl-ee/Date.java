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
    public void isOK() throws IllegalStateException{
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
        
        if(myYear < 0 || myDateInMonth <0 || myMonth <0 ){
        	throw new IllegalStateException("Negative is not allowed");
        }
        if(myMonth > 12){
        	throw new IllegalStateException("Month out of bound");
        }
        if(myYear > 2100 || myYear < 1900){
        	throw new IllegalStateException("Year out of bound");
        }
        if(leapDay){
        	int[] dayArray = { 31, 29, 31,30, 31, 30, 31, 31, 30, 31,30,31};
        	if (dayArray[myMonth-1] < myDateInMonth){
        		throw new IllegalStateException("Day out of bound");
        	}
        }
        else{
        	int[] dayArray = { 31, 28, 31,30, 31, 30, 31, 31, 30, 31,30,31};
        	if (dayArray[myMonth-1] < myDateInMonth){
        		throw new IllegalStateException("Day out of bound");
        }
        
    }
}
}
