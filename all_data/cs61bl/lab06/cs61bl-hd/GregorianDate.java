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
    	int year, month, date;
    	year = year();
    	month = month();
    	date = dayOfMonth();
    	if(month == 12) {
    		if(date == monthLengths[month-1]) {
    			year++;
    			month = 1;
    			date = 1;
    		}
    		else date++;
    	}
    	else {
    		if(date == monthLengths[month-1]) {
    			month++;
    			date = 1;
    		}
    		else date++;
    	}
    	
    	Date d = new GregorianDate(year, month, date);
    	return d;
    }
    

}
