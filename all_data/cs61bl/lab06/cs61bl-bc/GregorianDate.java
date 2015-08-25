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
    	int year = year();
    	int month = month();
    	int dayOfMonth = dayOfMonth();
    	if (dayOfMonth == monthLengths[month - 1]) {
    		month++;
    		dayOfMonth = 1;
    	}
    	if (month > 12) {
    		month = 1;
    		year++;
    	}
    	return new GregorianDate(year, month, dayOfMonth);
    }

}
