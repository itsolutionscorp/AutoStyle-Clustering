public class GregorianDate extends Date {

    public static int[] monthLengths = {31, 28, 31, 30, 31, 30, 31,
        31, 30, 31, 30, 31};

    public GregorianDate(int year, int month, int dayOfMonth) {
        super(year, month, dayOfMonth);
    }

    @Override
    public int dayOfYear() {
        int rtnValue = 0;
        for (int m = 0; m < month() - 1; m++) {
            rtnValue += monthLengths[m];
        }
        return rtnValue + dayOfMonth();
    }
    
    @Override
    public Date nextDate() {
    	int d = dayOfMonth();
    	int m = month();
    	int y = year();
    	++d;
    	if (d > monthLengths[m - 1]) {
    		d = 1;
    		++m;
    		if (m > 12) {
    			m = 1;
    			++y;
    		}
    	}
    	Date result = new GregorianDate(y, m, d);
    	return result;
    }
    
}