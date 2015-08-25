public class FrenchRevolutionaryDate extends Date {
	
    public static int[] monthLengths = {30, 30, 30, 30, 30, 30,
    	30, 30, 30, 30, 30, 30, 5}; // 13 months in total

    // In a nonleap year in the French Revolutionary Calendar,
    // the first twelve months have 30 days and month 13 has five days.
    public FrenchRevolutionaryDate(int year, int month, int dayOfMonth) {
        super(year, month, dayOfMonth);
    }

    @Override
    public int dayOfYear() {
        return (month() - 1) * 30 + dayOfMonth();
    }
    
    @Override
    public Date nextDate() {
    	int d = dayOfMonth();
    	int m = month();
    	int y = year();
    	++d;
    	if (d > monthLengths[m - 1]) {
    		// pass the month boundary
    		d = 1;
    		++m;
    		if (m > 13) {
    			// Happy New Year!
    			m = 1;
    			++y;
    		}
    	}
    	Date result = new FrenchRevolutionaryDate(y, m, d);
    	return result;
    }

}
