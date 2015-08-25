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
    	int year = year();
    	int month = month();
    	int day = dayOfMonth();
    	
    	if (dayOfMonth() == monthLengths[month()]) {
    		if (month() == 12) {
    			year++;
    			month = 1;
    			day = 1;
    		} else {
    		month++;
    		day = 1;
    		}
    	}
    	else {
    		day++;
    	}
    	return new GregorianDate(year, month, day);
    }
}
