public abstract class Date {

    public abstract int dayOfYear();
    public abstract Date nextDate();
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
    
    public static void main (String[] args) {
    	GregorianDate beginning = new GregorianDate(2000,1,1);
    	//System.out.println(beginning.nextDate());
    	GregorianDate ending = new GregorianDate(1999,12,31);
    	//System.out.println(ending.nextDate());
    	GregorianDate middle = new GregorianDate(2000, 2, 28);
    	//System.out.println(middle.nextDate());
    	FrenchRevolutionaryDate begF = new FrenchRevolutionaryDate(2000,1,1);
    	System.out.println(begF.nextDate());
    	FrenchRevolutionaryDate endF = new FrenchRevolutionaryDate(1999,13,5);
    	System.out.println(endF.nextDate());
    	FrenchRevolutionaryDate endF2 = new FrenchRevolutionaryDate(2000,12,30);
    	System.out.println(endF2.nextDate());
    	FrenchRevolutionaryDate midF = new FrenchRevolutionaryDate(2000,6,30);
    	System.out.println(midF.nextDate());
    	FrenchRevolutionaryDate midF2 = new FrenchRevolutionaryDate(2000,13,3);
    	System.out.println(midF2.nextDate());
    }

}

