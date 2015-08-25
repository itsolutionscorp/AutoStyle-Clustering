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
    		int DaysInMonth;
    		DaysInMonth = monthLengths[month()-1];
    		int tempDay = dayOfMonth();
    		int tempMonth = month();
    		int tempYear = year();
    		if (tempMonth <= 11 ) {
    			if (tempDay == DaysInMonth) {
    				tempDay = 1;
    				tempMonth ++;
    			} else {
    				tempDay ++;
    			}
    		} else {
    			if (tempDay == DaysInMonth) {
    				tempDay = 1;
    				tempMonth = 1;
    				tempYear ++;
    			} else {
    				tempDay++;
    			}
    		}
    		
    		Date date = new GregorianDate(tempDay, tempMonth, tempYear);
    		return date;
    }

}
