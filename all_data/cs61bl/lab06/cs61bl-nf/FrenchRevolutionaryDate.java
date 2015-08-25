public class FrenchRevolutionaryDate extends Date {
	
    public static int[] monthLengths = {30, 30, 30, 30, 30, 30,
    	30, 30, 30, 30, 30, 30, 5}; // 13 months in total

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
    	d++;
    	if (d > monthLengths[m - 1]) {
    		d = 1;
    		++m;
    		if (m > 13) {
    			m = 1;
    			++y;
    		}
    	}
    	Date fDate = new FrenchRevolutionaryDate(y, m, d);
    	return fDate;
    }

}