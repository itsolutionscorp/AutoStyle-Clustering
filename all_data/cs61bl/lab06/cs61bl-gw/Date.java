public abstract class Date {

    public abstract int dayOfYear();
    private int myDayOfMonth;
    private int myMonth;
    private int myYear;

    public Date(int year, int month, int dayOfMonth) {
        myDayOfMonth = dayOfMonth;
        myMonth = month;
        myYear = year;
    }
    
    public boolean LeapYear() {
    	int currentYear = year();
    	boolean IsLeapYear = false;
    	
		if (currentYear % 100 != 0) {
		if (currentYear % 400 != 0) {
		if (currentYear % 4 != 0) {
			IsLeapYear = false;	
		} else 
			IsLeapYear = true;
		} else 
			IsLeapYear = true;
		} else 
			IsLeapYear = false;
		return IsLeapYear;
		
		}
    
    
    public int dayOfMonth() {
        return myDayOfMonth;
    }

    public int month() {
        return myMonth;
    }

    public int year() {
        return myYear;
    }

    public String toString() {
        return "" + myDayOfMonth + "/" + myMonth + "/" + myYear;
    }
    
    
    public abstract Date nextDate();

}


