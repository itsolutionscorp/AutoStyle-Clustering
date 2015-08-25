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
    
    
    public static void main(String[] args) {
    	Date g = new GregorianDate(2015, 12, 31);
    	System.out.println(g);
      	System.out.println(g.nextDate());
      	
    	Date g2 = new GregorianDate(2015, 6, 30);
    	System.out.println(g2);
      	System.out.println(g2.nextDate());

    	Date f = new FrenchRevolutionaryDate(2015, 13, 5);
    	System.out.println(f);
      	System.out.println(f.nextDate());
      	
    	Date f2 = new FrenchRevolutionaryDate(2015, 1, 30);
    	System.out.println(f2);
      	System.out.println(f2.nextDate());
    }

}

