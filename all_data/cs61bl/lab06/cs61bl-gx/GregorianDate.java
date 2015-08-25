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

    
    public Date nextDate() {
    	int nextMonth, nextYear, nextDay;
    	nextMonth = super.month();
    	nextYear = super.year();
    	nextDay = super.dayOfMonth() + 1;
    	
    	if (nextDay > monthLengths[nextMonth - 1]) {
    		nextDay = 1;
    		nextMonth++;
    	}
    	
    	if (nextMonth > 12) {
    		nextMonth = 1;
    		nextYear++;
    	}
    	
    	return new GregorianDate(nextYear, nextMonth, nextDay); 
    }
}
