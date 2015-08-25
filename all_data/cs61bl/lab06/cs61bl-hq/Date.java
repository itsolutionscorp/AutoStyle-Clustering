public abstract class Date {
	public abstract int dayOfYear();
    public abstract String nextDate(); 
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

    public static void main(String[] args) {
    	GregorianDate greg = new GregorianDate(2015, 1, 15);
    	System.out.println(greg.dayOfYear()); // expected 16
    	System.out.println(greg.nextDate()); // expected 16/1/2015
    	System.out.println(greg.toString()); // make sure that nextDate() method did not change date
    	System.out.println();
    	GregorianDate dorian = new GregorianDate(2015, 2, 28);
    	System.out.println(dorian.dayOfYear()); // expected 59
    	System.out.println(dorian.nextDate()); // expected 1/3/2015
    	System.out.println(dorian.toString()); // make sure that nextDate() method did not change date
    	System.out.println();
    	GregorianDate happy = new GregorianDate(2015, 12, 31);
    	System.out.println(happy.dayOfYear()); // expected 365
    	System.out.println(happy.nextDate()); // expected 1/1/2016
    	System.out.println(happy.toString()); // make sure that nextDate() method did not change date
    	System.out.println();
    	FrenchRevolutionaryDate louis = new FrenchRevolutionaryDate(2015, 1, 15);
    	System.out.println(louis.dayOfYear()); // expected 16
    	System.out.println(louis.nextDate()); // expected 16/1/2015
    	System.out.println(louis.toString()); // make sure that nextDate() method did not change date
    	System.out.println();
    	FrenchRevolutionaryDate max = new FrenchRevolutionaryDate(2015, 2, 30);
    	System.out.println(max.dayOfYear()); // expected 60
    	System.out.println(max.nextDate()); // expected 1/3/2015
    	System.out.println(max.toString()); // make sure that nextDate() method did not change date
    	System.out.println();
    	FrenchRevolutionaryDate napoleon = new FrenchRevolutionaryDate(2015, 13, 5);
    	System.out.println(napoleon.dayOfYear()); // expected 365
    	System.out.println(napoleon.nextDate()); // expected 1/1/2016
    	System.out.println(napoleon.toString()); // make sure that nextDate() method did not change date
    }
}

