public class FrenchRevolutionaryDate extends Date {

    // In a nonleap year in the French Revolutionary Calendar,
    // the first twelve months have 30 days and month 13 has five days.
    public FrenchRevolutionaryDate(int year, int month, int dayOfMonth) {
        super(year, month, dayOfMonth);
    }
    
    public int dayOfYear() {
        return (month() - 1) * 30 + dayOfMonth();
    }
    
    public String nextDate() {	
    	
    	return "" + (dayOfYear() + 1);
    }
    
    public static void main(String args[]) {
    	FrenchRevolutionaryDate date1 = new FrenchRevolutionaryDate(2000, 12, 15);
    	System.out.println(date1.dayOfYear());
    	System.out.println(date1.nextDate());
    	System.out.println(date1.dayOfYear());
    }

}
