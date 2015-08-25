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
    
    public String nextDate() {
    	int nextYear = year();
    	int nextMonth = month();
    	int nextDay = dayOfMonth() + 1;
    	if (nextDay > monthLengths[month() -1 ]) {
    		if (month() == 12) {
    			nextMonth = 1;
    			nextYear++;
    		} else {
    			nextMonth++;
    		}
    		nextDay = 1;
    	}
    	return "" + nextDay + "/" + nextMonth + "/" + nextYear;
    }

}
