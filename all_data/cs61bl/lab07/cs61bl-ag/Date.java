public class Date {
    
    private int myMonth;        // months range from 1 (January) through 12 (December)
    private int myDateInMonth;  // dates-in-month range from 1 through the number of days in the month
    private int myYear;         // years are between 1900 and 2100 (arbitrary decision)
    private int[] monthLengths;

    public Date (int month, int dateInMonth, int year) {
        myMonth = month;
        myDateInMonth = dateInMonth;
        myYear = year;
        monthLengths = new int[] {31,28,31,30,31,30,31,31,30,31,30,31};
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
        if(myYear>2100||myYear<1900)
        	throw new IllegalStateException("Warning: time travel may cause disintegration. Please enter a year between 1900 and 2100.");
        if(myMonth<1||myMonth>12)
        	throw new IllegalStateException("We're not French revolutionaries (which is why we're still alive). Please enter a month between 1 and 12.");
        if(leapDay){
        	monthLengths[1]=29;
        }
        if(myDateInMonth<0||myDateInMonth>monthLengths[myMonth-1])
            	throw new IllegalStateException("OK buddy give me a valid day in the month. Hand it over nice and easy and no one gets garbage collected.");
    }
}
