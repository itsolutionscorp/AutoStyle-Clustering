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
    public Date nextDate(){
    	if(month() < 12 && dayOfMonth() == monthLengths[month() - 1]){ // if next date is in next month
    		return new GregorianDate(year(), month() + 1, 1);
    	}
    	else if (dayOfMonth() == 31){ // next date in next year
    		return new GregorianDate(year() + 1, 1, 1);
    	}
    	return new GregorianDate(year(), month(), dayOfMonth() + 1); // no wrap around
    }

}
