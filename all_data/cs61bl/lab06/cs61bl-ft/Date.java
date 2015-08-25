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
    
    public abstract Date nextDate();
    
    /*public static void main(String[] args) {	
		GregorianDate gd = new GregorianDate(1999, 2, 1);
		FrenchRevolutionaryDate fd = new FrenchRevolutionaryDate(1870,1,01);
		System.out.println(gd.nextDate());
		System.out.println(fd.nextDate());
    }*/
    

}
