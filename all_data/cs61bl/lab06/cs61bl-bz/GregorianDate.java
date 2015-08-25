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
    	int newDayOfMonth = dayOfMonth() + 1;
    	int newMonth = month();
    	int newYear = year();
    	if (dayOfMonth() == 31) {
    		newDayOfMonth = 1;
    		newMonth += 1;
    		if (month() == 12) {
    			newMonth = 1;
    			newYear += 1;
    		}
    	}
    	if (dayOfMonth() == 30) {
    		if (month() == 4 || month() == 6 || month() == 9 || month() == 11) {
    			newDayOfMonth = 1;
    			newMonth += 1;
    		}
    	}
    	if (dayOfMonth() == 28 && month() == 2) {
    		newDayOfMonth = 1;
    		newMonth += 1;
    	}
    	return new GregorianDate(newYear, newMonth, newDayOfMonth);
    }
}
