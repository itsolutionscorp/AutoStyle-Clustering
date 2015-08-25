public class FrenchRevolutionaryDate extends Date {

    // In a nonleap year in the French Revolutionary Calendar,
    // the first twelve months have 30 days and month 13 has five days.
    public FrenchRevolutionaryDate(int year, int month, int dayOfMonth) {
        super(year, month, dayOfMonth);
    }

    @Override
    public int dayOfYear() {
        return (month() - 1) * 30 + dayOfMonth();
    }
    
    public String nextDate() {
    	int d = dayOfMonth();
    	int m = month();
    	int y = year();
    	if (m < 13 && d == 30) {
    		d = 1;
    		m++;
    	}
    	else if (m == 13 && d == 5) {
    		d = 1;
    		m = 1;
    		y++;
    	}
    	else {
    		d++;
    	}
        return "" + d + "/" + m + "/" + y;
    }

    public static void main(String[] args) {
    	FrenchRevolutionaryDate d = new FrenchRevolutionaryDate(1996, 13, 5);
    	System.out.println(d.nextDate());
    	d = new FrenchRevolutionaryDate(1996, 2, 30);
    	System.out.println(d.nextDate());
    	d = new FrenchRevolutionaryDate(1996, 5, 14);
    	System.out.println(d.nextDate());
    }
}
