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
    	int nextDayOfYear = dayOfYear() + 1;
    	int forDate = nextDayOfYear;
    	int newYear = year();
    	int newMonth = month();
    	int newDayOfMonth = dayOfMonth() + 1;
    	if (nextDayOfYear > 365) {
    		newYear = year() + 1;
    		newMonth = 1;
    		newDayOfMonth = nextDayOfYear - 365;
    		return new GregorianDate(newYear, newMonth, newDayOfMonth);
    	}
    	for (int i = 0; forDate > monthLengths[i]; i++) {
    		forDate -= monthLengths[i];
    		newMonth = i + 2;
    	}
    	newDayOfMonth = forDate;
    	return new GregorianDate(newYear, newMonth, newDayOfMonth);
    }

}
