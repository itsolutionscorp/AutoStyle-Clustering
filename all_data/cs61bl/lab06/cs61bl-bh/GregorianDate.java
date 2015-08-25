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
    	int day = this.dayOfMonth();
    	int month = this.month();
    	int year = this.year();
    	if (monthLengths[month-1] == 31) {
    		if (day == 31) {
    			day = 1;
    			month++;
    		} else {
    			day++;
    		}
    	} else if (monthLengths[month-1] == 30) {
    		if (day == 30) {
    			day = 1;
    			month++;
    		} else {
    			day++;
    		}
    	} else {
    		if (day == 28) {
    			day = 1;
    			month++;
    		} else {
    			day++;
    		}
    	}
    	if (month == 13) {
    		month = 1;
    		day = 1;
    		year++;
    	}
    	return new GregorianDate(year, month, day);
    }

}
