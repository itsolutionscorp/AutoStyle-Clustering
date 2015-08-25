package lab06;
public abstract class Date implements Comparable <Date> {

	// Add nextDay method
	public abstract Date nextDay();
    public abstract int dayOfYear();
    private int myDayOfMonth;
    private int myMonth;
    private int myYear;

    public Date(int year, int month, int dayOfMonth) {
        myDayOfMonth = dayOfMonth;
        myMonth = month;
        myYear = year;
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
    
    // Adding compareTo method to the class (for comparing two Date objects)
    public int compareTo (Date another_obj){
    	if (this.dayOfYear()> another_obj.dayOfYear()){
    		return 1;
    	}
    	else if (this.dayOfYear() == another_obj.dayOfYear()){
    		return 0;
    	}
    	return -1;
    }
}



