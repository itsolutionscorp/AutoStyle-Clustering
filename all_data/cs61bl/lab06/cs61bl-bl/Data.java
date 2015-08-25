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

    // Then, add an abstract method named nextDate to the Date class.
    // nextDate returns the result of advancing this date by one day.
    // It should not change this date. Then modify the other two classes accordingly.
    // Make sure to test out your methods to be sure that they're right!
    public abstract int nextDate();

}

