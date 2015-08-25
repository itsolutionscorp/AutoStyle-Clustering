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
    	int nYear = year();
    	int nMonth = month();
    	int nDay = dayOfMonth();
    	
    	if (monthLengths[month()-1] == dayOfMonth()) {
    		nDay = 1;
    		nMonth += 1;
    	}
    	if (nMonth == 13) {
    		nYear += 1;
    		nMonth = 1;
    	}
    	
    	return new GregorianDate(nYear, nMonth, nDay);
    }

}
